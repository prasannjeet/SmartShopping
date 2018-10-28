package org.cart.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.cart.domain.service.event.CartCreatedEvent;
import org.cart.domain.service.event.CartDeletedEvent;
import org.cart.domain.service.event.CartUpdatedEvent;
import org.cart.domain.service.model.Cart;
import org.cart.query.service.service.CartQueryService;

@EventSubscriber(id = "cartQueryEventHandlers")
public class CartQueryEventSubscriber {

    private CartQueryService cartQueryService;

    public CartQueryEventSubscriber(CartQueryService cartQueryService) {
        this.cartQueryService = cartQueryService;
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<CartCreatedEvent> dispatchedEvent) {
        Cart cart = new Cart(dispatchedEvent.getEvent().getCart());
        cart.setId(dispatchedEvent.getEntityId());
        this.cartQueryService.save(cart);
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<CartUpdatedEvent> dispatchedEvent) {
        Cart cart = new Cart(dispatchedEvent.getEvent().getCart());
        cart.setId(dispatchedEvent.getEntityId());
        this.cartQueryService.save(cart);

    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<CartDeletedEvent> dispatchedEvent) {
        this.cartQueryService.delete(dispatchedEvent.getEntityId());
    }
}
