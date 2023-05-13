package com.project.utilityBills.controller;

import static org.assertj.core.api.Assertions.assertThat;
import com.project.utilityBills.entity.Donor;
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
import com.project.utilityBills.controller.support.CreateDonorTestSupport;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__UtilityBills_Schema.sql",
    "classpath:flyway/migrations/V1.1__UtilityBills_Data.sql"}, 
  config = @SqlConfig(encoding = "utf-8"))
class CreateDonorTest extends CreateDonorTestSupport{
  @Autowired
  private JdbcTemplate jdbcTemplate;
 
  @Test
  void testCreateDonorReturnsSuccess201() {
   // Given a new beneficiary as JSON
    String body = createDonorBody();
    String uri = getBaseUriForNewDonor();
    
    int numRowsDonors = JdbcTestUtils.countRowsInTable(jdbcTemplate, "donors");
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
   
    // When the new beneficiary is sent
    
    ResponseEntity<Donor> response = getRestTemplate().exchange(uri, HttpMethod.POST,
        bodyEntity, Donor.class);
    
    System.out.println(response);
    
   // Then a 201 status is sent 
    assertThat (response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
   
    // And : the returned beneficiary is correct
    assertThat(response.getBody()).isNotNull();
    
    Donor donor = response.getBody();
assertThat(donor.getFirst_name()).isEqualTo("Emel");   
assertThat(donor.getLast_name()).isEqualTo("Ustunyer");
assertThat(donor.getEmail()).isEqualTo("emelustunyer@hotmail.com");
assertThat(donor.getPassword()).isEqualTo("password21");
assertThat(donor.getCell_phone()).isEqualTo("555-223-5564");

assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "donors"))
.isEqualTo(numRowsDonors + 1);
   
  }

  

}
