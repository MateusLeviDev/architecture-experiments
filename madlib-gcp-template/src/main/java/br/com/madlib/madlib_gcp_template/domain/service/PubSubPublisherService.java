package br.com.madlib.madlib_gcp_template.domain.service;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class PubSubPublisherService {

    private final PubSubTemplate pubSubTemplate;

    public PubSubPublisherService(final PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    public void publishMessage(final String message, final String topic) {
        try {
            final CompletableFuture<String> publish = pubSubTemplate.publish(topic, message);
            log.info("message: {}, messageId: {}, topic: {}", message, publish.get(), topic);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
