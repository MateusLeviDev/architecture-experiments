package com.levi.customer.customer;

import com.levi.customer.domain.EmailAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
*
* Given When Then - https://martinfowler.com/bliki/GivenWhenThen.html
*
* */
class EmailAddressTest {

    @Test
    @DisplayName("GIVEN a valid email WHEN email address is created")
    void givenValidEmailAddressWhenCreateThenEmailReturned() {
        final var emailAddress = "mateus@gmail.com";

        final var actualEmailAddress = EmailAddress.of(emailAddress);
        Assertions.assertNotNull(actualEmailAddress);
        Assertions.assertEquals(emailAddress, actualEmailAddress.getValue());
    }

    @Test
    @DisplayName("GIVEN invalid email with null value WHEN create THEN NPE")
    void givenInvalidEmailAddressWhenCreateThenNullPointerException() {

        final NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class, () -> EmailAddress.of(null));
        Assertions.assertEquals("the email address cannot be null", nullPointerException.getMessage());
    }

    @Test
    @DisplayName("GIVEN invalid email format WHEN create THEN IllegalArgumentException")
    void givenInvalidEmailFormatWhenCreateThenIllegalArgumentException() {
        final String invalidEmail = "invalid-email";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> EmailAddress.of(invalidEmail));
        Assertions.assertEquals("invalid email address", exception.getMessage());
    }
}