package com.levi.gof.command.invoker2.command;

import com.levi.gof.command.invoker2.OwnerCredentialsDTO;

public class CommandFactory {

    public Command create(int commandCode, Object... params) {
        switch (commandCode) {
            case Command.CHECK_CREDENTIALS:
                    return new CheckCredentialsCommand((OwnerCredentialsDTO) params[0]);
            default:
                return null;
        }
    }
}
