package com.project.utilityBills.dao;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.project.utilityBills.dao.BasicPaymentsDao.DonorResultSetExtractor;
import com.project.utilityBills.entity.Donations;
import com.project.utilityBills.entity.Donor;

@Component
public class BasicCreateDonationDao implements CreateDonationDao {
  
  @Autowired 
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public Optional<Donor> fetchDonor(int donor_id) {

    String sql = ""
        + "SELECT * "
        + "FROM donors "
        + "WHERE donor_id = :donor_id";
   
    Map<String, Object> params =new HashMap<>();
    params.put("donor_id", donor_id);
    
      return Optional.ofNullable(
          jdbcTemplate.query(sql, params, new DonorResultSetExtractor()));
        
  }
      class DonorResultSetExtractor implements ResultSetExtractor<Donor> {
        @Override
        public Donor extractData(ResultSet rs) 
            throws SQLException, DataAccessException {
          rs.next();

          // @formatter:off
          return Donor.builder()
              .donor_id(rs.getInt("donor_id"))
              .first_name(rs.getString("first_name"))
              .last_name(rs.getString("last_name"))
              .email(rs.getString("email"))
              .password(rs.getString("password"))
              .cell_phone(rs.getString("cell_phone"))
              .build();
          // @formatter:on
        }
     }
      
  @Override
  public Donations saveNewDonation(Donor donor, 
      BigDecimal amountDonated, LocalDate donationDate) {
        
      SqlParams params = 
          generateInsertSql(donor, amountDonated, donationDate);
      
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(params.sql, params.source, keyHolder);
      int donationId = Objects.requireNonNull(keyHolder.getKey()).intValue();
      
      //formatter : off
      return Donations.builder()
          .donationId(donationId) // Add the auto-generated donationId here
          .donor(donor)
          .amountDonated(amountDonated)
          .donationDate(donationDate)
          .build();
      //formatter :on
  }
   
  private SqlParams generateInsertSql(Donor donor, 
      BigDecimal amountDonated, LocalDate donationDate) {
   //formatter : off
    String sql = ""
        + "INSERT INTO donations ("
        + "donor_id, amount_donated, donation_date"
        + ") VALUES ("
        + ":donor_id, :amount_donated, :donation_date"
        + ")";
    //formatter : on
   SqlParams params = new SqlParams();
   params.sql = sql;
   
   params.source.addValue("donor_id", donor.getDonor_id());
   params.source.addValue("amount_donated", amountDonated);
   params.source.addValue("donation_date", donationDate);
   
   
   return params;
 }
 class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
     }
  


