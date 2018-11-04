package org.cart.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.cart.command.service.command.*;
import org.cart.domain.dao.CartDao;
import org.cart.domain.event.CartEventProductAdded;
import org.cart.domain.event.CartEventProductDeleted;
import org.cart.domain.event.CartEventProductQuantityUpdated;
import org.cart.domain.event.CartEventSortingStarted;
import org.cart.domain.model.Product;

import java.util.Collections;
import java.util.List;

public class CartAggregate extends ReflectiveMutableCommandProcessingAggregate<CartAggregate, CartCommand> {

    private Product product;
    private boolean deleted;
    private CartDao cartDao;

    public List<Event> process(AddProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartEventProductAdded(command.getProduct()));
    }

    public List<Event> process(UpdateProductQuantityCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartEventProductQuantityUpdated(command.getProduct()));
    }

    public List<Event> process(DeleteProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartEventProductDeleted(command.getProduct()));
    }

    public List<Event> process(SortCartCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartEventSortingStarted(command.getCartDao()));
    }

    public void apply(CartEventProductAdded event) {
        this.product = event.getProduct();
    }

    public void apply(CartEventProductQuantityUpdated event) {
        this.product = event.getProduct();
    }

    public void apply(CartEventProductDeleted event) {
        this.deleted = true;
    }

    public void apply(CartEventSortingStarted event) {
        this.cartDao = event.getCartDao();
    }

    public Product getProduct() {
        return this.product;
    }

    public CartDao getCartDao() {
        return this.cartDao;
    }
}

