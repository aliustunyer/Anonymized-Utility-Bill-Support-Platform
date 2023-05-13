package com.project.utilityBills.errorhandler;

public class BillAlreadyPaidException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  public BillAlreadyPaidException(String message) {
    super(message);
  }
}
