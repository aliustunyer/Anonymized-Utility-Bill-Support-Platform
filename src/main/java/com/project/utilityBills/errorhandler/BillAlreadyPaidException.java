package com.project.utilityBills.errorhandler;

public class BillAlreadyPaidException extends RuntimeException {
  public BillAlreadyPaidException(String message) {
    super(message);
  }
}
