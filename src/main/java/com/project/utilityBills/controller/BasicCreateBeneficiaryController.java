package com.project.utilityBills.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.BeneficiaryRequest;
import com.project.utilityBills.service.CreateBeneficiaryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicCreateBeneficiaryController implements CreateBeneficiaryController {
  @Autowired
  private CreateBeneficiaryService createBeneficiaryService;
  @Override
  public Beneficiary createBeneficiary(@Valid BeneficiaryRequest beneficiaryRequest) {
    log.debug("Beneficiary={}", beneficiaryRequest);
    return createBeneficiaryService.createBeneficiary(beneficiaryRequest);
  }

}
