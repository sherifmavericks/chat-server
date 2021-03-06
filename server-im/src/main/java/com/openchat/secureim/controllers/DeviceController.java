package com.openchat.secureim.controllers;

import com.codahale.metrics.annotation.Timed;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.openchat.secureim.auth.AuthenticationCredentials;
import com.openchat.secureim.auth.AuthorizationHeader;
import com.openchat.secureim.auth.InvalidAuthorizationHeaderException;
import com.openchat.secureim.auth.StoredVerificationCode;
import com.openchat.secureim.entities.AccountAttributes;
import com.openchat.secureim.entities.DeviceInfo;
import com.openchat.secureim.entities.DeviceInfoList;
import com.openchat.secureim.entities.DeviceResponse;
import com.openchat.secureim.limits.RateLimiters;
import com.openchat.secureim.sqs.DirectoryQueue;
import com.openchat.secureim.storage.Account;
import com.openchat.secureim.storage.AccountsManager;
import com.openchat.secureim.storage.Device;
import com.openchat.secureim.storage.MessagesManager;
import com.openchat.secureim.storage.PendingDevicesManager;
import com.openchat.secureim.util.Util;
import com.openchat.secureim.util.VerificationCode;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.dropwizard.auth.Auth;

@Path("/v1/devices")
public class DeviceController {

  private final Logger logger = LoggerFactory.getLogger(DeviceController.class);

  private static final int MAX_DEVICES = 6;

  private final PendingDevicesManager pendingDevices;
  private final AccountsManager       accounts;
  private final MessagesManager       messages;
  private final RateLimiters          rateLimiters;
  private final Map<String, Integer>  maxDeviceConfiguration;
  private final DirectoryQueue        directoryQueue;

  public DeviceController(PendingDevicesManager pendingDevices,
                          AccountsManager accounts,
                          MessagesManager messages,
                          DirectoryQueue directoryQueue,
                          RateLimiters rateLimiters,
                          Map<String, Integer> maxDeviceConfiguration)
  {
    this.pendingDevices         = pendingDevices;
    this.accounts               = accounts;
    this.messages               = messages;
    this.directoryQueue         = directoryQueue;
    this.rateLimiters           = rateLimiters;
    this.maxDeviceConfiguration = maxDeviceConfiguration;
  }

  @Timed
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public DeviceInfoList getDevices(@Auth Account account) {
    List<DeviceInfo> devices = new LinkedList<>();

    for (Device device : account.getDevices()) {
      devices.add(new DeviceInfo(device.getId(), device.getName(),
                                 device.getLastSeen(), device.getCreated()));
    }

    return new DeviceInfoList(devices);
  }

  @Timed
  @DELETE
  @Path("/{device_id}")
  public void removeDevice(@Auth Account account, @PathParam("device_id") long deviceId) {
    if (account.getAuthenticatedDevice().get().getId() != Device.MASTER_ID) {
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }

    account.removeDevice(deviceId);
    accounts.update(account);

    if (!account.isActive()) {
      directoryQueue.deleteRegisteredUser(account.getNumber());
    }

    messages.clear(account.getNumber(), deviceId);
  }

  @Timed
  @GET
  @Path("/provisioning/code")
  @Produces(MediaType.APPLICATION_JSON)
  public VerificationCode createDeviceToken(@Auth Account account)
      throws RateLimitExceededException, DeviceLimitExceededException
  {
    rateLimiters.getAllocateDeviceLimiter().validate(account.getNumber());

    int maxDeviceLimit = MAX_DEVICES;

    if (maxDeviceConfiguration.containsKey(account.getNumber())) {
      maxDeviceLimit = maxDeviceConfiguration.get(account.getNumber());
    }

    if (account.getActiveDeviceCount() >= maxDeviceLimit) {
      throw new DeviceLimitExceededException(account.getDevices().size(), MAX_DEVICES);
    }

    if (account.getAuthenticatedDevice().get().getId() != Device.MASTER_ID) {
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }

    VerificationCode       verificationCode       = generateVerificationCode();
    StoredVerificationCode storedVerificationCode = new StoredVerificationCode(verificationCode.getVerificationCode(),
                                                                               System.currentTimeMillis());

    pendingDevices.store(account.getNumber(), storedVerificationCode);

    return verificationCode;
  }

  @Timed
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{verification_code}")
  public DeviceResponse verifyDeviceToken(@PathParam("verification_code") String verificationCode,
                                          @HeaderParam("Authorization")   String authorizationHeader,
                                          @Valid                          AccountAttributes accountAttributes)
      throws RateLimitExceededException, DeviceLimitExceededException
  {
    try {
      AuthorizationHeader header = AuthorizationHeader.fromFullHeader(authorizationHeader);
      String number              = header.getNumber();
      String password            = header.getPassword();

      rateLimiters.getVerifyDeviceLimiter().validate(number);

      Optional<StoredVerificationCode> storedVerificationCode = pendingDevices.getCodeForNumber(number);

      if (!storedVerificationCode.isPresent() || !storedVerificationCode.get().isValid(verificationCode)) {
        throw new WebApplicationException(Response.status(403).build());
      }

      Optional<Account> account = accounts.get(number);

      if (!account.isPresent()) {
        throw new WebApplicationException(Response.status(403).build());
      }

      int maxDeviceLimit = MAX_DEVICES;

      if (maxDeviceConfiguration.containsKey(account.get().getNumber())) {
        maxDeviceLimit = maxDeviceConfiguration.get(account.get().getNumber());
      }

      if (account.get().getActiveDeviceCount() >= maxDeviceLimit) {
        throw new DeviceLimitExceededException(account.get().getDevices().size(), MAX_DEVICES);
      }

      Device device = new Device();
      device.setName(accountAttributes.getName());
      device.setAuthenticationCredentials(new AuthenticationCredentials(password));
      device.setSignalingKey(accountAttributes.getSignalingKey());
      device.setFetchesMessages(accountAttributes.getFetchesMessages());
      device.setId(account.get().getNextDeviceId());
      device.setRegistrationId(accountAttributes.getRegistrationId());
      device.setLastSeen(Util.todayInMillis());
      device.setCreated(System.currentTimeMillis());

      account.get().addDevice(device);
      messages.clear(account.get().getNumber(), device.getId());
      accounts.update(account.get());

      pendingDevices.remove(number);

      return new DeviceResponse(device.getId());
    } catch (InvalidAuthorizationHeaderException e) {
      logger.info("Bad Authorization Header", e);
      throw new WebApplicationException(Response.status(401).build());
    }
  }

  @VisibleForTesting protected VerificationCode generateVerificationCode() {
    SecureRandom random = new SecureRandom();
    int randomInt       = 100000 + random.nextInt(900000);
    return new VerificationCode(randomInt);
  }
}
