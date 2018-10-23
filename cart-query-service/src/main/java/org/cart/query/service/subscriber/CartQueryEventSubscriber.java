package org.cart.query.service.subscriber;

import org.cart.domain.service.event.CartCreatedEvent;
import org.cart.domain.service.event.CartDeletedEvent;
import org.cart.domain.service.event.CartUpdatedEvent;
import org.cart.domain.service.model.Cart;
import org.cart.query.service.service.CartQueryService;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id = "cartQuerySideEventHandlers")
public class CartQueryEventSubscriber {

	private CartQueryService service;

	public CartQueryEventSubscriber(CartQueryService service) {
		this.service = service;
	}

	@EventHandlerMethod
	public void create(DispatchedEvent<CartCreatedEvent> event) {
		Cart cart = new Cart(event.getEvent().getCart());
		cart.setId(event.getEntityId());
		this.service.save(cart);
	}

	@EventHandlerMethod
	public void delete(DispatchedEvent<CartDeletedEvent> event) {
		this.service.delete(event.getEntityId());
	}

	@EventHandlerMethod
	public void update(DispatchedEvent<CartUpdatedEvent> event) {
		Cart cart = new Cart(event.getEvent().getCart());
		cart.setId(event.getEntityId());
		this.service.save(cart);
	}
}
