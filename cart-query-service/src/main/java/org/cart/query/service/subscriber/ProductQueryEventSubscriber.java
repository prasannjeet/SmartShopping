package org.cart.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.cart.domain.service.event.ProductCreatedEvent;
import org.cart.domain.service.event.ProductDeletedEvent;
import org.cart.domain.service.event.ProductUpdatedEvent;
import org.cart.domain.service.model.Product;
import org.cart.query.service.service.ProductQueryService;

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
