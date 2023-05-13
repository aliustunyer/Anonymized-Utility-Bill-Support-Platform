package com.project.utilityBills.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Donor {
  
  private int donor_id;
  private String first_name;
  private String last_name;
  private String email;
  private String password;
  private String cell_phone;

}
