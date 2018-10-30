package org.cart.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.cart.domain.service.event.ProductAddedEvent;
import org.cart.domain.service.event.ProductDeletedEvent;
import org.cart.domain.service.event.ProductUpdatedEvent;
import org.cart.domain.service.model.Cart;
import org.cart.domain.service.model.Product;
import org.cart.query.service.service.QueryService;
import org.user.domain.service.event.UserCreatedEvent;
import org.user.domain.service.event.UserDeletedEvent;

@EventSubscriber(id = "cartQueryEventHandler")
public class QueryEventSubscriber {

    private QueryService queryService;

    public QueryEventSubscriber(QueryService queryService) {
        this.queryService = queryService;
    }

    @EventHandlerMethod
    public void saveCart(DispatchedEvent<UserCreatedEvent> event) {
        this.queryService.saveCart(new Cart(event.getEntityId()));
    }

    @EventHandlerMethod
    public void deleteCart(DispatchedEvent<UserDeletedEvent> event) {
        this.queryService.delete(event.getEntityId());
    }

    @EventHandlerMethod
    public void saveProduct(DispatchedEvent<ProductAddedEvent> event) throws Exception {
        this.queryService.saveProduct(new Product(event.getEntityId(), event.getEvent().getProduct()));
    }

    @EventHandlerMethod
    public void updateProductQuantity(DispatchedEvent<ProductUpdatedEvent> event) throws Exception {
        this.queryService.saveProduct(new Product(event.getEntityId(), event.getEvent().getProduct()));
    }

    @EventHandlerMethod
    public void deleteProductFromCart(DispatchedEvent<ProductDeletedEvent> event) {
        this.queryService.deleteProduct(event.getEntityId());
    }

   /* @EventHandlerMethod
    public void updateProductInfo() {
        // from product service
    }

    @EventHandlerMethod
    public void deleteProducts() {
        // from product service
    }
    */


}
