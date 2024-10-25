package com.levi.gof.templatemethod;

import com.levi.gof.templatemethod.model.TariffContract;
import com.levi.gof.templatemethod.service.TariffContractService;

import java.math.BigDecimal;
import java.util.Optional;

public class TestContract {

    public static void main(String[] args) {
        final TariffContractService service = new TariffContractService();
        final TariffContract tariffContract = TariffContract.builder()
                .id(10L)
                .customValue(BigDecimal.TEN)
                .contractId(5L)
                .tariffId(2L)
                .build();

        System.out.println(service.getAgreementName());

        Optional<TariffContract> customTariff = service.findCustomTariff(1L, 20L, Boolean.FALSE);
        if (customTariff.isPresent()) {
            final var tariffContractFounded = customTariff.get();
            System.out.println("Tariff found: " + tariffContractFounded);
        } else {
            System.out.println("Tariff not found!");
        }

        System.out.println(service.save(tariffContract));


    }
}
