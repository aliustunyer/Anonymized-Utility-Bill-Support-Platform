package com.project.utilityBills.dao;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.project.utilityBills.entity.Beneficiary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@Component
public class BasicDeleteBeneficiaryDao implements DeleteBeneficiaryDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void deleteById(int beneficiary_Id) {
        String sql = "DELETE FROM beneficiaries WHERE beneficiary_id = :beneficiary_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("beneficiary_id", beneficiary_Id);
        
        int rowsAffected = jdbcTemplate.update(sql, params);
        
        if (rowsAffected == 0) {
            throw new NoSuchElementException("Beneficiary not found with ID: " + beneficiary_Id);
        }
    }

   
}
