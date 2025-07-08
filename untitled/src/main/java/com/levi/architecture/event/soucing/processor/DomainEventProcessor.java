package com.levi.architecture.event.soucing.processor;


import com.levi.architecture.event.soucing.event.DomainEvent;


public class DomainEventProcessor {

    private final EventJournal eventJournal;

    public DomainEventProcessor(EventJournal eventJournal) {
        this.eventJournal = eventJournal;
    }

    /**
     * Process.
     *
     * @param domainEvent the domain event
     */
    public void process(DomainEvent domainEvent) {
        domainEvent.process();
        eventJournal.write(domainEvent);
    }

    /**
     * Reset.
     */
    public void reset() {
        eventJournal.reset();
    }

    /**
     * Recover.
     */
    public void recover() {
        DomainEvent domainEvent;
        while ((domainEvent = eventJournal.readNext()) != null) {
            domainEvent.process();
        }
    }
}
