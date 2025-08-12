package com.ProductManagement.service;





import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.ProductManagement.DTO.CustomerDTO;
import com.ProductManagement.Entity.Customer;
import com.ProductManagement.Repo.CustomerRepository;
import com.ProductManagement.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service 
public class CustomerService {

    private static CustomerRepository customerRepo = null;

    // Constructor injection (recommended for testability)
    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    /**
     * Creates a new customer after validating unique email
     * @throws Exception 
     */
    public static CustomerDTO createCustomer(CustomerDTO dto) throws Exception {
        // Check if the email already exists
        try {
			if (customerRepo.existsByEmail(dto.getEmail())) {
			    throw new BadRequestException("Email already exists");
			}
		} catch (BadRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Convert DTO to Entity
        Customer customer = Mapper.toEntity(dto);

        // Save to DB
        Customer saved = customerRepo.save(customer);

        // Convert Entity back to DTO for response
        return Mapper.toDto(saved);
    }

    /**
     * Retrieves all customers
     */
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll()
                .stream()
                .map(Mapper::toDto) // Convert each Customer to CustomerDTO
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single customer by ID
     * @throws Exception 
     */
    public CustomerDTO getCustomerById(Long id) throws Exception {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new BadRequestException("Customer not found"));
        return Mapper.toDto(customer);
    }

    /**
     * Deletes a customer by ID
     * @throws BadRequestException 
     */
    public void deleteCustomer(Long id) throws BadRequestException {
        if (!customerRepo.existsById(id)) {
            throw new BadRequestException("Customer not found");
        }
        customerRepo.deleteById(id);
    }
}
