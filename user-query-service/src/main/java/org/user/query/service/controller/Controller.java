package org.user.query.service.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.user.domain.model.User;
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
    public CompletableFuture<List<User>> findAll() {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findAll());
    }

    @GetMapping("/{id}")
    public CompletableFuture<User> findById(@PathVariable @NotBlank String id) {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findById(id));
    }
}
