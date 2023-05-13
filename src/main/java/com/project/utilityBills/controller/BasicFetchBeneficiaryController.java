package com.project.utilityBills.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import com.project.utilityBills.entity.Beneficiary;


import com.project.utilityBills.service.FetchBeneficiaryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class BasicFetchBeneficiaryController implements FetchBeneficiaryController  {
   
   @Autowired 
   private FetchBeneficiaryService fetchBeneficiaryService;
   
   
   @Override
  public List<Beneficiary> fetchBeneficiaries(String lastName) {
    log.debug("lastName={}", lastName);
    return fetchBeneficiaryService.fetchBeneficiaries(lastName);
  }

}
