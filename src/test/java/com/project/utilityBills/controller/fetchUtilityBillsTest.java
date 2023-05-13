package com.project.utilityBills.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
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
import com.project.utilityBills.controller.support.fetchUtilityBillsTestSupport;
import com.project.utilityBills.entity.UtilityType;
import com.project.utilityBills.entity.UtilityBills;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__UtilityBills_Schema.sql",
    "classpath:flyway/migrations/V1.1__UtilityBills_Data.sql"}, 
  config = @SqlConfig(encoding = "utf-8"))

class fetchUtilityBillsTest extends fetchUtilityBillsTestSupport {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Disabled
  @Test
  void testDb() {
    int numRows =JdbcTestUtils.countRowsInTable(jdbcTemplate, "utility_bills");
    System.out.println("num=" + numRows);
  }
  
  @Test
  void testThatUtilityBillsAreReturnedWhenAValidUtilityTypeAndAmountDueAreSupplied() {
    //Given: a Valid UtilityType, AmountDue and URI
    UtilityType utilityType = UtilityType.electricity;
    BigDecimal amountDue = BigDecimal.valueOf(75.00);
    
    String uri = 
          String.format("%s?utilityType=%s&amountDue=%s", 
                               getBaseUriForUtilityBills(), utilityType, amountDue);
    //When: a connection is made to the URI
    ResponseEntity<List<UtilityBills>> response =
        getRestTemplate().exchange(uri, HttpMethod.GET, null, 
            new ParameterizedTypeReference<>() {});  
    
    //Then: a success (OK-200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    // And : the actual list returned is the same as the expected list
    List <UtilityBills> expected = buildExpected();
    List<UtilityBills> actual = response.getBody();
    
    actual.forEach(UtilityBills -> UtilityBills.setBillId(0));
    System.out.println(expected);
    System.out.println(actual);
    assertThat(actual).isEqualTo(expected);
  }
}
