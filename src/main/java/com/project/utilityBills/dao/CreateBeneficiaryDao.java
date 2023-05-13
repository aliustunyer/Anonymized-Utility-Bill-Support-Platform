package com.project.utilityBills.dao;

import com.project.utilityBills.entity.Beneficiary;

public interface CreateBeneficiaryDao {

  Beneficiary saveNewBeneficiary(String first_name, String last_name, String email, String password,
      String cell_phone);

  

}
