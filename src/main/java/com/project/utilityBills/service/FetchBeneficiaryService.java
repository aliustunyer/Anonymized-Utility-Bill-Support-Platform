package com.project.utilityBills.service;

import java.math.BigDecimal;
import java.util.List;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;

public interface FetchBeneficiaryService {

  List<Beneficiary> fetchBeneficiaries(String lastName);

}
