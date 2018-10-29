package org.store.command.service.subscriber;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.store.command.service.aggregate.StoreAggregate;
import org.store.command.service.command.DeleteStoreCommand;
import org.store.domain.service.event.StoreDeletionRequestedEvent;

import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "storeCommandEventHandlers")
public class StoreCommandEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<StoreAggregate>>
    delete(EventHandlerContext<StoreDeletionRequestedEvent> eventEventHandlerContext) {
        return eventEventHandlerContext.update(
                StoreAggregate.class,
                eventEventHandlerContext.getEvent().getStoreId(),
                new DeleteStoreCommand()
        );
    }
}
