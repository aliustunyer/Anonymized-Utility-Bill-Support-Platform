package com.project.utilityBills.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.project.utilityBills.entity.Beneficiary;


import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BasicFetchBeneficiaryDao implements FetchBeneficiaryDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Beneficiary> fetchBeneficiaries(String lastName) {
    log.debug("DAO: lastName={}", lastName);
    
  //@formatter: off
    String sql = ""
        + "SELECT * "
        + "FROM beneficiaries "
        + "WHERE last_name = :last_name";
    //@formatter: on
    
    Map<String, Object> params = new HashMap<>();
    params.put("last_name", lastName);
    
    
    return jdbcTemplate.query(sql, params,new RowMapper<> () {

      @Override
      public Beneficiary mapRow(ResultSet rs, int rowNum) throws SQLException {
        //formatter:on
        return Beneficiary.builder()
            .first_name(rs.getString("first_name"))
            .last_name(rs.getString("last_name"))
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .cell_phone(rs.getString("cell_phone"))
            .build();
      
        //formatter :off
      }
      });  
  }

}
