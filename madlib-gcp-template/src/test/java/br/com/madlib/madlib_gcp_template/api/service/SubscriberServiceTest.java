package br.com.madlib.madlib_gcp_template.api.service;

import br.com.madlib.madlib_gcp_template.dto.BatchRegulatoryDTO;
import br.com.madlib.madlib_gcp_template.handlers.HandlerRegistry;
import br.com.madlib.madlib_gcp_template.service.SubscriberService;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.pubsub.v1.PubsubMessage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(MockitoExtension.class)
class SubscriberServiceTest {

    @Mock
    private BasicAcknowledgeablePubsubMessage message;

    @Mock
    private PubSubTemplate pubSubTemplate;

    @Mock
    private HandlerRegistry handlerRegistry;

    @InjectMocks
    private SubscriberService subscriberService;

    static final String DLQ_TOPIC_ID = "dlq-topic";

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(subscriberService, "employeeBatchRegulatoryValidatedDlq", DLQ_TOPIC_ID);
    }

    @Test
    void shouldReceiveEmployeeBatchRegulatoryValidatedAndAck() {
        var payload = buildBatchRegulatoryDTO();

        subscriberService.employeeBatchRegulatoryValidatedMessageReceiver(payload, message);

        Mockito.verify(message).ack();
    }

    @Test
    void shouldNackMessageWhenDeliveryAttemptsAreBelowMaxRetries() {
        var payload = buildBatchRegulatoryDTO();

        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .putAttributes("googclient_deliveryattempt", "1").build();

        Mockito.when(message.getPubsubMessage()).thenReturn(pubsubMessage);
        Mockito.doThrow(new RuntimeException("Exception")).when(message).ack();

        subscriberService.employeeBatchRegulatoryValidatedMessageReceiver(payload, message);

        Mockito.verify(message).nack();
    }

    @Test
    void shouldPublishToDlqAndAckWhenDeliveryAttemptsExceedMaxRetries() {
        AtomicInteger count = new AtomicInteger(0);
        var payload = buildBatchRegulatoryDTO();

        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .putAttributes("googclient_deliveryattempt", "5").build();

        Mockito.when(message.getPubsubMessage()).thenReturn(pubsubMessage);

        Mockito.doAnswer(i -> {
            if (Objects.equals(count.getAndIncrement(), 0)) {
                throw new RuntimeException("Exception");
            }
            return null;
        }).when(message).ack();

        subscriberService.employeeBatchRegulatoryValidatedMessageReceiver(payload, message);

        Mockito.verify(pubSubTemplate).publish(Mockito.eq(DLQ_TOPIC_ID), Mockito.eq(payload));
        Mockito.verify(message, Mockito.times(2)).ack();
    }

    @NotNull
    private static BatchRegulatoryDTO buildBatchRegulatoryDTO() {
        Map<String, String> result = new HashMap<>();
        result.put("fraud", "Approved");
        result.put("compliance", "Pending");
        return new BatchRegulatoryDTO("APC", "123654789", result, "Pending");
    }
}