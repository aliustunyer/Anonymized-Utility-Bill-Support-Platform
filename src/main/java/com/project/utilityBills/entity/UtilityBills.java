package com.project.utilityBills.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UtilityBills {
  private int billId;
  private int beneficiaryId;
  private UtilityType utilityType;
  private BigDecimal amountDue;
  private LocalDate dueDate;
  private boolean isPaid;
  
  //@JsonIgnore
  //public int getBillId() {
  //return billId;
  //}
}
