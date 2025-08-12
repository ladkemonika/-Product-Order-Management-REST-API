package com.ProductManagement.DTO;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderRequestDTO {
	
	  @NotNull(message = "customerId required")
	  private Long customerId;

	  @NotEmpty(message = "productIds required")
	  private List<Long> productIds;
	  // getters/setters

	public Long getCustomerId() {
		return customerId;
	}

	public OrderRequestDTO() {
		super();
	}

	public OrderRequestDTO(@NotNull(message = "customerId required") Long customerId,
			@NotEmpty(message = "productIds required") List<Long> productIds) {
		super();
		this.customerId = customerId;
		this.productIds = productIds;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
	  
	}
