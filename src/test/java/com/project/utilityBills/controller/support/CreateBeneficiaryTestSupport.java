package com.project.utilityBills.controller.support;

public class CreateBeneficiaryTestSupport extends baseTest {
  protected String createBeneficiaryBody() {
    //formatter : off
     return "{ \"first_name\":\"Ali\"," +
     "\"last_name\":\"Ustunyer\"," +
     "\"email\":\"aliustunyer@hotmail.com\"," +
     "\"password\":\"password11\"," +
     "\"cell_phone\":\"555-223-5563\"" +
     "}";
     //formatter : on
  }
}
