package br.com.madlib.madlib_gcp_template.api.service;

import br.com.madlib.madlib_gcp_template.api.dto.TaxDTO;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final TaxService taxService;
    private final PubSubTemplate pubSubTemplate;

    @ServiceActivator(inputChannel = "inputChannel")
    public void messageReceiver(
            final TaxDTO payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) final BasicAcknowledgeablePubsubMessage message) {
        log.info("Message received from GCP PubSub: {}", payload);

        try {
            if ("error".equalsIgnoreCase(payload.name())) {
                throw new RuntimeException("Simulated error for DLQ topic");
            }

            taxService.processMessage(payload);
            message.ack();
        } catch (final ObjectRetrievalFailureException ore) {
            log.debug("Message Published to DLQ");
            handleDeliveryAttempt(payload, message);
        } catch (final Exception e) {
            log.error("Failed to process message", e);
            handleDeliveryAttempt(payload, message);
        }
    }

    private void handleDeliveryAttempt(Object payload, BasicAcknowledgeablePubsubMessage message) {
        final var deliveryAttempt = getDeliveryAttempt(message);
        log.debug("Delivery attempt {}", deliveryAttempt);
        if (Integer.parseInt(deliveryAttempt) >= 5) {
            pubSubTemplate.publish("test-topic-dlq", payload);
            message.ack();
        } else {
            message.nack();
        }
    }

    private String getDeliveryAttempt(BasicAcknowledgeablePubsubMessage message) {
        return message.getPubsubMessage()
                .getAttributesOrDefault("googclient_deliveryattempt", "0");
    }
}
