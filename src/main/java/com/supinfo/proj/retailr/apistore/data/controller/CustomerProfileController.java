package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.model.Response;
import com.supinfo.proj.retailr.apistore.service.CustomerProfileService;
import com.supinfo.proj.retailr.apistore.service.domain.CustomerProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "Permet de créer un profile d'utilisateur et de client")
@RestController
public class CustomerProfileController {
    private static Logger logger = LoggerFactory.getLogger(CustomerProfileController.class);

    @Autowired
    private CustomerProfileService customerProfileService;

    @ApiOperation("Création d'un Customer ET d'un User")
    @PostMapping("/customer/register")
    public ResponseEntity<?> registerCustomer(@RequestBody @Valid CustomerProfile customerProfile, BindingResult result){
        if (!result.hasErrors()){
            logger.info(customerProfile.toString());
            try {
                this.customerProfileService.registerCustomer(customerProfile);
            } catch (Exception e){
                return ResponseEntity.ok().body(new Response(e.getMessage()));
            }
            return ResponseEntity.ok().body(new Response("successfully registered new customer"));
        } else {
            return ResponseEntity.ok().body("Wrong format");
        }
    }

    @ApiOperation("Suppression du Customer ET du User lié à ce Customer")
    @DeleteMapping("customer/{username}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String username){
        try {
            this.customerProfileService.deleteCustomerAccount(username);
            logger.info("deleted customer account : " + username);
            return ResponseEntity.ok().body(new Response("successfully deleted account"));
        } catch (Exception e){
            return ResponseEntity.ok().body(new Response(e.getMessage()));
        }
    }
}
