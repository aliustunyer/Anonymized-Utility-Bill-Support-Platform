package com.project.utilityBills.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Validated
@RequestMapping("/DeleteBeneficiary")
@OpenAPIDefinition(info = @Info(title = "Delete Beneficiary Service"), servers = {
@Server(url = "http://localhost:8080", description = "Local server.")})

public interface DeleteBeneficiaryController {

  @Operation(
      summary = "Delete a beneficiary",
      description = "Deletes the specified beneficiary",
      responses = {
          @ApiResponse(
              responseCode="200", 
              description = "Beneficiary is successfully deleted", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "A Beneficiary component was not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred", 
              content = @Content(mediaType = "application/json")),      
      }
  )    
  @DeleteMapping("/{beneficiary_Id}")
  @ResponseStatus(HttpStatus.OK)
  void deleteBeneficiary(@PathVariable int beneficiary_Id);
}
