package com.ProductManagement.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerDTO {
	  
	private Long id;

	  @NotBlank
	  private String name;

	  @Email
	  @NotBlank
	  private String email;

	  private String region;

	
	  
	  public CustomerDTO() {
		super();
	}

	public CustomerDTO(Long id, @NotBlank String name, @Email @NotBlank String email, String region) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.region = region;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	 
	  // getters/setters
	  
	}
