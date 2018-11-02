package org.user.query.service.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.user.domain.service.model.User;
import org.user.query.service.service.QueryService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
@ResponseBody
public class Controller {

    private QueryService queryService;

    public Controller(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public CompletableFuture<List<User>> findAllUsers() {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findAllUsers());
    }

    @GetMapping("/{id}")
    public CompletableFuture<User> findUserById(@PathVariable @NotBlank String id) {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findUserById(id));
    }
}
