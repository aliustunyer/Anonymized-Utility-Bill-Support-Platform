package com.project.utilityBills.dao;

import com.project.utilityBills.entity.Beneficiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class BasicUpdateBeneficiaryDao implements UpdateBeneficiaryDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Beneficiary> findById(int beneficiary_Id) {
      
        String sql = "SELECT * FROM beneficiaries WHERE beneficiary_id = :beneficiary_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("beneficiary_id", beneficiary_Id);
        try {
          Beneficiary beneficiary = jdbcTemplate.queryForObject(sql, params, new BeneficiaryRowMapper());
          return Optional.ofNullable(beneficiary);
      } catch (EmptyResultDataAccessException e) {
          return Optional.empty();
      }
  }

    @Override
    public Beneficiary save(Beneficiary beneficiary) {
        String sql = "UPDATE beneficiaries SET first_name = :first_name, last_name = :last_name, email = :email, " +
                "password = :password, cell_phone = :cell_phone WHERE beneficiary_id = :beneficiary_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("first_name", beneficiary.getFirst_name());
        params.addValue("last_name", beneficiary.getLast_name());
        params.addValue("email", beneficiary.getEmail());
        params.addValue("password", beneficiary.getPassword());
        params.addValue("cell_phone", beneficiary.getCell_phone());
        params.addValue("beneficiary_id", beneficiary.getBeneficiary_id());
        jdbcTemplate.update(sql, params);
        return beneficiary;
    }
    
    class BeneficiaryRowMapper implements RowMapper<Beneficiary> {
        @Override
        public Beneficiary mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Beneficiary.builder()
                .beneficiary_id(rs.getInt("beneficiary_id"))
                .first_name(rs.getString("first_name"))
                .last_name(rs.getString("last_name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .cell_phone(rs.getString("cell_phone"))
                .build();
        }
    }
}
