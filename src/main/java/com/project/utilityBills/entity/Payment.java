package com.project.utilityBills.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  
  @JsonProperty("payment_type")
  private PaymentType paymentType;
}
