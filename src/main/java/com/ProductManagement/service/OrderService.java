package com.ProductManagement.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProductManagement.DTO.OrderRequestDTO;
import com.ProductManagement.DTO.OrderResponseDTO;
import com.ProductManagement.Entity.Customer;
import com.ProductManagement.Entity.Order;
import com.ProductManagement.Entity.OrderStatus;
import com.ProductManagement.Entity.Product;
import com.ProductManagement.Exception.ResourceNotFoundException;
import com.ProductManagement.Repo.CustomerRepository;
import com.ProductManagement.Repo.OrderRepository;
import com.ProductManagement.Repo.ProductRepository;
import com.ProductManagement.mapper.Mapper;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;

    // Constructor injection
    public OrderService(OrderRepository orderRepo, CustomerRepository customerRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO req) throws BadRequestException {
        Customer customer = customerRepo.findById(req.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<Product> products = productRepo.findAllById(req.getProductIds());

        if (products.size() != req.getProductIds().size()) {
            throw new BadRequestException("Some productIds not found");
        }

        BigDecimal total = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(products);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.NEW);

        Order saved = orderRepo.save(order);

        return Mapper.toOrderResponseDto(saved);
    }

    public OrderResponseDTO getOrder(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        return Mapper.toOrderResponseDto(order);
    }

    @Transactional
    public OrderResponseDTO cancelOrder(Long id) throws BadRequestException {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new BadRequestException("Order is already cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);
        Order updated = orderRepo.save(order);
        return Mapper.toOrderResponseDto(updated);
    }
}
