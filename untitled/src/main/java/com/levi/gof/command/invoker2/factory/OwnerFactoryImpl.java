package com.levi.gof.command.invoker2.factory;

import com.levi.gof.command.invoker2.Owner;

public class OwnerFactoryImpl implements EntityFactory<Owner> {

    @Override
    public Owner create() {
        return new Owner(1L, "Naruto", "uzumaki69@gmail.com", "123");
    }
}
