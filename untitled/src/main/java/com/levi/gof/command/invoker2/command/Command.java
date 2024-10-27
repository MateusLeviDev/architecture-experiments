package com.levi.gof.command.invoker2.command;

public interface Command {

    int CHECK_CREDENTIALS = 1;
    int CREATE_OWNER = 2;
    int DELETE_OWNER = 3;
    int GET_OWNER = 4;
    int GET_OWNER_LIST = 5;

    Object execute();
}
