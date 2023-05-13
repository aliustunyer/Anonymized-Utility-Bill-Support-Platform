package com.project.utilityBills.service;

import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.utilityBills.dao.DeleteBeneficiaryDao;
import com.project.utilityBills.dao.UpdateBeneficiaryDao;
import com.project.utilityBills.entity.Beneficiary;

@Service
public class BasicDeleteBeneficiaryService implements DeleteBeneficiaryService {

  @Autowired
  private DeleteBeneficiaryDao deleteBeneficiaryDao;
 
  @Autowired
  private UpdateBeneficiaryDao updateBeneficiaryDao;
  @Transactional
  @Override
  public void deleteBeneficiary(int beneficiary_Id) {
    updateBeneficiaryDao.findById(beneficiary_Id)
      .orElseThrow(() -> new NoSuchElementException("No beneficiary found with id " + beneficiary_Id));
    deleteBeneficiaryDao.deleteById(beneficiary_Id);
  }
}
