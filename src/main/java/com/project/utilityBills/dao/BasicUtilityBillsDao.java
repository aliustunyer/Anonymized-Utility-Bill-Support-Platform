package com.project.utilityBills.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketRequesterAutoConfiguration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BasicUtilityBillsDao implements utilityBillsDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<UtilityBills> fetchUtilityBills(UtilityType utilityType, BigDecimal amountDue) {
    log.debug("DAO: utilityType={}, amountDue={}", utilityType, amountDue);
    
  //@formatter: off
    String sql = ""
        + "SELECT * "
        + "FROM utility_bills "
        + "WHERE utility_type = :utility_type AND amount_due = :amount_due";
    //@formatter: on
    
    Map<String, Object> params = new HashMap<>();
    params.put("utility_type", utilityType.toString());
    params.put("amount_due", amountDue.toString());
    
    return jdbcTemplate.query(sql, params,new RowMapper<> () {

      @Override
      public UtilityBills mapRow(ResultSet rs, int rowNum) throws SQLException {
        //formatter:on
        return UtilityBills.builder()
            .billId(rs.getInt("bill_id"))
            .beneficiaryId(rs.getInt("beneficiary_id"))
            .utilityType(UtilityType.valueOf(rs.getString("utility_type")))
            .amountDue(new BigDecimal(rs.getString("amount_due")))
            .dueDate(rs.getDate("due_date").toLocalDate())
            .isPaid(rs.getBoolean("is_paid"))
            .build();
      
        //formatter :off
      }
      });  
  }

}
