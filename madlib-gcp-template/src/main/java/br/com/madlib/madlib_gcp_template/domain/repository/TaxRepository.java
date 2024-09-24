package br.com.madlib.madlib_gcp_template.domain.repository;

import br.com.madlib.madlib_gcp_template.domain.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
