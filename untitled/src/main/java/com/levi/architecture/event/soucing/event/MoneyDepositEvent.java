package com.levi.architecture.event.soucing.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.levi.architecture.event.soucing.state.AccountAggregate;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;


@Getter
public class MoneyDepositEvent extends DomainEvent {

    private final BigDecimal money;
    private final int accountNo;

    /**
     * Instantiates a new Money deposit event.
     *
     * @param sequenceId  the sequence id
     * @param createdTime the created time
     * @param accountNo   the account no
     * @param money       the money
     */
    @JsonCreator
    public MoneyDepositEvent(
            @JsonProperty("sequenceId") long sequenceId,
            @JsonProperty("createdTime") long createdTime,
            @JsonProperty("accountNo") int accountNo,
            @JsonProperty("money") BigDecimal money) {
        super(sequenceId, createdTime, "MoneyDepositEvent");
        this.money = money;
        this.accountNo = accountNo;
    }

    @Override
    public void process() {
        var account =
                Optional.ofNullable(AccountAggregate.getAccount(accountNo))
                        .orElseThrow(() -> new RuntimeException("Account not found"));
        account.handleEvent(this);
    }
}
