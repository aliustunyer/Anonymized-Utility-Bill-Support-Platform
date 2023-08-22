package com.project.utilityBills.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonorRequest {
  
  @NotNull
  @Size(max = 25)
  @Pattern (regexp ="[\\w\\s]*")
  private String first_name;
  
  
  @NotNull
  @Pattern (regexp ="[\\w\\s]*")
  private String last_name;
  
  
  @NotNull
  private String email;
  
  
  @NotNull
  @Pattern (regexp ="[\\w\\s]*")
  private String password;
  
  
  @NotNull
  @Pattern (regexp ="[\\w\\s]*")
  private String cell_phone;

}
