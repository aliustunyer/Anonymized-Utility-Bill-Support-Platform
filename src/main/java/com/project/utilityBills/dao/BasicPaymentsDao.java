package com.project.utilityBills.dao;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.ResultSet;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.Donor;
import com.project.utilityBills.entity.Payment;
import com.project.utilityBills.entity.PaymentType;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;


@Component

public class BasicPaymentsDao implements PaymentsDao {
  
  @Autowired 
  private NamedParameterJdbcTemplate jdbcTemplate;
  
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
    public Optional<Beneficiary> fetchBeneficiary(int beneficiary_id) {
      
      String sql = ""
          + "SELECT * "
          + "FROM beneficiaries "
          + "WHERE beneficiary_id = :beneficiary_id";
     
      Map<String, Object> params =new HashMap<>();
      params.put("beneficiary_id", beneficiary_id);
      
        return Optional.ofNullable(
            jdbcTemplate.query(sql, params, new BeneficiaryResultSetExtractor()));
          
    }
        class BeneficiaryResultSetExtractor implements ResultSetExtractor<Beneficiary> {
          @Override
          public Beneficiary extractData(ResultSet rs) 
              throws SQLException, DataAccessException {
            rs.next();

            // @formatter:off
            return Beneficiary.builder()
                .beneficiary_id(rs.getInt("beneficiary_id"))
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
        public Optional <UtilityBills> fetchUtilityBills(int bill_id) {
          String sql = ""
              + "SELECT * "
              + "FROM utility_bills "
              + "WHERE bill_id = :bill_id";
         
          Map<String, Object> params =new HashMap<>();
          params.put("bill_id", bill_id);
          
            return Optional.ofNullable(
                jdbcTemplate.query(sql, params, new BillResultSetExtractor()));
              
        }
            class BillResultSetExtractor implements ResultSetExtractor<UtilityBills> {
              @Override
              public UtilityBills extractData(ResultSet rs) 
                  throws SQLException, DataAccessException {
                rs.next();

                // @formatter:off
                return UtilityBills.builder()
                    .billId(rs.getInt("bill_id"))
                    .beneficiaryId(rs.getInt("beneficiary_id"))
                    .utilityType(UtilityType.valueOf(rs.getString("utility_type")))
                    .amountDue(rs.getBigDecimal("amount_due"))
                    .dueDate(rs.getDate("due_date").toLocalDate())
                    .isPaid(rs.getBoolean("is_paid"))
                    .build();
                // @formatter:on
              }
            }
     @Override
     public Payment savePayment(LocalDate payment_date, Donor donor, 
         Beneficiary beneficiary,UtilityBills utilityBills, PaymentType paymentType) {
           
         SqlParams params = 
             generateInsertSql(payment_date, donor, beneficiary, utilityBills, paymentType);
     
         KeyHolder keyHolder = new GeneratedKeyHolder();
         jdbcTemplate.update(params.sql, params.source, keyHolder);
         int paymentId = Objects.requireNonNull(keyHolder.getKey()).intValue();
         
         // Updates the utilityBill as paid in the utilityBills Table
         updateUtilityBillAsPaid (utilityBills);
         
         //formatter : off
         
         return Payment.builder()
             .payment_id(paymentId)
             .payment_date(payment_date)
             .donor(donor)
             .utilityBills (utilityBills)
             .beneficiary(beneficiary)
             .paymentType(paymentType)
             .build();
         //formatter :on
            }
     private SqlParams generateInsertSql(LocalDate payment_date, Donor donor, Beneficiary beneficiary, UtilityBills utilityBills, PaymentType paymentType) {
       //formatter : off
       String sql = ""
           + "INSERT INTO payments ("
           + "payment_date, donor_id, beneficiary_id, bill_id, payment_type"
           + ") VALUES ("
           + ":payment_date, :donor_id, :beneficiary_id, :bill_id, :payment_type"
           + ")";
       //formatter : on
       SqlParams params = new SqlParams();
       params.sql = sql;
       params.source.addValue("payment_date", payment_date);
       params.source.addValue("donor_id", donor.getDonor_id());
       params.source.addValue("beneficiary_id", beneficiary.getBeneficiary_id());
       params.source.addValue("bill_id", utilityBills.getBillId());
       params.source.addValue("payment_type", paymentType.toString()); // Added new line

       return params; 
   }
     
     private void updateUtilityBillAsPaid(UtilityBills utilityBills) {
       String sql = "UPDATE utility_bills SET is_paid = :is_paid WHERE bill_id = :bill_id";

       MapSqlParameterSource parameters = new MapSqlParameterSource();
       parameters.addValue("is_paid", true);
       parameters.addValue("bill_id", utilityBills.getBillId());

       jdbcTemplate.update(sql, parameters);
   }
    class SqlParams {
       String sql;
       MapSqlParameterSource source = new MapSqlParameterSource();
     }
        }
     
