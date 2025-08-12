package com.ProductManagement.mapper;

import java.util.stream.Collectors;

import com.ProductManagement.DTO.CustomerDTO;
import com.ProductManagement.DTO.OrderResponseDTO;
import com.ProductManagement.DTO.ProductDTO;
import com.ProductManagement.Entity.Customer;
import com.ProductManagement.Entity.Order;
import com.ProductManagement.Entity.Product;
import com.ProductManagement.service.CustomerService;

public class Mapper 
{
	  public static ProductDTO toDto(Product p) {
	    ProductDTO d = new ProductDTO();
	    d.setId(p.getId());
	    d.setName(p.getName());
	    d.setCategory(p.getCategory());
	    d.setPrice(p.getPrice());
	    return d;
	  }
	  // other mappers...
	
	 // Convert Entity to DTO
	    public static CustomerDTO toDto(Customer customer) {
	        if (customer == null) return null;
	        CustomerDTO dto = new CustomerDTO();
	        dto.setId(customer.getId());
	        dto.setName(customer.getName());
	        dto.setEmail(customer.getEmail());
	        dto.setRegion(customer.getRegion());
	        return dto; // ✅ returns CustomerDTO
	    }
	    public static OrderResponseDTO toOrderResponseDto(Order order) {
	        if (order == null) return null;

	        OrderResponseDTO dto = new OrderResponseDTO();
	        dto.setId(order.getId());
	        dto.setOrderDate(order.getOrderDate());
	        dto.setCustomerId(order.getCustomer().getId());
	        dto.setStatus(order.getStatus());  // pass enum, since DTO expects OrderStatus type

	        dto.setTotalAmount(order.getTotalAmount());

	        // Assuming OrderResponseDTO has a List<Long> productIds field:
	       
	        dto.setProducts(
	        	    order.getProducts()                // 1. Get the List<Product> from the Order entity
	        	         .stream()                    // 2. Create a Stream<Product> to process each product
	        	         .map(Mapper::toDto)          // 3. Convert each Product entity to ProductDTO using your Mapper class
	        	         .collect(Collectors.toList())  // 4. Collect all ProductDTO objects into a List<ProductDTO>
	        	);

	        return dto;
	    }
		// Convert DTO to Entity
	    public static Customer toEntity(CustomerDTO dto) {
	        if (dto == null) return null;
	        Customer customer = new Customer();
	        customer.setId(dto.getId());
	        customer.setName(dto.getName());
	        customer.setEmail(dto.getEmail());
	        customer.setRegion(dto.getRegion());
	        return customer; // ✅ returns Customer, not CustomerService
	    }
}