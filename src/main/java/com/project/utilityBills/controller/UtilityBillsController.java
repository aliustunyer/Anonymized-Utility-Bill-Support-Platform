package com.project.utilityBills.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.project.utilityBills.entity.UtilityBills;
import com.project.utilityBills.entity.UtilityType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;



@RequestMapping("/UtilityBills")
@OpenAPIDefinition(info = @Info(title = "Utility Bills Support Service"), servers = {
@Server(url = "http://localhost:8080", description = "Local server.")})

public interface UtilityBillsController {
  //formatter :off
  @Operation(
      summary = "Returns a list of Utility Bills",
      description = "Returns a list of Utility Bills given an optional Utility Type and/or Amount Due",
      responses = {
          @ApiResponse(
              responseCode="200", 
              description = "A list of Utility Bills is returned", 
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = UtilityBills.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Utility Bills were found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json")),      
      },
      parameters = {
          @Parameter(name = "UtilityType", 
               allowEmptyValue = false, 
               required = false, 
               description = "The Utility Type (i.e., 'electricity')"), 
          @Parameter(name = "AmountDue", 
               allowEmptyValue = false, 
               required = false, 
               description = "Amount Due (i.e., '75.00')"), 
        }
      )    
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<UtilityBills> fetchUtilityBills(
      @RequestParam(required= true)
      UtilityType utilityType, 
      @RequestParam(required= true)
      BigDecimal amountDue);
//formatter : on
}
