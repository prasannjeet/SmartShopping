package org.user.command.service.controller;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.user.command.service.service.UserCommandService;

import org.user.domain.service.model.User;


@RestController
@RequestMapping(value = "/users", produces = "application/json")
@ResponseBody
public class controller  {

    private UserCommandService commandService;

    public controller (UserCommandService commandService) {
        this.commandService = commandService;
        
    }

    @PostMapping(consumes = "application/json")
    public CompletableFuture<User> createUser(@RequestBody @Valid User user) throws Exception {
        return this.commandService
                .save(user)
                .thenApply(entity -> new User(entity.getEntityId(), entity.getAggregate().getUser()));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<User> deleteUserById(@NotBlank @PathVariable String id) {
        return this.commandService
                .delete(id)
                .thenApply(entity -> new User(entity.getEntityId(), entity.getAggregate().getUser()));
}
}