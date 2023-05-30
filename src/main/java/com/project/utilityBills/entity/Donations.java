package com.project.utilityBills.entity;

import lombok.Builder;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@Builder

public class Donations {

    
    private Integer donationId;
    private Donor donor;
    private BigDecimal amountDonated;
    private LocalDate donationDate;

}
