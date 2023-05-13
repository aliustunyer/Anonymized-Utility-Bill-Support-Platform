package com.project.utilityBills.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import com.project.utilityBills.controller.support.fetchBeneficiariesTestSupport;
import com.project.utilityBills.controller.support.fetchUtilityBillsTestSupport;
import com.project.utilityBills.entity.UtilityType;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.UtilityBills;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__UtilityBills_Schema.sql",
    "classpath:flyway/migrations/V1.1__UtilityBills_Data.sql"}, 
  config = @SqlConfig(encoding = "utf-8"))

class fetchBeneficiariesTest extends fetchBeneficiariesTestSupport {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Disabled
  @Test
  void testDb() {
    int numRows =JdbcTestUtils.countRowsInTable(jdbcTemplate, "beneficiaries");
    System.out.println("num=" + numRows);
  }
  
  @Test
  void testThatBeneficiariesAreReturnedWhenAValidLastNameIsSupplied() {
    //Given: a Valid Last Name and URI
    String lastName = "Doe";
    
    String uri = 
          String.format("%s?lastName=%s", 
                               getBaseUriForFetchBeneficiaries(), lastName);
    //When: a connection is made to the URI
    ResponseEntity<List<Beneficiary>> response =
        getRestTemplate().exchange(uri, HttpMethod.GET, null, 
            new ParameterizedTypeReference<>() {});  
    
    //Then: a success (OK-200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    // And : the actual list returned is the same as the expected list
    List <Beneficiary> expected = buildExpected();
    List<Beneficiary> actual = response.getBody();
    
    actual.forEach(Beneficiary -> Beneficiary.setBeneficiary_id(0));
    System.out.println(expected);
    System.out.println(actual);
    assertThat(actual).isEqualTo(expected);
  }
}
