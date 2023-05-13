package com.project.utilityBills.dao;


import java.util.List;
import com.project.utilityBills.entity.Beneficiary;



public interface FetchBeneficiaryDao {

  List<Beneficiary> fetchBeneficiaries(String lastName);

}
