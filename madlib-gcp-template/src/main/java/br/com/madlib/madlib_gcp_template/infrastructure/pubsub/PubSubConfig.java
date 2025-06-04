package br.com.madlib.madlib_gcp_template.infrastructure.pubsub;

import br.com.madlib.madlib_gcp_template.api.dto.BatchRegulatoryDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PubSubConfig {

    @Value("${spring.cloud.gcp.pubsub.subscriptions.employee-batch-regulatory-validated-sub}")
    private String employeeBatchRegulatoryValidatedSub;

    @Bean
    public MessageChannel employeeBatchRegulatoryValidatedInputChanel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel errorChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter employeeBatchRegulatoryValidatedChannelAdapter(
            @Qualifier("employeeBatchRegulatoryValidatedInputChanel") final MessageChannel employeeBatchRegulatoryValidatedInputChanel,
            final PubSubTemplate pubSubTemplate) {

        return createAdapter(
                pubSubTemplate,
                employeeBatchRegulatoryValidatedSub,
                employeeBatchRegulatoryValidatedInputChanel,
                BatchRegulatoryDTO.class);
    }

    private <T> PubSubInboundChannelAdapter createAdapter(
            final PubSubTemplate pubSubTemplate,
            final String subscriptionId,
            final MessageChannel inputChannel,
            final Class<T> payloadType) {

        final PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionId);
        adapter.setOutputChannel(inputChannel);
        adapter.setPayloadType(payloadType);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setErrorChannel(errorChannel());

        return adapter;
    }

    @Bean
    public JacksonPubSubMessageConverter jacksonPubSubMessageConverter() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new JacksonPubSubMessageConverter(objectMapper);
    }

}

