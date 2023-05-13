package com.project.utilityBills.dao;


import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.project.utilityBills.entity.Beneficiary;

@Component
public class BasicCreateBeneficiaryDao implements CreateBeneficiaryDao {
  @Autowired 
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public Beneficiary saveNewBeneficiary(String first_name, String last_name, String email, String password,
        String cell_phone) {
        
      SqlParams params = 
          generateInsertSql(first_name, last_name, email, password, cell_phone);
      
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(params.sql, params.source, keyHolder);
      int donorId = Objects.requireNonNull(keyHolder.getKey()).intValue();
      
      //formatter : off
      return Beneficiary.builder()
          .beneficiary_id(donorId)
          .first_name(first_name)
          .last_name(last_name)
          .email (email)
          .password(password)
          .cell_phone(cell_phone)
          .build();
      //formatter :on
}   
  private SqlParams generateInsertSql(String first_name, String last_name, String email, String password,
      String cell_phone) {
   //formatter : off
    String sql = ""
        + "INSERT INTO beneficiaries ("
        + "first_name, last_name, email, password, cell_phone"
        + ") VALUES ("
        + ":first_name, :last_name, :email, :password, :cell_phone"
        + ")";
    //formatter : on
   SqlParams params = new SqlParams();
   params.sql = sql;
   params.source.addValue("first_name", first_name);
   params.source.addValue("last_name", last_name);
   params.source.addValue("email", email);
   params.source.addValue("password", password);
   params.source.addValue("cell_phone", cell_phone);
   
   return params;
 }
 class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
     }
  


