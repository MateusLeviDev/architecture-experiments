package com.levi.customer.domain.customer;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirstName {

    private String value;

    private FirstName(final String value) {
        this.value = value;
    }

    public static FirstName of(final String value) {
        Objects.requireNonNull(value, "First name cannot be null");
        Assert.isTrue(!value.isBlank(), "First name cannot be empty");

        return new FirstName(value);
    }
}
