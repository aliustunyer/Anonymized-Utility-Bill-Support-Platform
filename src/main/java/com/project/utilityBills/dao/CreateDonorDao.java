package com.project.utilityBills.dao;

import com.project.utilityBills.entity.Donor;

public interface CreateDonorDao {

  Donor saveNewDonor(String first_name, String last_name, String email, String password,
      String cell_phone);

  

}
