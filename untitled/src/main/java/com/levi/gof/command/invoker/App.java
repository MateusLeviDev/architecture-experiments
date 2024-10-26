package com.levi.gof.command.invoker;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        final var payload1 = new ContractDTO(1L, null, null, null);
        final var payload2 = new ContractDTO(2L, "100", null, "11");
        final var payload3 = new ContractDTO(3L, "10", "15", null);
        final var payload4 = new ContractDTO(4L, "10000", "10", "5");

        final Map<String, ContractCommand> commands = new HashMap<>();
        commands.put(ContractConstants.GLOBAL_LIMIT, new GlobalLimitCommand());
        commands.put(ContractConstants.BILLING_INFORMATION, new BillingInformationCommand());
        commands.put(ContractConstants.FINANCIAL_SERVICES, new FinancialServicesCommand());
        commands.put(ContractConstants.DEFAULT, new DefaultCommand());

        ContractServiceInvoker contractServiceInvoker = new ContractServiceInvoker(commands);

        contractServiceInvoker.execute(payload1);
        contractServiceInvoker.execute(payload2);
        contractServiceInvoker.execute(payload3);
        contractServiceInvoker.execute(payload4);
    }
}
