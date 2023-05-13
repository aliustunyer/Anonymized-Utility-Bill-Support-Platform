package com.project.utilityBills.service;

import com.project.utilityBills.entity.Payment;
import com.project.utilityBills.entity.PaymentRequest;

public interface PaymentsService {

  Payment createPayment(PaymentRequest paymentRequest);

}
