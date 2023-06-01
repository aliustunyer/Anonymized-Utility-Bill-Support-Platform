package com.project.utilityBills.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.project.utilityBills.entity.DonationRequest;
import com.project.utilityBills.entity.Donations;



import com.project.utilityBills.service.CreateDonationService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicCreateDonationController implements CreateDonationController {
  @Autowired
  private CreateDonationService createDonationService;
  @Override
  public Donations createDonation(@Valid DonationRequest donationRequest) {
    log.debug("Donation={}", donationRequest);
    return createDonationService.createDonation(donationRequest);
  }

}
