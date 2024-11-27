package com.levi.customer.mapper;

import com.levi.customer.controller.dto.CustomerDTO;
import com.levi.customer.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "firstName", expression = "java(FirstName.of(customerDTO.firstName()))")
    @Mapping(target = "lastName", expression = "java(LastName.of(customerDTO.lastName()))")
    @Mapping(target = "birthDate", expression = "java(BirthDate.of(customerDTO.birthDate()))")
    @Mapping(target = "emailAddress", expression = "java(EmailAddress.of(customerDTO.emailAddress()))")
    @Mapping(target = "ssn", expression = "java(ssn.of(customerDTO.ssn()))")
    Customer toModel(CustomerDTO customerDTO);

    @Mapping(target = "firstName", expression = "java(customer.getFirstName().value())")
    @Mapping(target = "lastName", expression = "java(customer.getLastName().value())")
    @Mapping(target = "birthDate", expression = "java(customer.getBirthDate().value())")
    @Mapping(target = "emailAddress", expression = "java(customer.getEmailAddress().value())")
    @Mapping(target = "ssn", expression = "java(customer.getSsn().value())")
    CustomerDTO toDTO(Customer customer);
}
