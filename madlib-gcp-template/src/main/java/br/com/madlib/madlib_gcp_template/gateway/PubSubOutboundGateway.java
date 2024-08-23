package br.com.madlib.madlib_gcp_template.gateway;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "pubsubGateway", defaultRequestChannel = "pubsubOutputChannel")
public interface PubSubOutboundGateway {

    void sendToPubSub(String message);
}
