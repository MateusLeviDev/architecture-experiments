package com.levi.architecture.event.soucing.app;


import com.levi.architecture.event.soucing.event.AccountCreateEvent;
import com.levi.architecture.event.soucing.event.MoneyDepositEvent;
import com.levi.architecture.event.soucing.event.MoneyTransferEvent;
import com.levi.architecture.event.soucing.processor.DomainEventProcessor;
import com.levi.architecture.event.soucing.processor.JsonFileJournal;
import com.levi.architecture.event.soucing.state.AccountAggregate;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
public class App {

    /**
     * The constant ACCOUNT OF DAENERYS.
     */
    public static final int ACCOUNT_OF_DAENERYS = 1;

    /**
     * The constant ACCOUNT OF JON.
     */
    public static final int ACCOUNT_OF_JON = 2;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        var eventProcessor = new DomainEventProcessor(new JsonFileJournal());

        log.info("Running the system first time............");
        eventProcessor.reset();

        log.info("Creating the accounts............");

        eventProcessor.process(
                new AccountCreateEvent(0, new Date().getTime(), ACCOUNT_OF_DAENERYS, "Daenerys Targaryen"));

        eventProcessor.process(
                new AccountCreateEvent(1, new Date().getTime(), ACCOUNT_OF_JON, "Jon Snow"));

        log.info("Do some money operations............");

        eventProcessor.process(
                new MoneyDepositEvent(
                        2, new Date().getTime(), ACCOUNT_OF_DAENERYS, new BigDecimal("100000")));

        eventProcessor.process(
                new MoneyDepositEvent(3, new Date().getTime(), ACCOUNT_OF_JON, new BigDecimal("100")));

        eventProcessor.process(
                new MoneyTransferEvent(
                        4, new Date().getTime(), new BigDecimal("10000"), ACCOUNT_OF_DAENERYS, ACCOUNT_OF_JON));

        log.info("...............State:............");
        log.info(AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS).toString());
        log.info(AccountAggregate.getAccount(ACCOUNT_OF_JON).toString());

        log.info("At that point system had a shut down, state in memory is cleared............");
        AccountAggregate.resetState();

        log.info("Recover the system by the events in journal file............");

        eventProcessor = new DomainEventProcessor(new JsonFileJournal());
        eventProcessor.recover();

        log.info("...............Recovered State:............");
        log.info(AccountAggregate.getAccount(ACCOUNT_OF_DAENERYS).toString());
        log.info(AccountAggregate.getAccount(ACCOUNT_OF_JON).toString());
    }
}
