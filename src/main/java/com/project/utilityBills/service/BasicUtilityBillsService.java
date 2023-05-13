package com.project.utilityBills.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.utilityBills.dao.utilityBillsDao;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BasicUtilityBillsService implements utilityBillsService {
  @Autowired
  private utilityBillsDao utilityBillsDao;
  
  @Transactional(readOnly = true) 
  @Override
  public List<UtilityBills> fetchUtilityBills(UtilityType utilityType, BigDecimal amountDue) {
    
    log.info("The fetchUtilityBills Method was called with utilityType={} and amountDue={}",
        utilityType, amountDue);
    
    List<UtilityBills> utilityBills = utilityBillsDao.fetchUtilityBills(utilityType, amountDue);
    
    if (utilityBills.isEmpty()) {
      String msg = String.format("No utility bills found with utilityType=%s and amountDue=%s", 
                                  utilityType, amountDue);
      throw new NoSuchElementException(msg);
    }
    return utilityBills;
  }

}
