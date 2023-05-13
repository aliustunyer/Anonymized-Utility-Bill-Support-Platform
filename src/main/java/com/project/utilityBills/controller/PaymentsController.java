package com.project.utilityBills.controller;


import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import com.project.utilityBills.entity.Payment;
import com.project.utilityBills.entity.PaymentRequest;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;


@Validated
@RequestMapping("/Payments")
@OpenAPIDefinition(info = @Info(title = "Payments Support Service"), servers = {
@Server(url = "http://localhost:8080", description = "Local server.")})

public interface PaymentsController {
  //formatter :off
  @Operation(
      summary = "Create a payment",
      description = "Returns the created payment",
      responses = {
          @ApiResponse(
              responseCode="201", 
              description = "The created payment is returned", 
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Payment.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "A Payment component was not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json")),      
      },
      parameters = {
          @Parameter(name = "paymentRequest", 
               required = true, 
               description = "The Payment Request as JSON")
        }
      )    
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Payment createPayment (@Valid @RequestBody PaymentRequest paymentRequest);
}
