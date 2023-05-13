package com.project.utilityBills.service;

import javax.validation.Valid;
import com.project.utilityBills.entity.Beneficiary;

public interface UpdateBeneficiaryService {

  Beneficiary updateBeneficiary(int beneficiary_Id, @Valid Beneficiary beneficiaryRequest);

}
