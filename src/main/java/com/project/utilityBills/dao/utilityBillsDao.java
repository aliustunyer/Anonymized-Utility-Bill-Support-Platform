package com.project.utilityBills.dao;

import java.math.BigDecimal;
import java.util.List;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;

public interface utilityBillsDao {

  List<UtilityBills> fetchUtilityBills(UtilityType utilityType, BigDecimal amountDue);

}
