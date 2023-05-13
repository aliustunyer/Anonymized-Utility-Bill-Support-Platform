package com.project.utilityBills.controller;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.project.utilityBills.entity.Beneficiary;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;



@RequestMapping("/FetchBeneficiary")
@OpenAPIDefinition(info = @Info(title = "Fetch Beneficiary Support Service"), servers = {
@Server(url = "http://localhost:8080", description = "Local server.")})

public interface FetchBeneficiaryController {
  //formatter :off
  @Operation(
      summary = "Returns a list of Beneficiaries",
      description = "Returns a list of Beneficiaries given a Last Name",
      responses = {
          @ApiResponse(
              responseCode="200", 
              description = "A list of Beneficiaries is returned", 
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Beneficiary.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Beneficiaries were found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json")),      
      },
      parameters = {
          @Parameter(name = "lastName", 
               allowEmptyValue = false, 
               required = true, 
               description = "Last Name (i.e., 'Doe')"), 
        }
      )    
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Beneficiary> fetchBeneficiaries(
      @RequestParam(required= true)
      String lastName);
//formatter : on
}
