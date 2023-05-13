package com.project.utilityBills.controller;

import static org.assertj.core.api.Assertions.assertThat;
import com.project.utilityBills.entity.Beneficiary;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import com.project.utilityBills.controller.support.CreateBeneficiaryTestSupport;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__UtilityBills_Schema.sql",
    "classpath:flyway/migrations/V1.1__UtilityBills_Data.sql"}, 
  config = @SqlConfig(encoding = "utf-8"))
class CreateBeneficiaryTest extends CreateBeneficiaryTestSupport{
  @Autowired
  private JdbcTemplate jdbcTemplate;
 
  @Test
  void testCreateBeneficiaryReturnsSuccess201() {
   // Given a new beneficiary as JSON
    String body = createBeneficiaryBody();
    String uri = getBaseUriForNewBeneficiary();
    
    int numRowsBeneficiaries = JdbcTestUtils.countRowsInTable(jdbcTemplate, "beneficiaries");
    System.out.println(numRowsBeneficiaries);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
   
    // When the new beneficiary is sent
    
    ResponseEntity<Beneficiary> response = getRestTemplate().exchange(uri, HttpMethod.POST,
        bodyEntity, Beneficiary.class);
    
    System.out.println(response);
    
   // Then a 201 status is sent 
    assertThat (response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
   
    // And : the returned beneficiary is correct
    assertThat(response.getBody()).isNotNull();
    
    Beneficiary beneficiary = response.getBody();
assertThat(beneficiary.getFirst_name()).isEqualTo("Ali");   
assertThat(beneficiary.getLast_name()).isEqualTo("Ustunyer");
assertThat(beneficiary.getEmail()).isEqualTo("aliustunyer@hotmail.com");
assertThat(beneficiary.getPassword()).isEqualTo("password11");
assertThat(beneficiary.getCell_phone()).isEqualTo("555-223-5563");

assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "beneficiaries"))
.isEqualTo(numRowsBeneficiaries + 1);
int numRowsBeneficiariess = JdbcTestUtils.countRowsInTable(jdbcTemplate, "beneficiaries");
System.out.println(numRowsBeneficiariess);
  }

  

}
