package com.levi.gof.templatemethod.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Tariff {

    private Long id;
    private String channel;
    private String description;
    private BigDecimal value;

}
