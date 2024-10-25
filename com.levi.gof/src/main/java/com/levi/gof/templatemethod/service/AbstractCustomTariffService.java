package com.levi.gof.templatemethod.service;

import com.levi.gof.templatemethod.model.CustomTariff;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
public abstract class AbstractCustomTariffService<T extends CustomTariff> {

    public T save(final T customTariff)  {
        System.out.printf("Starting the save operation for Custom Tariff: %s%n", customTariff.getTariffId());

        return customTariff;
    }

    public T update(final T customTariff) {
        System.out.printf("Starting the update operation for Custom Tariff: %s%n", customTariff.getTariffId());
        customTariff.setCustomValue(BigDecimal.valueOf(100));

        return customTariff;

    }

    public void deleteById(final Long tariffId, final Long agreementId) {
        System.out.printf("Starting the delete operation for Custom Tariff with id: %s%n and agreement: %s%n", tariffId, agreementId);
    }

    public abstract Boolean isAgreementExisting(Long agreementId);

    public abstract Optional<T> findCustomTariff(Long tariffId, Long agreementId);

    public abstract Optional<T> findCustomTariff(Long tariffId, Long agreementId, Boolean isDeleted);


    public abstract String getAgreementName();

    protected abstract void agreementValidation(final CustomTariff customTariff);

}
