package com.levi.architecture.event.soucing.processor;

import com.levi.architecture.event.soucing.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * Base class for Journaling implementations.
 */
@Slf4j
public abstract class EventJournal {

    File file;

    /**
     * Write.
     *
     * @param domainEvent the domain event.
     */
    abstract void write(DomainEvent domainEvent);

    /**
     * Reset.
     */
    void reset() {
        if (file.delete()) {
            log.info("File cleared successfully............");
        }
    }

    /**
     * Read domain event.
     *
     * @return the domain event.
     */
    abstract DomainEvent readNext();
}
