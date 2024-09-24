package br.com.madlib.madlib_gcp_template.infrastructure.pubsub;

import br.com.madlib.madlib_gcp_template.api.dto.TaxDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Slf4j
@Configuration
public class PubSubConfig {

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("inputChannel") final MessageChannel inputChannel, final PubSubTemplate pubSubTemplate) {
        final PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(
                pubSubTemplate, "MySub");
        adapter.setOutputChannel(inputChannel);
        adapter.setPayloadType(TaxDTO.class);
        adapter.setAckMode(AckMode.MANUAL);

        return adapter;
    }

    @Bean
    public JacksonPubSubMessageConverter jacksonPubSubMessageConverter() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new JacksonPubSubMessageConverter(objectMapper);
    }

}

