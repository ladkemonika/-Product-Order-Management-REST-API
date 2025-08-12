package com.ProductManagement.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


import com.ProductManagement.Entity.OrderStatus;

import lombok.Data;
@Data

public class OrderResponseDTO {

	private Long id;
	  private LocalDateTime orderDate;
	  private Long customerId;
	  private List<ProductDTO> products;
	  private BigDecimal totalAmount;
	  private OrderStatus status;
	
	  
	  
	  public OrderResponseDTO() {
		super();
	}
	public OrderResponseDTO(Long id, LocalDateTime orderDate, Long customerId, List<ProductDTO> products,
			BigDecimal totalAmount, OrderStatus status) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.products = products;
		this.totalAmount = totalAmount;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public List<ProductDTO> getProducts() {
	    return products;
	}

	public void setProducts(List<ProductDTO> products) {
	    this.products = products;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
	
	  
	  
	
	  
	
	
	
	}

