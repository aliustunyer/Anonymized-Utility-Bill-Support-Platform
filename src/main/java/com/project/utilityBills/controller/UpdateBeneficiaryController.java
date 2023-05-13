package com.project.utilityBills.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.project.utilityBills.entity.Beneficiary;
import com.project.utilityBills.entity.BeneficiaryRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/Beneficiary")
@OpenAPIDefinition(info = @Info(title = "Create Beneficiary Service"), servers = {
@Server(url = "http://localhost:8080", description = "Local server.")})

public interface UpdateBeneficiaryController {

//formatter :off
  @Operation(
      summary = "Update a beneficiary",
      description = "Returns the updated beneficiary",
      responses = {
          @ApiResponse(
              responseCode="200", 
              description = "Updated Beneficiary is returned", 
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Beneficiary.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
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
  @PutMapping("/{beneficiary_Id}")
  Beneficiary updateBeneficiary(@PathVariable int beneficiary_Id, @Valid @RequestBody Beneficiary beneficiaryRequest);
}