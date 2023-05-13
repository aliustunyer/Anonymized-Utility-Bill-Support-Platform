package com.project.utilityBills.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.service.UpdateBeneficiaryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicUpdateBeneficiaryController implements UpdateBeneficiaryController {
  @Autowired
  private UpdateBeneficiaryService updateBeneficiaryService;

  @Override
  public Beneficiary updateBeneficiary(int beneficiary_Id, @Valid Beneficiary beneficiaryRequest) {
    log.debug("Updating Beneficiary Id={}, Request={}", beneficiary_Id, beneficiaryRequest);
    return updateBeneficiaryService.updateBeneficiary(beneficiary_Id, beneficiaryRequest);
  }
}