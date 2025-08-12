package com.ProductManagement.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ProductManagement.DTO.ProductDTO;
import com.ProductManagement.Entity.Product;
import com.ProductManagement.Repo.ProductRepository;
import com.ProductManagement.mapper.Mapper;

import jakarta.validation.Valid;

@Service
public class ProductService {

	private final ProductRepository productRepository;

    // Constructor Injection
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Method: getAllProducts
     * Purpose: Fetch all products from DB and convert them into DTOs
     */
   
    private ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory()); 
        dto.setPrice(product.getPrice());
        
        // set other fields you want to expose in API
        return dto;
    }

  
  public List<ProductDTO> getAllProducts() {
      // ✅ Normal approach
      List<Product> products = productRepository.findAll();

      // Convert to DTO
      return products.stream()
              .map(this::toDTO) // Convert Product entity to ProductDTO
              .collect(Collectors.toList());
  }

  public ProductDTO createProduct(@Valid ProductDTO dto) {
	    // 1️⃣ Convert DTO to entity
	    Product product = new Product();
	    product.setName(dto.getName());
	    product.setCategory(dto.getCategory());
	    product.setPrice(dto.getPrice());
	   
	    // set other fields if needed

	    // 2️⃣ Save entity in DB
	    Product savedProduct = productRepository.save(product);

	    // 3️⃣ Convert saved entity back to DTO
	    return toDTO(savedProduct);
	}

}

