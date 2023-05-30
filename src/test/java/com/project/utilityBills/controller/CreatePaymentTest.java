package com.project.utilityBills.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.project.utilityBills.controller.support.CreatePaymentTestSupport;
import com.project.utilityBills.dao.PaymentsDao;
import com.project.utilityBills.entity.Payment;
import com.project.utilityBills.entity.UtilityBills;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__UtilityBills_Schema.sql",
    "classpath:flyway/migrations/V1.1__UtilityBills_Data.sql"}, 
  config = @SqlConfig(encoding = "utf-8"))
class CreatePaymentTest extends CreatePaymentTestSupport {
  
  @Autowired
  private PaymentsDao paymentsDao;
  @Test
  void testCreatePaymentReturnsSuccess201() {
   // Given an payment as JSON
    String body = createPaymentBody();
    String uri = getBaseUriForPayments();
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
   
    // When the payment is sent
    
    ResponseEntity<Payment> response = getRestTemplate().exchange(uri, HttpMethod.POST,
        bodyEntity, Payment.class);
    
    System.out.println(response);// to see if the payment_id is given
   
    // Then a 201 status is sent 
    assertThat (response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
   
    // And : the returned payment is correct
    assertThat(response.getBody()).isNotNull();
    
    Payment payment = response.getBody();
    assertThat(payment.getPayment_date()).isEqualTo("2023-06-10");   
    assertThat(payment.getDonor().getDonor_id()).isEqualTo(6);
    assertThat(payment.getUtilityBills().getBillId()).isEqualTo(8);
    assertThat(payment.getBeneficiary().getBeneficiary_id()).isEqualTo(8);

     // Fetch the utility bill after the payment is made
     UtilityBills fetchedBill = paymentsDao.fetchUtilityBills(payment.getUtilityBills().getBillId()).orElse(null);

     // Assert that the fetched bill is not null
     assertNotNull(fetchedBill);

     // Assert that the isPaid field in the fetched UtilityBill object is true
     assertTrue(fetchedBill.isPaid());
    
  }

  

}
