package com.levi.decision.domain;
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
public class SSN {

    @Column(name = "ssn")
    private Integer ssn;

    private SSN(final Integer value) {
        this.ssn = value;
    }

    public static SSN create(final Integer ssn) {
        Objects.requireNonNull(ssn, "Social Security Number cannot be null");
        Assert.isTrue(String.valueOf(ssn).toCharArray().length == 9, "Social Security Number should have 9 characters");

        return new SSN(ssn);
    }
}
