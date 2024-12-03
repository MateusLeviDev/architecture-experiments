package com.levi.customer.messaging;

import com.levi.customer.controller.dto.CustomerEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
@RequiredArgsConstructor
public class CustomerMessaging {

    @Bean
    public Sinks.Many<CustomerEvent> customerCreatedEventStream() {
        return Sinks.many().replay().latest();
    }

    @Bean
    public Supplier<Flux<CustomerEvent>> customerEventSink() {
        return () -> customerCreatedEventStream().asFlux();
    }

}