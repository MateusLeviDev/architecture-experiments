package br.com.madlib.madlib_gcp_template.handlers;

import br.com.madlib.madlib_gcp_template.dto.BatchRegulatoryDTO;
import br.com.madlib.madlib_gcp_template.interfaces.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class HandlerRegistry {

    private final Map<String, MessageHandler> handlers;

    public HandlerRegistry(Map<String, MessageHandler> handlers) {
        this.handlers = handlers;
    }

    public void dispatch(BatchRegulatoryDTO payload) {
        MessageHandler handler = handlers.get(payload.source());
        if (Objects.nonNull(handler)) {
            handler.process(payload);
        } else {
            log.error("Resource Not Found");
        }
    }
}

