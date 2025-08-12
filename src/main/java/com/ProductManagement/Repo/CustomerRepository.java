package com.ProductManagement.Repo;



import com.ProductManagement.Entity.Customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email); // for email check in CustomerService
}
