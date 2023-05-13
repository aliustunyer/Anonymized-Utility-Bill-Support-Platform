package com.project.utilityBills.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.utilityBills.dao.UpdateBeneficiaryDao;
import com.project.utilityBills.entity.Beneficiary;


@Service
public class BasicUpdateBeneficiaryService implements UpdateBeneficiaryService {

    @Autowired
    private UpdateBeneficiaryDao updateBeneficiaryDao;

    @Override
    public Beneficiary updateBeneficiary(int beneficiary_Id, Beneficiary beneficiaryRequest) {
       Beneficiary beneficiary = updateBeneficiaryDao.findById(beneficiary_Id)
                .orElseThrow(() -> new NoSuchElementException("No beneficiary found with id " + beneficiary_Id));

        beneficiary.setFirst_name(beneficiaryRequest.getFirst_name());
        beneficiary.setLast_name(beneficiaryRequest.getLast_name());
        beneficiary.setEmail(beneficiaryRequest.getEmail());
        beneficiary.setPassword(beneficiaryRequest.getPassword());
        beneficiary.setCell_phone(beneficiaryRequest.getCell_phone());

        return updateBeneficiaryDao.save(beneficiary);
    }
}

