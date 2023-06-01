package com.project.utilityBills.controller.support;

public class CreatePaymentTestSupport extends baseTest {
  protected String createPaymentBody() {
    //formatter : off
    return "{ \"payment_date\":\"2023-06-10\"," +
    "\"donor_id\":6," +
    "\"bill_id\":8," +
    "\"beneficiary_id\":8," +
    "\"paymentType\":\"DIRECT\"" +
    "}"; 

     //formatter : on
  }
}
