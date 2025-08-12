package com.ProductManagement.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ProductManagement.DTO.ProductDTO;
import com.ProductManagement.service.ProductService;

import jakarta.validation.Valid;



import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ProductManagement.DTO.ProductDTO;
import com.ProductManagement.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products") // All product endpoints start with this
public class ProductController {
	
    private final ProductService productService;

    // Constructor injection — avoids NullPointerException
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // POST /api/products → Add a new product
    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
        ProductDTO created = productService.createProduct(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri();
        return ResponseEntity.created(location).body(created);
    }

    // GET /api/products → Get all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

   
}

