package com.levi.gof.templatemethod.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class TariffContract extends CustomTariff {

    @Override
    public Long getAgreementId() { return getContractId(); }



}
