package br.com.madlib.madlib_gcp_template.controller;

import br.com.madlib.madlib_gcp_template.gateway.PubSubOutboundGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebAppController {

    @Autowired
    PubSubOutboundGateway gateway;

    @PostMapping("/publishMessage")
    public String publishMessage(@RequestBody String message) {
        gateway.sendToPubSub(message);
        return "Message sent successfully";
    }
}
