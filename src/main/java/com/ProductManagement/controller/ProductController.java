package com.ProductManagement.controller;



import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ProductManagement.DTO.CustomerDTO;
import com.ProductManagement.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers") // Base path
public class CustomerController {
 
    private final CustomerService customerService;

    // Constructor injection
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // POST /api/customers → Register a customer
    @PostMapping
    public ResponseEntity<CustomerDTO> registerCustomer(
        @Valid @RequestBody CustomerDTO dto) throws Exception {
        CustomerDTO created = customerService.createCustomer(dto);
        return ResponseEntity.ok(created);
    }
}


