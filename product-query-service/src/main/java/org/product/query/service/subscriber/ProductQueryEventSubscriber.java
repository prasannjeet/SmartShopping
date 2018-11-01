package org.product.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.product.domain.service.event.ProductCreatedEvent;
import org.product.domain.service.event.ProductDeletedEvent;
import org.product.domain.service.event.ProductUpdatedEvent;
import org.product.domain.service.model.Product;
import org.product.query.service.service.ProductQueryService;

@EventSubscriber(id = "productQueryEventHandlers")
public class ProductQueryEventSubscriber {

    private ProductQueryService productQueryService;

    public ProductQueryEventSubscriber(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<ProductCreatedEvent> dispatchedEvent) {
        Product product = new Product(dispatchedEvent.getEvent().getProduct().getBarcode(), dispatchedEvent.getEvent().getProduct().getBrand(), dispatchedEvent.getEvent().getProduct().getName());
        product.setId(dispatchedEvent.getEntityId());
        this.productQueryService.save(product);
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<ProductUpdatedEvent> dispatchedEvent) {
    		Product product = new Product(dispatchedEvent.getEvent().getProduct().getBarcode(), dispatchedEvent.getEvent().getProduct().getBrand(), dispatchedEvent.getEvent().getProduct().getName());
    		product.setId(dispatchedEvent.getEntityId());
    		this.productQueryService.save(product);
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<ProductDeletedEvent> dispatchedEvent) {
        this.productQueryService.delete(dispatchedEvent.getEntityId());
    }
}