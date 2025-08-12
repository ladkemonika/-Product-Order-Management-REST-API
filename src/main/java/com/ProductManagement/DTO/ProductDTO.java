package com.ProductManagement.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
	  private Long id;

	  @NotBlank(message = "name required")
	  private String name;

	  @NotNull
	  private String category;

	  @NotNull(message = "price required")
	  @DecimalMin(value = "0.0", inclusive = false, message = "price must be > 0")
	  private BigDecimal price;

	
	  public ProductDTO() {
		super();
	}

	public ProductDTO(Long id, @NotBlank(message = "name required") String name, String category,
			@NotNull(message = "price required") @DecimalMin(value = "0.0", inclusive = false, message = "price must be > 0") BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
	 
	}

