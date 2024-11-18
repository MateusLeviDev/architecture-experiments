package com.levi.gof.adapter.exemplo1;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

class PayPalAdapter implements PaymentProcessor {
    public void makePayment(double amount) {
        // PayPal-specific logic to process payment
        //actual api
        System.out.println("Payment processed via PayPal: " + amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    public void makePayment(double amount) {
        // Stripe-specific logic to process payment
        System.out.println("Payment processed via Stripe: " + amount);
    }
}

class GPayAdapter implements PaymentProcessor {
    public void makePayment(double amount) {
        // Gpay-specific logic to process payment
        System.out.println("Payment processed via Gpay: " + amount);
    }
}

@Log4j2
public class App {
    public static void main(String[] args) {
        PaymentProcessor paypal = new PayPalAdapter();
        PaymentProcessor stripe = new StripeAdapter();
        PaymentProcessor gPay = new GPayAdapter();

        List<PaymentProcessor> processors = Arrays.asList(paypal, stripe, gPay);

        PaymentService paymentService = new PaymentService(processors);

        PaymentResponse paymentResponse = paymentService.processPayment("PayPal", new PaymentRequest(150.00, "R$"));

        log.info("App::ProcessPayment {}", paymentResponse);
    }
}
