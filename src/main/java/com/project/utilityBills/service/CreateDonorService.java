package com.project.utilityBills.service;

import javax.validation.Valid;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.BeneficiaryRequest;
import com.project.utilityBills.entity.Donor;
import com.project.utilityBills.entity.DonorRequest;

public interface CreateDonorService {

  Donor createDonor(@Valid DonorRequest donorRequest);

}
