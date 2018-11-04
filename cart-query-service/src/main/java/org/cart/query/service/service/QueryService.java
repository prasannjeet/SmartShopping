package org.cart.query.service.service;

import org.cart.domain.dao.CartResponseDao;
import org.cart.domain.model.Cart;
import org.cart.domain.repository.CartRepository;
import org.cart.domain.repository.ProductRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class QueryService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public QueryService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<CartResponseDao> findAll() {
        List<CartResponseDao> cartResponseDaos = new LinkedList<>();
        List<Cart> carts = this.cartRepository.findAll();
        carts.forEach(cart ->
                cartResponseDaos.add(
                        new CartResponseDao(cart.getUserId(), this.productRepository.findByUserId(cart.getUserId()))
                ));
        return cartResponseDaos;
    }

    public CartResponseDao findByUserId(String userId) {
        Cart cart = Optional
                .of(this.cartRepository.findByUserId(userId))
                .orElseThrow(() -> new NoSuchElementException("No cart with userId = " + userId));
        return new CartResponseDao(cart.getUserId(), this.productRepository.findByUserId(cart.getUserId()));
    }
}
