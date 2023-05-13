package com.project.utilityBills.controller.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.utilityBills.entity.Beneficiary;

public class UpdateBeneficiaryTestSupport extends baseTest {

    protected String updateBeneficiaryBody() throws JsonProcessingException {
      Beneficiary beneficiary = Beneficiary.builder()
        .first_name("JaneUpdated")
        .last_name("DoeUpdated")
        .email ("janeupdated.doeupdated@example.com")
        .password("password2updated")
        .cell_phone("555-987-6543updated")
        .build();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(beneficiary);
    }
}