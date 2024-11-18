package com.levi.gof.adapter.exemplo1;

public record PaymentRequest(Double amount, String currency) {

    public PaymentRequest {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }
}
