package com.openchat.protocal;


import com.openchat.protocal.ecc.Curve;
import com.openchat.protocal.ecc.ECKeyPair;
import com.openchat.protocal.ecc.ECPublicKey;
import com.openchat.protocal.logging.Log;
import com.openchat.protocal.protocol.CiphertextMessage;
import com.openchat.protocal.protocol.KeyExchangeMessage;
import com.openchat.protocal.protocol.PreKeyOpenchatMessage;
import com.openchat.protocal.ratchet.AliceOpenchatParameters;
import com.openchat.protocal.ratchet.BobOpenchatParameters;
import com.openchat.protocal.ratchet.RatchetingSession;
import com.openchat.protocal.ratchet.SymmetricOpenchatParameters;
import com.openchat.protocal.state.OpenchatStore;
import com.openchat.protocal.state.IdentityKeyStore;
import com.openchat.protocal.state.PreKeyBundle;
import com.openchat.protocal.state.PreKeyStore;
import com.openchat.protocal.state.SessionRecord;
import com.openchat.protocal.state.SessionState;
import com.openchat.protocal.state.SessionStore;
import com.openchat.protocal.state.SignedPreKeyStore;
import com.openchat.protocal.util.KeyHelper;
import com.openchat.protocal.util.Medium;
import com.openchat.protocal.util.guava.Optional;


public class SessionBuilder {

  private static final String TAG = SessionBuilder.class.getSimpleName();

  private final SessionStore      sessionStore;
  private final PreKeyStore       preKeyStore;
  private final SignedPreKeyStore signedPreKeyStore;
  private final IdentityKeyStore  identityKeyStore;
  private final long              recipientId;
  private final int               deviceId;

  
  public SessionBuilder(SessionStore sessionStore,
                        PreKeyStore preKeyStore,
                        SignedPreKeyStore signedPreKeyStore,
                        IdentityKeyStore identityKeyStore,
                        long recipientId, int deviceId)
  {
    this.sessionStore      = sessionStore;
    this.preKeyStore       = preKeyStore;
    this.signedPreKeyStore = signedPreKeyStore;
    this.identityKeyStore  = identityKeyStore;
    this.recipientId       = recipientId;
    this.deviceId          = deviceId;
  }

  
  public SessionBuilder(OpenchatStore store, long recipientId, int deviceId) {
    this(store, store, store, store, recipientId, deviceId);
  }

  
   Optional<Integer> process(SessionRecord sessionRecord, PreKeyOpenchatMessage message)
      throws InvalidKeyIdException, InvalidKeyException, UntrustedIdentityException
  {
    int         messageVersion   = message.getMessageVersion();
    IdentityKey theirIdentityKey = message.getIdentityKey();

    Optional<Integer> unsignedPreKeyId;

    if (!identityKeyStore.isTrustedIdentity(recipientId, theirIdentityKey)) {
      throw new UntrustedIdentityException();
    }

    switch (messageVersion) {
      case 2:  unsignedPreKeyId = processV2(sessionRecord, message); break;
      case 3:  unsignedPreKeyId = processV3(sessionRecord, message); break;
      default: throw new AssertionError("Unknown version: " + messageVersion);
    }

    identityKeyStore.saveIdentity(recipientId, theirIdentityKey);
    return unsignedPreKeyId;
  }

  private Optional<Integer> processV3(SessionRecord sessionRecord, PreKeyOpenchatMessage message)
      throws UntrustedIdentityException, InvalidKeyIdException, InvalidKeyException
  {

    if (sessionRecord.hasSessionState(message.getMessageVersion(), message.getBaseKey().serialize())) {
      Log.w(TAG, "We've already setup a session for this V3 message, letting bundled message fall through...");
      return Optional.absent();
    }

    ECKeyPair ourSignedPreKey = signedPreKeyStore.loadSignedPreKey(message.getSignedPreKeyId()).getKeyPair();

    BobOpenchatParameters.Builder parameters = BobOpenchatParameters.newBuilder();

    parameters.setTheirBaseKey(message.getBaseKey())
              .setTheirIdentityKey(message.getIdentityKey())
              .setOurIdentityKey(identityKeyStore.getIdentityKeyPair())
              .setOurSignedPreKey(ourSignedPreKey)
              .setOurRatchetKey(ourSignedPreKey);

    if (message.getPreKeyId().isPresent()) {
      parameters.setOurOneTimePreKey(Optional.of(preKeyStore.loadPreKey(message.getPreKeyId().get()).getKeyPair()));
    } else {
      parameters.setOurOneTimePreKey(Optional.<ECKeyPair>absent());
    }

    if (!sessionRecord.isFresh()) sessionRecord.archiveCurrentState();

    RatchetingSession.initializeSession(sessionRecord.getSessionState(), message.getMessageVersion(), parameters.create());

    sessionRecord.getSessionState().setLocalRegistrationId(identityKeyStore.getLocalRegistrationId());
    sessionRecord.getSessionState().setRemoteRegistrationId(message.getRegistrationId());
    sessionRecord.getSessionState().setAliceBaseKey(message.getBaseKey().serialize());

    if (message.getPreKeyId().isPresent() && message.getPreKeyId().get() != Medium.MAX_VALUE) {
      return message.getPreKeyId();
    } else {
      return Optional.absent();
    }
  }

  private Optional<Integer> processV2(SessionRecord sessionRecord, PreKeyOpenchatMessage message)
      throws UntrustedIdentityException, InvalidKeyIdException, InvalidKeyException
  {
    if (!message.getPreKeyId().isPresent()) {
      throw new InvalidKeyIdException("V2 message requires one time prekey id!");
    }

    if (!preKeyStore.containsPreKey(message.getPreKeyId().get()) &&
        sessionStore.containsSession(recipientId, deviceId))
    {
      Log.w(TAG, "We've already processed the prekey part of this V2 session, letting bundled message fall through...");
      return Optional.absent();
    }

    ECKeyPair ourPreKey = preKeyStore.loadPreKey(message.getPreKeyId().get()).getKeyPair();

    BobOpenchatParameters.Builder parameters = BobOpenchatParameters.newBuilder();

    parameters.setOurIdentityKey(identityKeyStore.getIdentityKeyPair())
              .setOurSignedPreKey(ourPreKey)
              .setOurRatchetKey(ourPreKey)
              .setOurOneTimePreKey(Optional.<ECKeyPair>absent())
              .setTheirIdentityKey(message.getIdentityKey())
              .setTheirBaseKey(message.getBaseKey());

    if (!sessionRecord.isFresh()) sessionRecord.archiveCurrentState();

    RatchetingSession.initializeSession(sessionRecord.getSessionState(), message.getMessageVersion(), parameters.create());

    sessionRecord.getSessionState().setLocalRegistrationId(identityKeyStore.getLocalRegistrationId());
    sessionRecord.getSessionState().setRemoteRegistrationId(message.getRegistrationId());
    sessionRecord.getSessionState().setAliceBaseKey(message.getBaseKey().serialize());

    if (message.getPreKeyId().get() != Medium.MAX_VALUE) {
      return message.getPreKeyId();
    } else {
      return Optional.absent();
    }
  }

  
  public void process(PreKeyBundle preKey) throws InvalidKeyException, UntrustedIdentityException {
    synchronized (SessionCipher.SESSION_LOCK) {
      if (!identityKeyStore.isTrustedIdentity(recipientId, preKey.getIdentityKey())) {
        throw new UntrustedIdentityException();
      }

      if (preKey.getSignedPreKey() != null &&
          !Curve.verifySignature(preKey.getIdentityKey().getPublicKey(),
                                 preKey.getSignedPreKey().serialize(),
                                 preKey.getSignedPreKeySignature()))
      {
        throw new InvalidKeyException("Invalid signature on device key!");
      }

      if (preKey.getSignedPreKey() == null && preKey.getPreKey() == null) {
        throw new InvalidKeyException("Both signed and unsigned prekeys are absent!");
      }

      boolean               supportsV3           = preKey.getSignedPreKey() != null;
      SessionRecord         sessionRecord        = sessionStore.loadSession(recipientId, deviceId);
      ECKeyPair             ourBaseKey           = Curve.generateKeyPair();
      ECPublicKey           theirSignedPreKey    = supportsV3 ? preKey.getSignedPreKey() : preKey.getPreKey();
      Optional<ECPublicKey> theirOneTimePreKey   = Optional.fromNullable(preKey.getPreKey());
      Optional<Integer>     theirOneTimePreKeyId = theirOneTimePreKey.isPresent() ? Optional.of(preKey.getPreKeyId()) :
                                                                                    Optional.<Integer>absent();

      AliceOpenchatParameters.Builder parameters = AliceOpenchatParameters.newBuilder();

      parameters.setOurBaseKey(ourBaseKey)
                .setOurIdentityKey(identityKeyStore.getIdentityKeyPair())
                .setTheirIdentityKey(preKey.getIdentityKey())
                .setTheirSignedPreKey(theirSignedPreKey)
                .setTheirRatchetKey(theirSignedPreKey)
                .setTheirOneTimePreKey(supportsV3 ? theirOneTimePreKey : Optional.<ECPublicKey>absent());

      if (!sessionRecord.isFresh()) sessionRecord.archiveCurrentState();

      RatchetingSession.initializeSession(sessionRecord.getSessionState(),
                                          supportsV3 ? 3 : 2,
                                          parameters.create());

      sessionRecord.getSessionState().setUnacknowledgedPreKeyMessage(theirOneTimePreKeyId, preKey.getSignedPreKeyId(), ourBaseKey.getPublicKey());
      sessionRecord.getSessionState().setLocalRegistrationId(identityKeyStore.getLocalRegistrationId());
      sessionRecord.getSessionState().setRemoteRegistrationId(preKey.getRegistrationId());
      sessionRecord.getSessionState().setAliceBaseKey(ourBaseKey.getPublicKey().serialize());

      sessionStore.storeSession(recipientId, deviceId, sessionRecord);
      identityKeyStore.saveIdentity(recipientId, preKey.getIdentityKey());
    }
  }

  
  public KeyExchangeMessage process(KeyExchangeMessage message)
      throws InvalidKeyException, UntrustedIdentityException, StaleKeyExchangeException
  {
    synchronized (SessionCipher.SESSION_LOCK) {
      if (!identityKeyStore.isTrustedIdentity(recipientId, message.getIdentityKey())) {
        throw new UntrustedIdentityException();
      }

      KeyExchangeMessage responseMessage = null;

      if (message.isInitiate()) responseMessage = processInitiate(message);
      else                      processResponse(message);

      return responseMessage;
    }
  }

  private KeyExchangeMessage processInitiate(KeyExchangeMessage message) throws InvalidKeyException {
    int           flags         = KeyExchangeMessage.RESPONSE_FLAG;
    SessionRecord sessionRecord = sessionStore.loadSession(recipientId, deviceId);

    if (message.getVersion() >= 3 &&
        !Curve.verifySignature(message.getIdentityKey().getPublicKey(),
                               message.getBaseKey().serialize(),
                               message.getBaseKeySignature()))
    {
      throw new InvalidKeyException("Bad signature!");
    }

    SymmetricOpenchatParameters.Builder builder = SymmetricOpenchatParameters.newBuilder();

    if (!sessionRecord.getSessionState().hasPendingKeyExchange()) {
      builder.setOurIdentityKey(identityKeyStore.getIdentityKeyPair())
             .setOurBaseKey(Curve.generateKeyPair())
             .setOurRatchetKey(Curve.generateKeyPair());
    } else {
      builder.setOurIdentityKey(sessionRecord.getSessionState().getPendingKeyExchangeIdentityKey())
             .setOurBaseKey(sessionRecord.getSessionState().getPendingKeyExchangeBaseKey())
             .setOurRatchetKey(sessionRecord.getSessionState().getPendingKeyExchangeRatchetKey());
      flags |= KeyExchangeMessage.SIMULTAENOUS_INITIATE_FLAG;
    }

    builder.setTheirBaseKey(message.getBaseKey())
           .setTheirRatchetKey(message.getRatchetKey())
           .setTheirIdentityKey(message.getIdentityKey());

    SymmetricOpenchatParameters parameters = builder.create();

    if (!sessionRecord.isFresh()) sessionRecord.archiveCurrentState();

    RatchetingSession.initializeSession(sessionRecord.getSessionState(),
                                        Math.min(message.getMaxVersion(), CiphertextMessage.CURRENT_VERSION),
                                        parameters);

    sessionStore.storeSession(recipientId, deviceId, sessionRecord);
    identityKeyStore.saveIdentity(recipientId, message.getIdentityKey());

    byte[] baseKeySignature = Curve.calculateSignature(parameters.getOurIdentityKey().getPrivateKey(),
                                                       parameters.getOurBaseKey().getPublicKey().serialize());

    return new KeyExchangeMessage(sessionRecord.getSessionState().getSessionVersion(),
                                  message.getSequence(), flags,
                                  parameters.getOurBaseKey().getPublicKey(),
                                  baseKeySignature, parameters.getOurRatchetKey().getPublicKey(),
                                  parameters.getOurIdentityKey().getPublicKey());
  }

  private void processResponse(KeyExchangeMessage message)
      throws StaleKeyExchangeException, InvalidKeyException
  {
    SessionRecord sessionRecord                  = sessionStore.loadSession(recipientId, deviceId);
    SessionState  sessionState                   = sessionRecord.getSessionState();
    boolean       hasPendingKeyExchange          = sessionState.hasPendingKeyExchange();
    boolean       isSimultaneousInitiateResponse = message.isResponseForSimultaneousInitiate();

    if (!hasPendingKeyExchange || sessionState.getPendingKeyExchangeSequence() != message.getSequence()) {
      Log.w(TAG, "No matching sequence for response. Is simultaneous initiate response: " + isSimultaneousInitiateResponse);
      if (!isSimultaneousInitiateResponse) throw new StaleKeyExchangeException();
      else                                 return;
    }

    SymmetricOpenchatParameters.Builder parameters = SymmetricOpenchatParameters.newBuilder();

    parameters.setOurBaseKey(sessionRecord.getSessionState().getPendingKeyExchangeBaseKey())
              .setOurRatchetKey(sessionRecord.getSessionState().getPendingKeyExchangeRatchetKey())
              .setOurIdentityKey(sessionRecord.getSessionState().getPendingKeyExchangeIdentityKey())
              .setTheirBaseKey(message.getBaseKey())
              .setTheirRatchetKey(message.getRatchetKey())
              .setTheirIdentityKey(message.getIdentityKey());

    if (!sessionRecord.isFresh()) sessionRecord.archiveCurrentState();

    RatchetingSession.initializeSession(sessionRecord.getSessionState(),
                                        Math.min(message.getMaxVersion(), CiphertextMessage.CURRENT_VERSION),
                                        parameters.create());

    if (sessionRecord.getSessionState().getSessionVersion() >= 3 &&
        !Curve.verifySignature(message.getIdentityKey().getPublicKey(),
                               message.getBaseKey().serialize(),
                               message.getBaseKeySignature()))
    {
      throw new InvalidKeyException("Base key signature doesn't match!");
    }

    sessionStore.storeSession(recipientId, deviceId, sessionRecord);
    identityKeyStore.saveIdentity(recipientId, message.getIdentityKey());

  }

  
  public KeyExchangeMessage process() {
    synchronized (SessionCipher.SESSION_LOCK) {
      try {
        int             sequence         = KeyHelper.getRandomSequence(65534) + 1;
        int             flags            = KeyExchangeMessage.INITIATE_FLAG;
        ECKeyPair       baseKey          = Curve.generateKeyPair();
        ECKeyPair       ratchetKey       = Curve.generateKeyPair();
        IdentityKeyPair identityKey      = identityKeyStore.getIdentityKeyPair();
        byte[]          baseKeySignature = Curve.calculateSignature(identityKey.getPrivateKey(), baseKey.getPublicKey().serialize());
        SessionRecord   sessionRecord    = sessionStore.loadSession(recipientId, deviceId);

        sessionRecord.getSessionState().setPendingKeyExchange(sequence, baseKey, ratchetKey, identityKey);
        sessionStore.storeSession(recipientId, deviceId, sessionRecord);

        return new KeyExchangeMessage(2, sequence, flags, baseKey.getPublicKey(), baseKeySignature,
                                      ratchetKey.getPublicKey(), identityKey.getPublicKey());
      } catch (InvalidKeyException e) {
        throw new AssertionError(e);
      }
    }
  }


}