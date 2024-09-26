package br.com.madlib.madlib_gcp_template.api.controller;

import br.com.madlib.madlib_gcp_template.domain.model.Tax;
import br.com.madlib.madlib_gcp_template.domain.service.TaxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TaxController {

    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping
    public ResponseEntity<Tax> save(@RequestBody Tax tax) {
        return ResponseEntity.ok(taxService.save(tax));
    }
}
