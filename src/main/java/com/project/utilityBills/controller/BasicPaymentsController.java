package com.project.utilityBills.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.project.utilityBills.entity.Payment;
import com.project.utilityBills.entity.PaymentRequest;
import com.project.utilityBills.service.PaymentsService;
import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class BasicPaymentsController implements PaymentsController {
  @Autowired
  private PaymentsService paymentsService;
  @Override
  public Payment createPayment(PaymentRequest paymentRequest) {
    log.debug("Payment={}", paymentRequest);
    return paymentsService.createPayment(paymentRequest);
  }

}
