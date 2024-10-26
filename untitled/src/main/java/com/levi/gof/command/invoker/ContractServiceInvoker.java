package com.levi.gof.command.invoker;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

import static com.levi.gof.command.invoker.ContractConstants.*;

@Slf4j
public class ContractServiceInvoker implements ContractCommand {

    private final Map<String, ContractCommand> contractCommands;

    public ContractServiceInvoker(final Map<String, ContractCommand> contractCommand) {
        this.contractCommands = contractCommand;
    }

    @Override
    public void execute(final ContractDTO payload) {
        final var commandKey = parseCommandType(payload);
        final var contractCommand = contractCommands.get(commandKey);
        if (!Objects.isNull(contractCommand)) {
            contractCommand.execute(payload);
        } else {
            log.error("Command not found: {}", commandKey);
        }
    }

    protected String parseCommandType(final ContractDTO payload) {
        var commandtype = DEFAULT;

        if (payload.billingDay() == null && payload.globalLimit() == null && payload.invoiceCutOff() == null) {
            commandtype = FINANCIAL_SERVICES;
        } else {
            if (payload.billingDay() != null) {
                commandtype = BILLING_INFORMATION;
            }
            if (payload.globalLimit() != null) {
                commandtype = GLOBAL_LIMIT;
            }
        }
        return commandtype;
    }
}
