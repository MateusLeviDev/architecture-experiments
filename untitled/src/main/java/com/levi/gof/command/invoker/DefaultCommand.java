package com.levi.gof.command.invoker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultCommand implements ContractCommand {
    @Override
    public void execute(ContractDTO payload) {
        log.info("Executing DefaultCommand with payload: {}", payload);
    }
}