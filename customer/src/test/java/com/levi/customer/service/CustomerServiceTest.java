package com.levi.customer.service;

import com.levi.customer.controller.dto.CustomerDTO;
import com.levi.customer.controller.dto.CustomerEvent;
import com.levi.customer.domain.*;
import com.levi.customer.mapper.CustomerMapper;
import com.levi.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Sinks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private Sinks.Many<CustomerEvent> customerProducer;

    @InjectMocks
    private CustomerServiceImpl customerService;

    CustomerDTO customerDTO;

    Customer customer;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO(
                "John", "Doe", LocalDate.of(2000, 1, 1), "john.doe@example.com", 123456789);
        customer = Customer.create(
                FirstName.of("John"), LastName.of("Doe"), BirthDate.of(LocalDate.of(1985, 5, 15)),
                EmailAddress.of("john.doe@example.com"), SSN.of(123456789));
    }

    @Test
    @DisplayName("GIVEN a valid CustomerDTO WHEN create is called THEN Customer is saved")
    void givenValidCustomerDTOWhenCreateThenCustomerSaved() {
        when(customerMapper.toModel(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer customerCreated = customerService.create(customerDTO);

        assertNotNull(customerCreated);
        assertEquals("John", customerCreated.getFirstName().getValue());
        assertEquals("Doe", customerCreated.getLastName().getValue());
    }

    @Test
    @DisplayName("GIVEN a valid CustomerDTO WHEN create is called THEN CustomerCreated event is emitted")
    void givenValidCustomerDTOWhenCreateThenEventEmitted() {
        when(customerMapper.toModel(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);

        customerService.create(customerDTO);

        ArgumentCaptor<CustomerEvent> eventCaptor = ArgumentCaptor.forClass(CustomerEvent.class);
        verify(customerProducer).tryEmitNext(eventCaptor.capture());

        CustomerEvent emittedEvent = eventCaptor.getValue();
        assertInstanceOf(CustomerEvent.CustomerCreated.class, emittedEvent);
    }

}