package org.product.command.service.subscriber;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.product.command.service.aggregate.ProductAggregate;
import org.product.command.service.command.DeleteProductCommand;
import org.product.domain.service.event.ProductDeletionRequestedEvent;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "productCommandEventHandlers")
public class ProductCommandEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> delete(
            EventHandlerContext<ProductDeletionRequestedEvent> eventEventHandlerContext) {
        return eventEventHandlerContext.update(
                ProductAggregate.class,
                eventEventHandlerContext.getEvent().getProductId(),
                new DeleteProductCommand()
        );
    }
}