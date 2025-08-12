package com.ProductManagement.controller;

import java.net.URI;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ProductManagement.DTO.OrderRequestDTO;
import com.ProductManagement.DTO.OrderResponseDTO;
import com.ProductManagement.service.OrderService;



import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ProductManagement.DTO.OrderRequestDTO;
import com.ProductManagement.DTO.OrderResponseDTO;
import com.ProductManagement.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders") // Base URL for all order APIs
public class OrderController {

    private final OrderService orderService; // Injected service

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /api/orders → Place a new order
    @PostMapping
    public ResponseEntity<OrderResponseDTO> place(@Valid @RequestBody OrderRequestDTO req) throws Exception {
        OrderResponseDTO created = orderService.placeOrder(req);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        
        return ResponseEntity.created(location).body(created);
    }

    // GET /api/orders/{id} → View order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    // PUT /api/orders/{id}/cancel → Cancel an order
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancel(@PathVariable Long id) throws BadRequestException {
        OrderResponseDTO updated = orderService.cancelOrder(id);
        return ResponseEntity.ok(updated);
    }
}
