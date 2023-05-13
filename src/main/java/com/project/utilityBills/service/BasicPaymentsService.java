package com.project.utilityBills.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.utilityBills.dao.PaymentsDao;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.Donor;
import com.project.utilityBills.entity.Payment;
import com.project.utilityBills.entity.PaymentRequest;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.errorhandler.BillAlreadyPaidException;

@Service
public class BasicPaymentsService implements PaymentsService {
  @Autowired
  private PaymentsDao paymentsDao;
  
  @Transactional
  @Override
  public Payment createPayment(PaymentRequest paymentRequest) {
    
    LocalDate payment_date = paymentRequest.getPayment_date();
    
    Donor donor = getDonor(paymentRequest);

    Beneficiary beneficiary = getBeneficiary(paymentRequest);
    
    UtilityBills utilityBills = getUtilityBills(paymentRequest);
    
   if (!utilityBills.isPaid()) { // if the bill is not paid
     return paymentsDao.savePayment(payment_date, donor, beneficiary, utilityBills);
    
  }
  else {
     throw new BillAlreadyPaidException("The bill is already paid. "
          + "Please choose a bill that is not paid.");
  }  
  }
  

  protected UtilityBills getUtilityBills(PaymentRequest paymentRequest) {
    return paymentsDao.fetchUtilityBills(
        paymentRequest.getBill_id())
        .orElseThrow(() -> new NoSuchElementException("UtilityBill with ID ="
            + paymentRequest.getBill_id() + " was not found"));
  }

  protected Beneficiary getBeneficiary(PaymentRequest paymentRequest) {
    return paymentsDao.fetchBeneficiary(
           paymentRequest.getBeneficiary_id())
        .orElseThrow(() -> new NoSuchElementException("Beneficiary with ID ="
            + paymentRequest.getBeneficiary_id() + " was not found"));
  }

  protected Donor getDonor(PaymentRequest paymentRequest) {
    return paymentsDao.fetchDonor(paymentRequest.getDonor_id())
        .orElseThrow(() -> new NoSuchElementException("Donor with ID ="
            + paymentRequest.getDonor_id() + " was not found"));
  }

}
