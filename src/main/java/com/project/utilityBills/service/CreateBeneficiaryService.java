package com.project.utilityBills.service;

import javax.validation.Valid;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.BeneficiaryRequest;

public interface CreateBeneficiaryService {

  Beneficiary createBeneficiary(@Valid BeneficiaryRequest beneficiaryRequest);

}
