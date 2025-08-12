package com.ProductManagement.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  
	@Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime orderDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToMany
  @JoinTable(
    name = "order_products",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  
  private List<Product> products = new ArrayList<>();

  
  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

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

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public List<Product> getProducts() {
	return products;
}

public void setProducts(List<Product> products) {
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
