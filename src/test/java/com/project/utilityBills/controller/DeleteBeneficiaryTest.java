package com.project.utilityBills.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.project.utilityBills.controller.support.DeleteBeneficiaryTestSupport;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__UtilityBills_Schema.sql",
    "classpath:flyway/migrations/V1.1__UtilityBills_Data.sql"}, 
  config = @SqlConfig(encoding = "utf-8"))
class DeleteBeneficiaryTest extends DeleteBeneficiaryTestSupport {
  @Autowired
  private JdbcTemplate jdbcTemplate;
 
  @Test
  
  void testDeleteBeneficiaryReturnsSuccess200() {
      // Given an existing beneficiary
      int beneficiary_Id = 2; // ID of Jane Doe data set
      String uri = getBaseUriDeleteBeneficiary(beneficiary_Id);

      // Check if the beneficiary exists before deletion
      int initialCount = jdbcTemplate.queryForObject("SELECT count(*) FROM beneficiaries WHERE beneficiary_id = ?", Integer.class, beneficiary_Id);
      assertThat(initialCount).isEqualTo(1);

      // When a delete request is sent
      ResponseEntity<Void> response = getRestTemplate().exchange(uri, HttpMethod.DELETE, null, Void.class);

      // Then a 200 status is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

      // And the beneficiary no longer exists in the database
      int countAfterDeletion = jdbcTemplate.queryForObject("SELECT count(*) FROM beneficiaries WHERE beneficiary_id = ?", Integer.class, beneficiary_Id);
      assertThat(countAfterDeletion).isEqualTo(0);
  }
  @Test
  void testDeleteNonExistentBeneficiaryReturnsNotFound404() {
      // Given a non-existent beneficiary
      int beneficiary_Id = 999; // Non-existent ID
      String uri = getBaseUriDeleteBeneficiary(beneficiary_Id);

      // Check if the beneficiary exists before deletion
      int initialCount = jdbcTemplate.queryForObject("SELECT count(*) FROM beneficiaries WHERE beneficiary_id = ?", Integer.class, beneficiary_Id);
      assertThat(initialCount).isEqualTo(0);

      // When a delete request is sent
      ResponseEntity<Void> response = getRestTemplate().exchange(uri, HttpMethod.DELETE, null, Void.class);

      // Then a 404 status is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
 
}