package org.cart.command.service.service;

import org.cart.domain.service.model.Cart;
import org.cart.domain.service.repository.CartRepository;

import java.util.Optional;

public class CartQueryService {

    private CartRepository cartRepository;

    public CartQueryService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart findByUserId(String userId) {
        return Optional.of(this.cartRepository.findByUserId(userId)).get();
    }
}
