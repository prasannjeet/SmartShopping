package org.cart.query.service.service;

import org.cart.domain.service.dao.CartDao;
import org.cart.domain.service.model.Cart;
import org.cart.domain.service.model.Product;
import org.cart.domain.service.repository.CartRepository;
import org.cart.domain.service.repository.ProductRepository;

import java.util.LinkedList;
import java.util.List;

public class QueryService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public QueryService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<CartDao> findAllCarts() {
        List<CartDao> cartDaos = new LinkedList<>();
        List<Cart> carts = this.cartRepository.findAll();
        carts.forEach(cart ->
                cartDaos.add(new CartDao(cart.getUserId(), this.productRepository.findByUserId(cart.getUserId()))));
        return cartDaos;
    }

    public CartDao findCartByUserId(String userId) {
        Cart cart = this.cartRepository.findByUserId(userId);
        return new CartDao(cart.getUserId(), this.productRepository.findByUserId(cart.getUserId()));
    }

    public void saveCart(Cart cart) {
        this.cartRepository.save(cart);
    }

    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    public void deleteProduct(String id) {
        this.productRepository.delete(id);
    }

    public void delete(String id) {
        Cart cart = this.cartRepository.findOne(id);
        this.cartRepository.delete(cart);
        this.productRepository.findByUserId(cart.getUserId())
                .forEach(product -> this.productRepository.delete(product));
    }

    public void deleteAll() {
        this.cartRepository.deleteAll();
        this.productRepository.deleteAll();
    }
}
