package com.project.utilityBills.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.utilityBills.controller.support.UpdateBeneficiaryTestSupport;
import com.project.utilityBills.entity.Beneficiary;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__UtilityBills_Schema.sql",
    "classpath:flyway/migrations/V1.1__UtilityBills_Data.sql"}, 
  config = @SqlConfig(encoding = "utf-8"))
class UpdateBeneficiaryTest extends UpdateBeneficiaryTestSupport {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Test
  void testUpdateBeneficiaryReturnsSuccess200() throws JsonProcessingException {
      // Given existing beneficiary details
      int beneficiary_Id = 2; // ID of Jane Doe in the data set
      String body = updateBeneficiaryBody();
      String uri = getBaseUriForUpdateBeneficiary(beneficiary_Id);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);

      // When the updated beneficiary details are sent
      ResponseEntity<Beneficiary> response = getRestTemplate().exchange(uri, HttpMethod.PUT, bodyEntity, Beneficiary.class);

      // Then a 200 status is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

      // And : the returned beneficiary is updated correctly
      assertThat(response.getBody()).isNotNull();

      Beneficiary beneficiary = response.getBody();
      assertThat(beneficiary.getBeneficiary_id()).isEqualTo(2);
      assertThat(beneficiary.getFirst_name()).isEqualTo("JaneUpdated");
      assertThat(beneficiary.getLast_name()).isEqualTo("DoeUpdated");
      assertThat(beneficiary.getEmail()).isEqualTo("janeupdated.doeupdated@example.com");
      assertThat(beneficiary.getPassword()).isEqualTo("password2updated");
      assertThat(beneficiary.getCell_phone()).isEqualTo("555-987-6543updated");
  }
  @Test
  void testUpdateNonExistentBeneficiaryReturnsNotFound404() {
      // Given a non-existent beneficiary
      int beneficiary_Id = 999; // Non-existent ID
      String uri = getBaseUriForUpdateBeneficiary(beneficiary_Id);

      // Check if the beneficiary exists before update
      int initialCount = jdbcTemplate.queryForObject("SELECT count(*) FROM beneficiaries WHERE beneficiary_id = ?", Integer.class, beneficiary_Id);
      assertThat(initialCount).isEqualTo(0);

      // When update request is sent
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      String body = null;
      try {
        body = updateBeneficiaryBody();
      } catch (JsonProcessingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } // Make sure this method returns a valid beneficiary JSON body
      HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
      ResponseEntity<Beneficiary> response = getRestTemplate().exchange(uri, HttpMethod.PUT, bodyEntity, Beneficiary.class);

      // Then a 404 status is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

}
