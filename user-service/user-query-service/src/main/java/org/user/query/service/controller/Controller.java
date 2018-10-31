package org.user.query.service.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.user.domain.service.model.User;
import org.user.query.service.service.UserQueryService;

@RestController
@RequestMapping(value="/user",produces="application/json")
@ResponseBody
public class Controller {
	private UserQueryService userQueryService;
	
	public  Controller (UserQueryService userQueryService) {
		this.userQueryService=userQueryService;
	}
	
	@GetMapping
	public CompletableFuture<List<User>> findAllUsers() {
        return CompletableFuture
                .supplyAsync(() -> this.userQueryService.findAllUsers());
}
	
	
	@GetMapping("/{id}")
    public CompletableFuture<User> findUserById(@PathVariable @NotBlank String id) {
        return CompletableFuture
                .supplyAsync(() -> this.userQueryService.findUserById(id));
}

}
