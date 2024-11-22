package com.orchidaceae.br.domain.service;

import com.orchidaceae.br.api.dto.request.ContractProductRequestDTO;
import com.orchidaceae.br.domain.gateway.ProductGateway;
import com.orchidaceae.br.domain.model.ContractProduct;
import com.orchidaceae.br.domain.repository.ContractProductRepository;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractProductService {

    private final ProductGateway productGateway;
    private final ContractProductRepository contractProductRepository;
    private final ContractService contractService;
    private final Processor processor;

    public ContractProductService(ProductGateway productGateway, ContractProductRepository contractProductRepository, ContractService contractService, Processor processor) {
        this.productGateway = productGateway;
        this.contractProductRepository = contractProductRepository;
        this.contractService = contractService;
        this.processor = processor;
    }

    public ContractProduct attachProductToContract(
            final Long contractId,
            final Long productId,
            final ContractProductRequestDTO contractProductRequestDTO) {
        final var foundProduct = productGateway.findProductById(productId);
        if (foundProduct.isEmpty()) {
            throw new IllegalArgumentException("Product not Found.");
        }

        final var foundContract = contractService.findById(contractId);

        if (findContractProductByProductId(productId).stream()
                .anyMatch(contractProduct -> contractProduct.getContract().getId().equals(contractId))) {
            throw new RuntimeException("Already Linked");
        }

        final var contractProduct = ContractProduct.builder()
                .contract(foundContract)
                .productId(foundProduct.get().id())
                .startDate(contractProductRequestDTO.startDate())
                .build();

        publishToKafka(contractProduct);

        return contractProductRepository.save(contractProduct);
    }

    private void publishToKafka(ContractProduct contractProduct) {
        Message<ContractProduct> message = MessageBuilder
                .withPayload(contractProduct)
                .build();

        boolean sent = processor.output().send(message);
        if (!sent) {
            throw new RuntimeException("Failed to send to Kafka");
        }
    }

    public List<ContractProduct> findContractProductByProductId(final Long productId) {
        return contractProductRepository.findContractProductByProductId(productId);
    }

    public ContractProduct findByContractIdAndProductId(final Long contractId, final Long productId) {
        return contractProductRepository.findByContractIdAndProductId(contractId, productId)
                .orElseThrow(() -> new RuntimeException("Link not found"));
    }
}
