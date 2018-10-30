package org.store.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.store.command.service.command.CreateProductCommand;
import org.store.command.service.command.DeleteProductCommand;
import org.store.command.service.command.ProductCommand;
import org.store.command.service.command.UpdateProductCommand;
import org.store.domain.service.event.ProductCreatedEvent;
import org.store.domain.service.event.ProductDeletedEvent;
import org.store.domain.service.event.ProductUpdatedEvent;
import org.store.domain.service.model.Product;

import java.util.Collections;
import java.util.List;

public class ProductAggregate extends ReflectiveMutableCommandProcessingAggregate<ProductAggregate, ProductCommand> {

    private Product product;
    private boolean deleted;

    public List<Event> process(CreateProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new ProductCreatedEvent(command.getProduct()));
    }

    public List<Event> process(UpdateProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new ProductUpdatedEvent(command.getProduct()));
    }

    public List<Event> process(DeleteProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new ProductDeletedEvent());
    }

    public void apply(ProductCreatedEvent event) {
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

