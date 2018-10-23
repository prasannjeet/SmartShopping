package org.cart.query.service.service;

import java.util.List;
import java.util.Optional;

import org.cart.domain.service.model.Cart;
import org.cart.domain.service.repository.CartRepository;

public class CartQueryService {

	private CartRepository repository;

	public CartQueryService(CartRepository repository) {
		this.repository = repository;
	}

	public List<Cart> findAll() {
		return this.repository.findAll();
	}

	public Cart findOne(String id) {
		return Optional.of(this.repository.findOne(id)).get();
	}

	public Cart findByUserId(String userId) {
		return Optional.of(this.repository.findByUserId(userId)).get();
	}

	public Cart save(Cart cart) {
		return this.repository.save(cart);
	}

	public void delete(String id) {
		this.repository.delete(this.findOne(id));
	}

	public void deleteAll() {
		this.repository.deleteAll();
	}
}
