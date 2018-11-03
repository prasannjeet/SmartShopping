package org.cart.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.cart.command.service.command.AddProductCommand;
import org.cart.command.service.command.CartCommand;
import org.cart.command.service.command.DeleteProductCommand;
import org.cart.command.service.command.UpdateProductQuantityCommand;
import org.cart.domain.event.CartEventProductAdded;
import org.cart.domain.event.CartEventProductDeleted;
import org.cart.domain.event.CartEventProductQuantityUpdated;
import org.cart.domain.model.Product;

import java.util.Collections;
import java.util.List;

public class CartAggregate extends ReflectiveMutableCommandProcessingAggregate<CartAggregate, CartCommand> {

    private Product product;
    private boolean deleted;

    public List<Event> process(AddProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartEventProductAdded(command.getProduct()));
    }

    public List<Event> process(UpdateProductQuantityCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartEventProductQuantityUpdated(command.getProduct()));
    }

    public List<Event> process(DeleteProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new CartEventProductDeleted(command.getProduct()));
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

    public Product getProduct() {
        return this.product;
    }
}

