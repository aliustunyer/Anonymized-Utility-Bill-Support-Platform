package com.project.utilityBills.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import com.project.utilityBills.entity.Donations;
import com.project.utilityBills.entity.Donor;


public interface CreateDonationDao {
  Optional<Donor> fetchDonor(int donor_id);
  public Donations saveNewDonation(Donor donor, 
      BigDecimal amountDonated, LocalDate donationDate);
  

}
