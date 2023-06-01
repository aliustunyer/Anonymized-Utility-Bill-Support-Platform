package com.project.utilityBills.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonationRequest {

  @NotNull(message = "Donor ID cannot be null")
  private Integer donor_id;
  
  @NotNull(message = "Donation amount cannot be null")
  @DecimalMin(value = "0.0", inclusive = false, message = "Donation amount must be greater than 0")
  private BigDecimal amountDonated;

  @NotNull(message = "Donation date cannot be null")
  @PastOrPresent(message = "Donation date must be in the past or present")
  private LocalDate donationDate;
}
  


