package br.com.madlib.madlib_gcp_template.interfaces;

import br.com.madlib.madlib_gcp_template.dto.BatchRegulatoryDTO;

public interface MessageHandler {

    void process(BatchRegulatoryDTO payload);
}
