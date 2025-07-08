package com.levi.architecture.event.soucing.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@RequiredArgsConstructor
public abstract class DomainEvent implements Serializable {

    private final long sequenceId;
    private final long createdTime;
    private final String eventClassName;
    private boolean realTime = true;

    /**
     * Process.
     */
    public abstract void process();
}
