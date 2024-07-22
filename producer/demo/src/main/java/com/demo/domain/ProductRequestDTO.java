package com.demo.domain;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String title,
        BigDecimal price,
        Integer quantity
) {
}
