package com.levi.customer.messaging.event;

import java.time.LocalDate;

public record CustomerDTO(
        String firstName,
        String lastName,
        LocalDate birthdate,
        String emailAddress,
        Integer SSN) {
}
