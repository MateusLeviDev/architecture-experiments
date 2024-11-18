package com.orchidaceae.br.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private void publishMessage(final String topic, final String key, String message) {
        kafkaTemplate.send(topic, key, message);
        log.info("Message sent to topic: " + topic);
    }
}
