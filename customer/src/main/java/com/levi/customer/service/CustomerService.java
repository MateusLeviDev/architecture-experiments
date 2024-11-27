package com.levi.customer.service;

import com.levi.customer.domain.Customer;
import com.levi.customer.domain.EmailAddress;

public interface CustomerService {

    Customer create(Customer customer);

    void changeEmail(Long customerId, EmailAddress emailAddress);
}
