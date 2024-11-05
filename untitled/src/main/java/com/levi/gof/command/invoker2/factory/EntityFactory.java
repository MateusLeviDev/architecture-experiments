package com.levi.gof.command.invoker2.factory;

public class EntityFactory {

    public <T> T create(T entity) {
        return entity;
    }
}
