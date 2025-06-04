package br.com.madlib.madlib_gcp_template.api.service;

import br.com.madlib.madlib_gcp_template.api.dto.BatchRegulatoryDTO;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriberService {

    public static final int MAX_RETRIES = 5;

    private final PubSubTemplate pubSubTemplate;

    @Value("${spring.cloud.gcp.pubsub.dlq.employee-batch-regulatory-validated-dlq}")
    private String employeeBatchRegulatoryValidatedDlq;

    @ServiceActivator(inputChannel = "employeeBatchRegulatoryValidatedInputChanel")
    public void employeeBatchRegulatoryValidatedMessageReceiver(
            final BatchRegulatoryDTO payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) final BasicAcknowledgeablePubsubMessage message) {

        log.info("Message received from GCP PubSub: {}", payload);

        try {
            message.ack();
        } catch (final Exception e) {
            log.error("Failed to process message", e);
            handleDeliveryAttempt(payload, message, employeeBatchRegulatoryValidatedDlq);
        }
    }

    private void handleDeliveryAttempt(
            Object payload,
            BasicAcknowledgeablePubsubMessage message,
            String dlqTopicId) {
        final var deliveryAttempt = getDeliveryAttempt(message);
        log.debug("Delivery attempt {}", deliveryAttempt);
        if (deliveryAttempt >= MAX_RETRIES) {
            pubSubTemplate.publish(dlqTopicId, payload);
            log.error("Max Delivery attempts exceeded: {}. Moving to DLQ {}", MAX_RETRIES, dlqTopicId);
            message.ack();
        } else {
            message.nack();
        }
    }

    private static int getDeliveryAttempt(BasicAcknowledgeablePubsubMessage message) {
        return Integer.parseInt(message.getPubsubMessage().getAttributesOrDefault("googclient_deliveryattempt", "1"));
    }
}
