package com.project.utilityBills.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import com.project.utilityBills.entity.Donor;
import com.project.utilityBills.entity.DonorRequest;

import com.project.utilityBills.service.CreateDonorService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicCreateDonorController implements CreateDonorController {
  @Autowired
  private CreateDonorService createDonorService;
  @Override
  public Donor createDonor(@Valid DonorRequest donorRequest) {
    log.debug("Donor={}", donorRequest);
    return createDonorService.createDonor(donorRequest);
  }

}
