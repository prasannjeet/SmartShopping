package org.cart.query.service.service;

import org.cart.domain.service.dao.CartDao;
import org.cart.domain.service.model.Cart;
import org.cart.domain.service.repository.CartRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CartQueryService {

    private CartRepository cartRepository;
    private ProductQueryService productQueryService;

    public CartQueryService(CartRepository cartRepository, ProductQueryService productQueryService) {
        this.cartRepository = cartRepository;
        this.productQueryService = productQueryService;
    }

    public List<CartDao> findAll() {
        List<Cart> carts = this.cartRepository.findAll();
        List<CartDao> cartDaos = new LinkedList<>();
        carts.forEach(cart -> cartDaos.add(new CartDao(cart, this.productQueryService.findByUserId(cart.getUserId()))));
        return cartDaos;
    }

    public CartDao findByUserId(String userId) {
        Cart cart = Optional
                .of(this.cartRepository.findByUserId(userId))
                .orElseThrow(() -> new NoSuchElementException("No cart with userId = " + userId));
        return new CartDao(cart, this.productQueryService.findByUserId(cart.getUserId()));
    }

    public void save(Cart cart) {
        this.cartRepository.save(cart);
    }

    public void delete(String id) {
        this.cartRepository.delete(id);
    }

    public void deleteAll() {
        this.cartRepository.deleteAll();
    }
}
