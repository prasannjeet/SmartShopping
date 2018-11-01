package org.user.command.service.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.user.command.service.service.CommandService;
import org.user.domain.service.model.User;
import org.user.domain.service.repository.UserRepository;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
@ResponseBody
public class Controller {

    private CommandService commandService;
    private UserRepository userRepository;

    public Controller(CommandService commandService, UserRepository userRepository) {
        this.commandService = commandService;
        this.userRepository = userRepository;
    }

    @PostMapping(consumes = "application/json")
    public CompletableFuture<User> createUser(@RequestBody @Valid User user) throws Exception {
        if (this.userRepository.isDuplicate(user)) {
            throw new Exception("Duplicate user with username = " + user.getUsername());
        }
        return this.commandService
                .save(user)
                .thenApply(entity -> new User(entity.getEntityId(), entity.getAggregate().getUser()));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<User> deleteUserById(@NotBlank @PathVariable String id) {
        return this.commandService
                .delete(
                        Optional
                                .of(this.userRepository.findOne(id))
                                .orElseThrow(() -> new NoSuchElementException("No user with id = " + id))
                )
                .thenApply(entity -> new User(entity.getEntityId(), entity.getAggregate().getUser()));
    }
}
