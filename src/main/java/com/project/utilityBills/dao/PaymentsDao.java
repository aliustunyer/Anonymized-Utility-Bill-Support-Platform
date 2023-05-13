package com.project.utilityBills.dao;

import java.time.LocalDate;
import java.util.Optional;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.Donor;
import com.project.utilityBills.entity.Payment;

import com.project.utilityBills.entity.UtilityBills;

public interface PaymentsDao {

  Optional<Donor> fetchDonor(int donor_id);

  Optional<Beneficiary> fetchBeneficiary(int beneficiary_id);

  Optional<UtilityBills> fetchUtilityBills(int bill_id);

  Payment savePayment(LocalDate payment_date, Donor donor, Beneficiary beneficiary,
      UtilityBills utilityBills);

}
