package org.cart.query.service.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.cart.domain.event.CartEventProductAdded;
import org.cart.domain.event.CartEventProductDeleted;
import org.cart.domain.event.CartEventProductQuantityUpdated;
import org.cart.domain.model.Cart;
import org.cart.domain.model.Product;
import org.cart.domain.repository.CartRepository;
import org.cart.domain.repository.ProductRepository;
import org.user.domain.event.UserEventUserCreated;
import org.user.domain.event.UserEventUserDeleted;

@EventSubscriber(id = "cartQueryEventHandler")
public class QueryEventSubscriber {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public QueryEventSubscriber(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @EventHandlerMethod
    public Cart createCart(DispatchedEvent<UserEventUserCreated> event) {
        System.out.println("Event came in cart query");
        if (!this.cartRepository.isDuplicate(event.getEntityId())) {
            Cart cart = this.cartRepository.save(new Cart(event.getEntityId()));
            try {
                System.out.println("Event returned a cart = " + new ObjectMapper().writeValueAsString(cart));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return cart;
        }

        System.out.println("Event returned null");
        return null;
    }

    @EventHandlerMethod
    public void deleteCart(DispatchedEvent<UserEventUserDeleted> event) {
        Cart cart = this.cartRepository.findByUserId(event.getEntityId());
        if (cart != null) {
            this.cartRepository.delete(cart);
            this.productRepository.findByUserId(cart.getUserId())
                    .forEach(product -> this.productRepository.delete(product));
        }
    }

    @EventHandlerMethod
    public void addProduct(DispatchedEvent<CartEventProductAdded> event) {
        try {
            this.productRepository.save(new Product(event.getEntityId(), event.getEvent().getProduct()));
        } catch (Exception e) {
            System.err.println("Cannot add product into cart. " + e.getMessage());
        }
    }

    @EventHandlerMethod
    public void updateProductQuantity(DispatchedEvent<CartEventProductQuantityUpdated> event) {
        try {
            this.productRepository.save(new Product(event.getEntityId(), event.getEvent().getProduct()));
        } catch (Exception e) {
            System.err.println("Cannot update product quantity. " + e.getMessage());
        }
    }

    @EventHandlerMethod
    public void deleteProduct(DispatchedEvent<CartEventProductDeleted> event) {
        this.productRepository.delete(event.getEntityId());
    }
}