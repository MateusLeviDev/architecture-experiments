package com.levi.gof.templatemethod.service;

import com.levi.gof.templatemethod.model.CustomTariff;

public interface CustomTariffService<T extends CustomTariff> {

    T save(T customTariff) ;

    T update(T customTariff) ;

    void deleteById(Long id, Long agreementId);

    T findByTariffIdAndAgreementId(Long id, final Long agreementId);


}
