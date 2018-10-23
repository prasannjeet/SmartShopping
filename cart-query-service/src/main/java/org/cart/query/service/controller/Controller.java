package org.cart.query.service.controller;

import java.util.concurrent.CompletableFuture;

import org.cart.query.service.service.CartQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
public class Controller {

	@Autowired
	private CartQueryService service;

	@GetMapping
	public CompletableFuture<ResponseEntity<?>> findAll() {
		return CompletableFuture.supplyAsync(() -> this.service.findAll()).handle((res, err) -> {
			return ResponseEntity.status(err == null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
					.body(err == null ? res : err.getMessage());
		});
	}

	@GetMapping("/{id}")
	public CompletableFuture<ResponseEntity<?>> findOne(@PathVariable String id) {
		return CompletableFuture.supplyAsync(() -> this.service.findOne(id)).handle((res, err) -> {
			return ResponseEntity.status(err == null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
					.body(err == null ? res : err.getMessage());
		});
	}

	@GetMapping("/users/{userId}")
	public CompletableFuture<ResponseEntity<?>> findByUserId(@PathVariable String userId) {
		return CompletableFuture.supplyAsync(() -> this.service.findByUserId(userId)).handle((res, err) -> {
			return ResponseEntity.status(err == null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
					.body(err == null ? res : err.getMessage());
		});
	}
}
