package br.com.madlib.madlib_gcp_template.handlers;

import br.com.madlib.madlib_gcp_template.dto.BatchRegulatoryDTO;
import br.com.madlib.madlib_gcp_template.interfaces.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("compliance")
@Slf4j
public class ComplianceHandler implements MessageHandler {
    @Override
    public void process(BatchRegulatoryDTO payload) {
        log.info("Processing ComplianceHandler");
    }
}
