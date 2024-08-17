package com.ms.producer.service.impl;

import com.ms.core.domain.ProductCreatedEvent;
import com.ms.producer.domain.ProductRequestDTO;
import com.ms.producer.service.ProductService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(ProductRequestDTO productRequestDTO) {
        String productId = UUID.randomUUID().toString();

        // TODO: Persist into db before publishing an event

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, productRequestDTO.title(),
                productRequestDTO.price(), productRequestDTO.quantity());

        //this will send a message to kafka topic asynchronously, and it will not wait for acknowledgement
        //we can use CompletableFuture class, to represents a future result sync
        kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);

        return productId;
    }
}
