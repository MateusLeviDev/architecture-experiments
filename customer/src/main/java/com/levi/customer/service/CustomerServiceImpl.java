package com.levi.customer.service;

import com.levi.customer.controller.dto.CustomerDTO;
import com.levi.customer.controller.dto.CustomerEvent;
import com.levi.customer.domain.Customer;
import com.levi.customer.domain.EmailAddress;
import com.levi.customer.mapper.CustomerMapper;
import com.levi.customer.repository.CustomerRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.time.Instant;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private final Sinks.Many<CustomerEvent> customerProducer;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, Sinks.Many<CustomerEvent> customerProducer) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerProducer = customerProducer;
    }

    @Override
    public Customer create(CustomerDTO customerDTO) {
        Customer customerCreated = customerRepository.save(customerMapper.toModel(customerDTO));
        var customerCreatedEvent = new CustomerEvent.CustomerCreated(customerCreated.getId(), Instant.now(), customerDTO);
        customerProducer.tryEmitNext(customerCreatedEvent);
        return customerCreated;
    }

    @Override
    public void changeEmail(Long customerId, EmailAddress emailAddress) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Couldn´t find a customer by id: %s", customerId)));

        customer.changeEmail(emailAddress);

        customerRepository.save(customer);
    }
}

