package com.project.utilityBills.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.project.utilityBills.service.DeleteBeneficiaryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicDeleteBeneficiaryController implements DeleteBeneficiaryController {
  @Autowired
  private DeleteBeneficiaryService deleteBeneficiaryService;

  @Override
  public void deleteBeneficiary(int beneficiary_Id) {
    log.debug("Deleting Beneficiary Id={}", beneficiary_Id);
    deleteBeneficiaryService.deleteBeneficiary(beneficiary_Id);
  }
}
