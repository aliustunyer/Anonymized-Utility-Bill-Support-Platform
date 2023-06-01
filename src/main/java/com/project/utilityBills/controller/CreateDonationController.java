package com.project.utilityBills.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.project.utilityBills.entity.DonationRequest;
import com.project.utilityBills.entity.Donations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/Donations")
@OpenAPIDefinition(info = @Info(title = "Create Donation Service"), servers = {
@Server(url = "http://localhost:8080", description = "Local server.")})

public interface CreateDonationController {

//formatter :off
  @Operation(
      summary = "Create a donation",
      description = "Returns the created donation",
      responses = {
          @ApiResponse(
              responseCode="201", 
              description = "New Donation is returned", 
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Donations.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "A Donation component was not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json")),      
      },
      parameters = {
          @Parameter(name = "donationRequest", 
               required = true, 
               description = "The New Donation Request as JSON")
        }
      )    
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Donations createDonation (@Valid @RequestBody DonationRequest donationRequest);
}

