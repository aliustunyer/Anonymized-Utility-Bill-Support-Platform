package com.project.utilityBills.controller.support;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;

public class fetchUtilityBillsTestSupport extends baseTest {
  protected List<UtilityBills> buildExpected() {
    List<UtilityBills> list = new LinkedList<>();
    //formatter: off
    list.add(UtilityBills.builder()
       .beneficiaryId(2)
       .utilityType(UtilityType.electricity)
       .amountDue (new BigDecimal("75.00"))
       .dueDate(LocalDate.parse("2023-05-10"))
       .isPaid(true)
       .build());
    
    list.add(UtilityBills.builder()
        .beneficiaryId(5)
        .utilityType(UtilityType.electricity)
        .amountDue (new BigDecimal("75.00"))
        .dueDate(LocalDate.parse("2023-05-25"))
        .isPaid(false)
        .build());
    //formatter : on
    return list;
  }
}
