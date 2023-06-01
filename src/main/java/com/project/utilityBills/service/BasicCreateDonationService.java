package com.project.utilityBills.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.utilityBills.dao.CreateDonationDao;
import com.project.utilityBills.entity.DonationRequest;
import com.project.utilityBills.entity.Donations;
import com.project.utilityBills.entity.Donor;



@Service
public class BasicCreateDonationService implements CreateDonationService {
  @Autowired
  private CreateDonationDao createDonationDao;
  @Transactional
  @Override
  public Donations createDonation(@Valid DonationRequest donationRequest) {
    
    
    Donor donor = getDonor(donationRequest);
    BigDecimal amountDonated = donationRequest.getAmountDonated();
    LocalDate donationDate = donationRequest.getDonationDate();

    return createDonationDao.saveNewDonation(donor, amountDonated, donationDate);
    
  }
  private Donor getDonor(@Valid DonationRequest donationRequest) {
    return createDonationDao.fetchDonor(donationRequest.getDonor_id())
        .orElseThrow(() -> new NoSuchElementException("Donation with ID ="
            + donationRequest.getDonor_id() + " was not found"));
    
  }
  }
