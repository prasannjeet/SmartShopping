package org.cart.command.service.subscriber;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.cart.command.service.aggregate.ProductAggregate;
import org.cart.command.service.command.DeleteProductCommand;
import org.cart.domain.service.event.ProductDeletionRequestedEvent;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "productCommandEventHandlers")
public class ProductCommandEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>>
    delete(EventHandlerContext<ProductDeletionRequestedEvent> eventEventHandlerContext) {
        return eventEventHandlerContext.update(
                ProductAggregate.class,
                eventEventHandlerContext.getEvent().getProductId(),
                new DeleteProductCommand()
        );
    }
}
