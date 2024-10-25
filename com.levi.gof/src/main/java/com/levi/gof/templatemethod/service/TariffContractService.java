package com.levi.gof.templatemethod.service;

import com.levi.gof.templatemethod.model.CustomTariff;
import com.levi.gof.templatemethod.model.TariffContract;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static com.levi.gof.templatemethod.contants.ApiConstants.TARIFF_CONTRACT;

public class TariffContractService extends AbstractCustomTariffService<TariffContract> implements
        CustomTariffService<TariffContract> {

    @Override
    public Boolean isAgreementExisting(Long agreementId) {
        return Objects.nonNull(agreementId);
    }

    @Override
    public Optional<TariffContract> findCustomTariff(Long tariffId, Long agreementId) {
        if (!Objects.isNull(tariffId) && !Objects.isNull(agreementId)) {
            TariffContract mockTariffContract = new TariffContract();
            mockTariffContract.setCustomValue(BigDecimal.valueOf(200));
            mockTariffContract.setContractId(2L);
            mockTariffContract.setTariffId(2L);
            mockTariffContract.setId(10L);
            return Optional.of(mockTariffContract);
        }
        return Optional.empty();
    }

    @Override
    public Optional<TariffContract> findCustomTariff(Long tariffId, Long agreementId, Boolean isDeleted) {
        if (!Objects.isNull(tariffId) && !Objects.isNull(agreementId)) {
            TariffContract mockTariffContract = new TariffContract();
            mockTariffContract.setCustomValue(BigDecimal.valueOf(200));
            mockTariffContract.setContractId(2L);
            mockTariffContract.setTariffId(2L);
            mockTariffContract.setId(10L);
            agreementValidation(mockTariffContract);
            return Optional.of(mockTariffContract);
        }
        return Optional.empty();
    }

    @Override
    public String getAgreementName() {
        return TARIFF_CONTRACT;
    }

    @Override
    protected void agreementValidation(CustomTariff customTariff) {
        System.out.printf("Success validation for Custom Tariff: %s%n", customTariff.getTariffId());
    }

    @Override
    public TariffContract findByTariffIdAndAgreementId(Long id, Long agreementId) {
        if (!Objects.isNull(id) && !Objects.isNull(agreementId)) {
            TariffContract mockTariffContract = new TariffContract();
            mockTariffContract.setCustomValue(BigDecimal.valueOf(300));
            mockTariffContract.setContractId(12L);
            mockTariffContract.setTariffId(20L);
            mockTariffContract.setId(100L);
            return mockTariffContract;
        }
        return null;
    }
}
