package com.levi.customer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LastName {

    @Column(name = "lastName")
    private String value;

    private LastName(final String value) {
        this.value = value;
    }

    public static LastName of(final String value) {
        Objects.requireNonNull(value, "Last name cannot be null");
        Assert.isTrue(!value.isBlank(), "Last name cannot be empty");

        return new LastName(value);
    }
}
