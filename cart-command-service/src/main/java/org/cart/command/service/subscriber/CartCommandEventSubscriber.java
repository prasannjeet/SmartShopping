package org.cart.command.service.subscriber;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.command.DeleteCartCommand;
import org.cart.domain.service.event.CartDeletionRequestedEvent;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "cartCommandEventHandlers")
public class CartCommandEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>>
    delete(EventHandlerContext<CartDeletionRequestedEvent> eventEventHandlerContext) {
        return eventEventHandlerContext.update(
                CartAggregate.class,
                eventEventHandlerContext.getEvent().getCartId(),
                new DeleteCartCommand()
        );
    }
}
