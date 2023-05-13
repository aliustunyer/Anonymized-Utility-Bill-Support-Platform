package com.project.utilityBills.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import com.project.utilityBills.entity.UtilityType;
import lombok.Getter;

public class baseTest {
  @Autowired
  @Getter
  private TestRestTemplate restTemplate;
  @LocalServerPort
  private int serverPort;
  
  protected String getBaseUriForUtilityBills() {
    return String.format("http://localhost:%d/UtilityBills", serverPort); 
    
   }
  protected String getBaseUriForPayments() {
    return String.format("http://localhost:%d/Payments", serverPort); 
    
   }
  protected String getBaseUriForNewBeneficiary() {
    return String.format("http://localhost:%d/Beneficiary", serverPort); 
    
   }
  protected String  getBaseUriForFetchBeneficiaries() {
    return String.format("http://localhost:%d/FetchBeneficiary", serverPort); 
    
   }
  protected String getBaseUriForNewDonor() {
    return String.format("http://localhost:%d/Donor", serverPort); 
    
   }

  protected String getBaseUriDeleteBeneficiary(int beneficiary_Id) {
    // TODO Auto-generated method stub
    return String.format("http://localhost:%d/DeleteBeneficiary/%d", serverPort, beneficiary_Id); 
  }
  
  protected String getBaseUriForUpdateBeneficiary(int beneficiary_Id) {
    return String.format("http://localhost:%d/Beneficiary/%d", serverPort, beneficiary_Id); 
  }
  }


