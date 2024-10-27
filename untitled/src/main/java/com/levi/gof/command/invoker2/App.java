package com.levi.gof.command.invoker2;

import com.levi.gof.command.invoker2.command.Command;
import com.levi.gof.command.invoker2.command.CommandFactory;

public class App {
    public static void main(String[] args) {
        OwnerCredentialsDTO credentialsDTO = new OwnerCredentialsDTO("123", "naruto@example.com");

        CommandFactory commandFactory = new CommandFactory();

        Command checkCommand = commandFactory.create(Command.CHECK_CREDENTIALS, credentialsDTO);

        checkCommand.execute();

    }
}
