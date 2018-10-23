package org.cart.command.service.controller;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.service.CartCommandService;
import org.cart.command.service.service.CartQueryService;
import org.cart.domain.service.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.eventuate.EntityWithIdAndVersion;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
public class Controller {

	@Autowired
	private CartCommandService commandService;

	@Autowired
	private CartQueryService queryService;

	@PostMapping(consumes = "application/json")
	@ResponseBody
	public CompletableFuture<ResponseEntity<?>> create(@RequestBody @Valid Cart cart) {
		return this.commandService.create(cart).handle((res, err) -> {
			return this.handle(res, err, HttpStatus.CREATED, HttpStatus.BAD_REQUEST);
		});
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public CompletableFuture<ResponseEntity<?>> remove(@PathVariable String id) {
		return this.commandService.remove(id).handle((res, err) -> {
			return this.handle(res, err, HttpStatus.OK, HttpStatus.NOT_FOUND);
		});
	}

	@DeleteMapping("/users/{userId}")
	@ResponseBody
	public CompletableFuture<ResponseEntity<?>> removeByUserId(@PathVariable String userId) {
		return this.commandService.remove(this.queryService.findByUserId(userId).getId()).handle((res, err) -> {
			return this.handle(res, err, HttpStatus.OK, HttpStatus.NOT_FOUND);
		});
	}

	private ResponseEntity<?> handle(EntityWithIdAndVersion<CartAggregate> res, Throwable err, HttpStatus success,
			HttpStatus failure) {
		return ResponseEntity.status(err == null ? success : failure)
				.body(err == null ? this.findByUserId(res) : err.getMessage());
	}

	private Cart findByUserId(EntityWithIdAndVersion<CartAggregate> res) {
		return this.queryService.findByUserId(res.getAggregate().getCart().getUserId());
	}
}
