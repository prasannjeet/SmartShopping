package org.product.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.product.command.service.command.ProductCommand;
import org.product.command.service.command.CreateProductCommand;
import org.product.command.service.command.DeleteProductCommand;
import org.product.command.service.command.UpdateProductCommand;
import org.product.domain.service.event.ProductCreatedEvent;
import org.product.domain.service.event.ProductDeletedEvent;
import org.product.domain.service.event.ProductUpdatedEvent;
import org.product.domain.service.model.Product;

import java.util.Collections;
import java.util.List;

public class ProductAggregate extends ReflectiveMutableCommandProcessingAggregate<ProductAggregate, ProductCommand> {

    private Product product;
    private boolean deleted;

    public List<Event> process(CreateProductCommand createProductCommand) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new ProductCreatedEvent(createProductCommand.getProduct()));
    }

    public List<Event> process(UpdateProductCommand updateProductCommand) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new ProductUpdatedEvent(updateProductCommand.getProduct()));
    }

    public List<Event> process(DeleteProductCommand deleteProductCommand) {
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