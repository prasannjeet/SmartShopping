package org.gateway.command.service.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.gateway.command.service.service.CommandService;
import org.user.domain.model.User;

import javax.validation.Valid;

import java.net.InetAddress;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/")
@ResponseBody
public class Controller {

    private CommandService commandService;

    public Controller(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public CompletableFuture<User> createUser(@RequestBody @Valid User user) throws Exception {
        return this.commandService
                .createUser(user)
                .thenApply(entity -> new User(entity.getEntityId(), entity.getAggregate().getUser()));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public CompletableFuture<User> deleteUser(@NotBlank @PathVariable String id) throws NoSuchElementException {
        return this.commandService
                .deleteUser(id)
                .thenApply(entity -> new User(entity.getEntityId(), entity.getAggregate().getUser()));
    }    
}
