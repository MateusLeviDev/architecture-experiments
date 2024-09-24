package br.com.madlib.madlib_gcp_template.api.service;

import br.com.madlib.madlib_gcp_template.api.dto.TaxDTO;
import br.com.madlib.madlib_gcp_template.domain.model.Tax;
import br.com.madlib.madlib_gcp_template.domain.repository.TaxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaxService {

    private final TaxRepository taxRepository;

    public void processMessage(final TaxDTO taxDTO) {
        getTax(taxDTO);
    }

    public void getTax(final TaxDTO taxDTO) {
        final var tax = taxRepository.findById(taxDTO.id())
                .orElseThrow(
                        () -> {
                            final var err = "ID not found: " + taxDTO.id();
                            return new ObjectRetrievalFailureException(Tax.class, err);
                        }
                );

        tax.setName(taxDTO.name());
        tax.setDescription(taxDTO.description());
        taxRepository.save(tax);
    }
}
