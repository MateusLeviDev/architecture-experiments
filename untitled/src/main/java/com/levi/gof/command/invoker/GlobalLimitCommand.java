package com.levi.gof.command.invoker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalLimitCommand implements ContractCommand {
    @Override
    public void execute(ContractDTO payload) {
        log.info("Executing GlobalLimitCommand with payload: {}", payload);
    }
}
