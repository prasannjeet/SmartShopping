package org.cart.command.service.subscriber;

import java.util.concurrent.CompletableFuture;

import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.command.DeleteCartCommand;
import org.cart.domain.service.event.CartDeletionRequestedEvent;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id = "cartCommandSideEventHandlers")
public class CartCommandEventSubscriber {

	@EventHandlerMethod
	public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> deleteCart(
			EventHandlerContext<CartDeletionRequestedEvent> context) {

		return context.update(CartAggregate.class, context.getEvent().getCartId(), new DeleteCartCommand());
	}
}
