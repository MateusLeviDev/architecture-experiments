package br.com.madlib.madlib_gcp_template.domain.service;

import br.com.madlib.madlib_gcp_template.api.dto.TaxDTO;
import br.com.madlib.madlib_gcp_template.domain.model.Tax;
import br.com.madlib.madlib_gcp_template.domain.repository.TaxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaxService {

    private final TaxRepository taxRepository;
    private final String taxTopicId;
    private final PubSubPublisherService pubSubPublisherService;
    private final ObjectMapper objectMapper;

    public TaxService(
            final TaxRepository taxRepository,
            @Value("${spring.cloud.gcp.pubsub.topic}") final String taxTopicId,
            PubSubPublisherService pubSubPublisherService, ObjectMapper objectMapper) {
        this.taxRepository = taxRepository;
        this.taxTopicId = taxTopicId;
        this.pubSubPublisherService = pubSubPublisherService;
        this.objectMapper = objectMapper;
    }

    public Tax save(final Tax tax) {
        final var savedTax = taxRepository.save(tax);
        pubSubPublisherService.publishMessage(getMessage(savedTax), taxTopicId);

        return savedTax;
    }

    private String getMessage(final Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
