package org.cart.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.cart.command.service.command.AddProductCommand;
import org.cart.command.service.command.DeleteProductCommand;
import org.cart.command.service.command.ProductCommand;
import org.cart.command.service.command.UpdateProductQuantityCommand;
import org.cart.domain.service.event.ProductAddedEvent;
import org.cart.domain.service.event.ProductDeletedEvent;
import org.cart.domain.service.event.ProductUpdatedEvent;
import org.cart.domain.service.model.Product;

import java.util.Collections;
import java.util.List;

public class ProductAggregate extends ReflectiveMutableCommandProcessingAggregate<ProductAggregate, ProductCommand> {

    private Product product;
    private boolean deleted;

    public List<Event> process(AddProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new ProductAddedEvent(command.getProduct()));
    }

    public List<Event> process(UpdateProductQuantityCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new ProductUpdatedEvent(command.getProduct()));
    }

    public List<Event> process(DeleteProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new ProductDeletedEvent());
    }

    public void apply(ProductAddedEvent event) {
        this.product = event.getProduct();
    }

    public void apply(ProductUpdatedEvent event) {
        this.product = event.getProduct();
    }

    public void apply(ProductDeletedEvent event) {
        this.deleted = true;
    }

    public Product getProduct() {
        return this.product;
    }
}

