package com.project.utilityBills.service;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.utilityBills.dao.CreateBeneficiaryDao;
import com.project.utilityBills.dao.CreateDonorDao;
import com.project.utilityBills.dao.PaymentsDao;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.BeneficiaryRequest;
import com.project.utilityBills.entity.Donor;
import com.project.utilityBills.entity.DonorRequest;

@Service
public class BasicCreateDonorService implements CreateDonorService {
  @Autowired
  private CreateDonorDao createDonorDao;
  @Transactional
  @Override
  public Donor createDonor(@Valid DonorRequest donorRequest) {
    
    String first_name = donorRequest.getFirst_name();
    String last_name  = donorRequest.getLast_name();
    String email      = donorRequest.getEmail();
    String password   = donorRequest.getPassword();
    String cell_phone = donorRequest.getCell_phone();
    
    return createDonorDao.saveNewDonor(first_name, last_name, email, password, cell_phone);
  }

  }
