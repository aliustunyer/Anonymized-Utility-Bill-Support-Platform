package com.project.utilityBills.dao;

import java.util.Optional;
import com.project.utilityBills.entity.Beneficiary;

public interface UpdateBeneficiaryDao {

  Optional <Beneficiary> findById(int beneficiary_Id);

  Beneficiary save(Beneficiary beneficiary);

}
