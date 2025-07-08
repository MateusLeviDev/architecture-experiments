package com.levi.architecture.event.soucing.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi.architecture.event.soucing.event.AccountCreateEvent;
import com.levi.architecture.event.soucing.event.DomainEvent;
import com.levi.architecture.event.soucing.event.MoneyDepositEvent;
import com.levi.architecture.event.soucing.event.MoneyTransferEvent;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class JsonFileJournal extends EventJournal {

    private final List<String> events = new ArrayList<>();
    private int index = 0;

    /**
     * Instantiates a new Json file journal.
     */
    public JsonFileJournal() {
        file = new File("Journal.json");
        if (file.exists()) {
            try (var input =
                         new BufferedReader(
                                 new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
                String line;
                while ((line = input.readLine()) != null) {
                    events.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            reset();
        }
    }

    /**
     * Write.
     *
     * @param domainEvent the domain event
     */
    @Override
    public void write(DomainEvent domainEvent) {
        var mapper = new ObjectMapper();
        try (var output =
                     new BufferedWriter(
                             new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
            var eventString = mapper.writeValueAsString(domainEvent);
            output.write(eventString + "\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read the next domain event.
     *
     * @return the domain event
     */
    public DomainEvent readNext() {
        if (index >= events.size()) {
            return null;
        }
        var event = events.get(index);
        index++;

        var mapper = new ObjectMapper();
        DomainEvent domainEvent;
        try {
            var jsonElement = mapper.readTree(event);
            var eventClassName = jsonElement.get("eventClassName").asText();
            domainEvent =
                    switch (eventClassName) {
                        case "AccountCreateEvent" -> mapper.treeToValue(jsonElement, AccountCreateEvent.class);
                        case "MoneyDepositEvent" -> mapper.treeToValue(jsonElement, MoneyDepositEvent.class);
                        case "MoneyTransferEvent" -> mapper.treeToValue(jsonElement, MoneyTransferEvent.class);
                        default -> throw new RuntimeException("Journal Event not recognized");
                    };
        } catch (JsonProcessingException jsonProcessingException) {
            throw new RuntimeException("Failed to convert JSON");
        }

        domainEvent.setRealTime(false);
        return domainEvent;
    }
}
