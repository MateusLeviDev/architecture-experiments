package com.levi.gof.command.invoker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FinancialServicesCommand implements ContractCommand {
    @Override
    public void execute(ContractDTO payload) {
        log.info("Executing FinancialServicesCommand with payload: {}", payload);
    }
}