package com.project.utilityBills.entity;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Payment {
  private int payment_id;
  private LocalDate payment_date;
  private Donor donor;
  private UtilityBills utilityBills;
  private Beneficiary beneficiary;
}
