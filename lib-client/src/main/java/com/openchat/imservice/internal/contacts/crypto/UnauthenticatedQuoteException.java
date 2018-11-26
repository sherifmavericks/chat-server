package com.openchat.imservice.internal.contacts.crypto;

public class UnauthenticatedQuoteException extends Exception {
  public UnauthenticatedQuoteException(String s) {
    super(s);
  }

  public UnauthenticatedQuoteException(Exception nested) {
    super(nested);
  }
}
