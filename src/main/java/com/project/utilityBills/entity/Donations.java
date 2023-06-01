package com.project.utilityBills.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Donations {

    
    private Integer donationId;
    private Donor donor;
    private BigDecimal amountDonated;
    private LocalDate donationDate;

}
