package com.project.utilityBills.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.utilityBills.dao.FetchBeneficiaryDao;
import com.project.utilityBills.dao.utilityBillsDao;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BasicFetchBeneficiaryService implements FetchBeneficiaryService {
  @Autowired
  private FetchBeneficiaryDao fetchBeneficiaryDao;
  
  @Transactional(readOnly = true) 
  @Override
  public List<Beneficiary> fetchBeneficiaries(String lastName) {
    
    log.info("The fetchBeneficiaries Method was called with lastName={}",
        lastName);
    
    List<Beneficiary> beneficiary = fetchBeneficiaryDao.fetchBeneficiaries(lastName);
    
    if (beneficiary.isEmpty()) {
      String msg = String.format("No beneficiaries found with lastName=%s", 
                                  lastName);
      throw new NoSuchElementException(msg);
    }
    return beneficiary;
  }

}
