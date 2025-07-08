package com.levi.architecture.event.soucing.state;


import com.levi.architecture.event.soucing.domain.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class AccountAggregate {

    private static Map<Integer, Account> accounts = new HashMap<>();

    private AccountAggregate() {
    }

    /**
     * Put account.
     *
     * @param account the account
     */
    public static void putAccount(Account account) {
        accounts.put(account.getAccountNo(), account);
    }

    /**
     * Gets account.
     *
     * @param accountNo the account no
     * @return the copy of the account or null if not found
     */
    public static Account getAccount(int accountNo) {
        return Optional.of(accountNo).map(accounts::get).map(Account::copy).orElse(null);
    }

    /**
     * Reset state.
     */
    public static void resetState() {
        accounts = new HashMap<>();
    }
}
