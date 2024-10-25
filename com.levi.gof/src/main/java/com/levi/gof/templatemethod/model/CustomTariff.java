package com.levi.gof.templatemethod.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@ToString
public abstract class CustomTariff {
    
    private Long id;
    private Long tariffId ;
    private Long contractId;
    private BigDecimal customValue;
    public abstract Long getAgreementId();

}
