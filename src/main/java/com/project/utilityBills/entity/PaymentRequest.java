package com.project.utilityBills.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
@Data
public class PaymentRequest {
  
  
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate payment_date;
  
  @Positive
  private int donor_id;
  
  @Positive
  private int bill_id;
  
  @Positive
  private int beneficiary_id;

}
