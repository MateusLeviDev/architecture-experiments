package com.levi.customer.service;

import com.levi.customer.domain.Customer;
import com.levi.customer.domain.EmailAddress;
import com.levi.customer.repository.CustomerRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final StreamBridge streamBridge;

    public CustomerServiceImpl(final CustomerRepository customerRepository, final StreamBridge streamBridge) {
        this.customerRepository = customerRepository;
        this.streamBridge = streamBridge;
    }


    @Override
    public Customer create(Customer customer) {
        Customer customerCreated = customerRepository.save(customer);
        streamBridge.send("customer-created-topic", customerCreated);
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
