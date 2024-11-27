package com.levi.customer.service;

import com.levi.customer.controller.dto.CustomerDTO;
import com.levi.customer.domain.Customer;
import com.levi.customer.domain.EmailAddress;

public interface CustomerService {

    Customer create(CustomerDTO customerDTO);

    void changeEmail(Long customerId, EmailAddress emailAddress);
}
