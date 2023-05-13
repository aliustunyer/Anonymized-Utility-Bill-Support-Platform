package com.project.utilityBills.controller.support;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;

public class fetchBeneficiariesTestSupport extends baseTest {
  protected List<Beneficiary> buildExpected() {
    List<Beneficiary> list = new LinkedList<>();
    //formatter: off
    list.add(Beneficiary.builder()
       .first_name("John")
       .last_name("Doe")
       .email ("john.doe@example.com")
       .password("password1")
       .cell_phone("555-123-4567")
       .build());
    
    
    
    list.add(Beneficiary.builder()
        .first_name("Jane")
        .last_name("Doe")
        .email ("jane.doe@example.com")
        .password("password2")
        .cell_phone("555-987-6543")
        .build());
    //formatter : on
    return list;
  }
}
