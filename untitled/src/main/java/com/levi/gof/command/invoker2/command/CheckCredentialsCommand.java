package com.levi.gof.command.invoker2.command;

import com.levi.gof.command.invoker2.Owner;
import com.levi.gof.command.invoker2.OwnerCredentialsDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CheckCredentialsCommand implements Command{

    private final OwnerCredentialsDTO credentialsDTO;

    public CheckCredentialsCommand(final OwnerCredentialsDTO credentialsDTO) {
        this.credentialsDTO = credentialsDTO;
    }

    @Override
    public Owner execute() {
        List<Owner> owners = List.of(
                new Owner(1L, "Naruto", "naruto@example.com", "123"),
                new Owner(2L, "Sasuke", "sasuke@example.com", "456")
        );
        log.info("Executing Check Credentials Command");

        return owners.stream()
                .filter(owner -> owner.email().equals(credentialsDTO.email()) && owner.password().equals(credentialsDTO.password()))
                .findFirst()
                .orElse(null);


    }
}
