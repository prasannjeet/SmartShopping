package org.cart.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.cart.command.service.command.CartCommand;
import org.cart.command.service.command.CreateCartCommand;
import org.cart.command.service.command.DeleteCartCommand;
import org.cart.command.service.command.UpdateCartCommand;
import org.cart.domain.service.event.CartCreatedEvent;
import org.cart.domain.service.event.CartDeletedEvent;
import org.cart.domain.service.event.CartUpdatedEvent;
import org.cart.domain.service.model.Cart;

import java.util.Collections;
import java.util.List;

public class CartAggregate extends ReflectiveMutableCommandProcessingAggregate<CartAggregate, CartCommand> {

    private Cart cart;
    private boolean deleted;

    public List<Event> process(CreateCartCommand createCartCommand) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartCreatedEvent(createCartCommand.getCart()));
    }

    public List<Event> process(UpdateCartCommand updateCartCommand) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartUpdatedEvent(updateCartCommand.getCart()));
    }

    public List<Event> process(DeleteCartCommand deleteCartCommand) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartDeletedEvent());
    }

    public void apply(CartCreatedEvent event) {
        this.cart = event.getCart();
    }

    public void apply(CartUpdatedEvent event) {
        this.cart = event.getCart();
    }

    public void apply(CartDeletedEvent event) {
        this.deleted = true;
    }

    public Cart getCart() {
        return this.cart;
    }
}
