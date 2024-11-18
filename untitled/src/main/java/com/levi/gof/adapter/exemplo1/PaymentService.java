package com.levi.gof.adapter.exemplo1;


import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaymentService {

    private final Map<String, PaymentProcessor> paymentProcessorMap;

    public PaymentService(final List<PaymentProcessor> paymentProcessors) {
        paymentProcessorMap = paymentProcessors.stream()
                .collect(Collectors.toMap(paymentProcessor -> paymentProcessor.getClass().getSimpleName(), Function.identity()));
    }

    public PaymentResponse processPayment(String gateway, PaymentRequest paymentRequest) {
        PaymentProcessor paymentProcessor = paymentProcessorMap.get(gateway + "Adapter");
        paymentProcessor.makePayment(paymentRequest.amount());
        return new PaymentResponse(true, new Random().nextLong(1000000000L));
    }
}
