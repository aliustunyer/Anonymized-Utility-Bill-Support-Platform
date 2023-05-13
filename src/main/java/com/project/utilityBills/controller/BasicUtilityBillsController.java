package com.project.utilityBills.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;
import com.project.utilityBills.service.utilityBillsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class BasicUtilityBillsController implements UtilityBillsController {
   
   @Autowired 
   private utilityBillsService utilityBillsService;
   
   
   @Override
  public List<UtilityBills> fetchUtilityBills(UtilityType utilityType, BigDecimal amountDue) {
    log.debug("utilityType={}, amountDue={}", utilityType, amountDue);
    return utilityBillsService.fetchUtilityBills(utilityType, amountDue);
  }

}
