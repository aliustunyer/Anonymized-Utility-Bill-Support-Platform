package com.project.utilityBills.service;

import javax.validation.Valid;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.BeneficiaryRequest;
import com.project.utilityBills.entity.DonationRequest;
import com.project.utilityBills.entity.Donations;
import com.project.utilityBills.entity.Donor;
import com.project.utilityBills.entity.DonorRequest;

public interface CreateDonationService {

  Donations createDonation(@Valid DonationRequest donationRequest);

}
