package org.store.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.store.domain.service.event.ProductCreatedEvent;
import org.store.domain.service.event.ProductDeletedEvent;
import org.store.domain.service.event.ProductUpdatedEvent;
import org.store.domain.service.model.Product;
import org.store.query.service.service.ProductQueryService;

@EventSubscriber(id = "productQueryEventHandlers")
public class ProductQueryEventSubscriber {

    private ProductQueryService productQueryService;

    public ProductQueryEventSubscriber(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<ProductCreatedEvent> dispatchedEvent) throws Exception {
        Product product = new Product(dispatchedEvent.getEvent().getProduct());
        product.setId(dispatchedEvent.getEntityId());
        this.productQueryService.save(product);
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<ProductUpdatedEvent> dispatchedEvent) throws Exception {
        Product product = new Product(dispatchedEvent.getEvent().getProduct());
        product.setId(dispatchedEvent.getEntityId());
        this.productQueryService.save(product);

    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<ProductDeletedEvent> dispatchedEvent) {
        this.productQueryService.delete(dispatchedEvent.getEntityId());
    }
}
