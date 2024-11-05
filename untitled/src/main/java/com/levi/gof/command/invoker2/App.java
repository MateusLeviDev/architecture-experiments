package com.levi.gof.command.invoker2;

import com.levi.gof.command.invoker2.command.Command;
import com.levi.gof.command.invoker2.command.CommandFactory;
import com.levi.gof.command.invoker2.factory.EntityFactory;

public class App {
    public static void main(String[] args) {
        OwnerCredentialsDTO credentialsDTO = new OwnerCredentialsDTO("123", "naruto@example.com");

        EntityFactory entityFactory = new EntityFactory();
        OwnerCredentialsDTO ownerCredentialsDTO = entityFactory.create(credentialsDTO);
        System.out.println(ownerCredentialsDTO);

        CommandFactory commandFactory = new CommandFactory();

        Command checkCommand = commandFactory.create(Command.CHECK_CREDENTIALS, credentialsDTO);

        Object execute = checkCommand.execute();
        System.out.println(execute);

    }
}
