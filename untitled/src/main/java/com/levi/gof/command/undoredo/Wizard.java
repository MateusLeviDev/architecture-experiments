package com.levi.gof.command.undoredo;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;

@Slf4j
public class Wizard {

    private final Deque<Runnable> undoStack = new LinkedList<>();
    private final Deque<Runnable> redoStack = new LinkedList<>();

    public void castSpell(Runnable runnable) {
        runnable.run();
        undoStack.offerLast(runnable);
    }

    public void undoLastSpell() {
        if (!undoStack.isEmpty()) {
            var previousSpell = undoStack.pollLast();
            redoStack.offerLast(previousSpell);
            previousSpell.run();
        }
    }

    public void redoLastSpell() {
        if (!redoStack.isEmpty()) {
            var previousSpell = redoStack.pollLast();
            undoStack.offerLast(previousSpell);
            previousSpell.run();
        }
    }

    @Override
    public String toString() {
        return "Wizard";
    }
}
