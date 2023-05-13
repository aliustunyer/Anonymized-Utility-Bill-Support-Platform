package com.project.utilityBills.controller.support;

public class CreateDonorTestSupport extends baseTest {
  protected String createDonorBody() {
    //formatter : off
     return "{ \"first_name\":\"Emel\"," +
     "\"last_name\":\"Ustunyer\"," +
     "\"email\":\"emelustunyer@hotmail.com\"," +
     "\"password\":\"password21\"," +
     "\"cell_phone\":\"555-223-5564\"" +
     "}";
     //formatter : on
  }
}
