package com.openchat.imservice.api.messages;

import com.openchat.protocal.util.guava.Optional;
import com.openchat.imservice.api.messages.calls.OpenchatServiceCallMessage;
import com.openchat.imservice.api.messages.multidevice.OpenchatServiceSyncMessage;

public class OpenchatServiceContent {

  private final Optional<OpenchatServiceDataMessage>    message;
  private final Optional<OpenchatServiceSyncMessage>    synchronizeMessage;
  private final Optional<OpenchatServiceCallMessage>    callMessage;
  private final Optional<OpenchatServiceReceiptMessage> readMessage;

  public OpenchatServiceContent() {
    this.message            = Optional.absent();
    this.synchronizeMessage = Optional.absent();
    this.callMessage        = Optional.absent();
    this.readMessage        = Optional.absent();
  }

  public OpenchatServiceContent(OpenchatServiceDataMessage message) {
    this.message            = Optional.fromNullable(message);
    this.synchronizeMessage = Optional.absent();
    this.callMessage        = Optional.absent();
    this.readMessage        = Optional.absent();
  }

  public OpenchatServiceContent(OpenchatServiceSyncMessage synchronizeMessage) {
    this.message            = Optional.absent();
    this.synchronizeMessage = Optional.fromNullable(synchronizeMessage);
    this.callMessage        = Optional.absent();
    this.readMessage        = Optional.absent();
  }

  public OpenchatServiceContent(OpenchatServiceCallMessage callMessage) {
    this.message            = Optional.absent();
    this.synchronizeMessage = Optional.absent();
    this.callMessage        = Optional.of(callMessage);
    this.readMessage        = Optional.absent();
  }

  public OpenchatServiceContent(OpenchatServiceReceiptMessage receiptMessage) {
    this.message            = Optional.absent();
    this.synchronizeMessage = Optional.absent();
    this.callMessage        = Optional.absent();
    this.readMessage        = Optional.of(receiptMessage);
  }

  public Optional<OpenchatServiceDataMessage> getDataMessage() {
    return message;
  }

  public Optional<OpenchatServiceSyncMessage> getSyncMessage() {
    return synchronizeMessage;
  }

  public Optional<OpenchatServiceCallMessage> getCallMessage() {
    return callMessage;
  }

  public Optional<OpenchatServiceReceiptMessage> getReceiptMessage() {
    return readMessage;
  }
}
