package com.project.utilityBills.service;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.utilityBills.dao.CreateBeneficiaryDao;
import com.project.utilityBills.dao.PaymentsDao;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.BeneficiaryRequest;

@Service
public class BasicCreateBeneficiaryService implements CreateBeneficiaryService {
  @Autowired
  private CreateBeneficiaryDao createBeneficiaryDao;
  @Transactional
  @Override
  public Beneficiary createBeneficiary(@Valid BeneficiaryRequest beneficiaryRequest) {
    
    String first_name = beneficiaryRequest.getFirst_name();
    String last_name  = beneficiaryRequest.getLast_name();
    String email      = beneficiaryRequest.getEmail();
    String password   = beneficiaryRequest.getPassword();
    String cell_phone = beneficiaryRequest.getCell_phone();
    
    return createBeneficiaryDao.saveNewBeneficiary(first_name, last_name, email, password, cell_phone);
  }

  }
