package com.openchat.websocket.messages;

public class InvalidMessageException extends Exception {
  public InvalidMessageException(String s) {
    super(s);
  }

  public InvalidMessageException(Exception e) {
    super(e);
  }
}
