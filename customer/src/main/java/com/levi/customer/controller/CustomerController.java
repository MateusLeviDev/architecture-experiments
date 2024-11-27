package com.levi.customer.controller;

import com.levi.customer.controller.dto.CustomerDTO;
import com.levi.customer.domain.Customer;
import com.levi.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody @Valid CustomerDTO customerDTO) {
        Customer customer = customerService.create(customerDTO);

        return ResponseEntity.ok(customer);
    }
}
