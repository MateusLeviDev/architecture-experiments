package com.levi.gof.command.invoker;

public record ContractDTO(
        Long id,
        String globalLimit,
        String invoiceCutOff,
        String billingDay
) {
}
