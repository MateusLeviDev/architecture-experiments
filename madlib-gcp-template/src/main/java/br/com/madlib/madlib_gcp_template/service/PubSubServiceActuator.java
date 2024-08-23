package br.com.madlib.madlib_gcp_template.service;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class PubSubServiceActuator {

    @Bean
    @ServiceActivator(inputChannel = "pubsubOutputChannel")
    public PubSubMessageHandler messageSender(PubSubTemplate pubSubTemplate) {
        return new PubSubMessageHandler(pubSubTemplate, "testTopic");
    }
}
