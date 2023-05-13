package com.project.utilityBills.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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

public interface CreateBeneficiaryController {

//formatter :off
  @Operation(
      summary = "Create a beneficiary",
      description = "Returns the created beneficiary",
      responses = {
          @ApiResponse(
              responseCode="201", 
              description = "New Beneficiary is returned", 
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
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json")),      
      },
      parameters = {
          @Parameter(name = "Create Beneficiary Request", 
               required = true, 
               description = "The New Beneficiary Request as JSON")
        }
      )    
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Beneficiary createBeneficiary (@Valid @RequestBody BeneficiaryRequest beneficiaryRequest);
}

