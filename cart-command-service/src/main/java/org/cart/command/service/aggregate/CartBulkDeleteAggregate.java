package org.cart.command.service.aggregate;

import java.util.List;
import java.util.stream.Collectors;

import org.cart.command.service.command.CartCommand;
import org.cart.command.service.command.DeleteCartsCommand;
import org.cart.domain.service.event.CartDeletionRequestedEvent;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class CartBulkDeleteAggregate
		extends ReflectiveMutableCommandProcessingAggregate<CartBulkDeleteAggregate, CartCommand> {

	public List<Event> process(DeleteCartsCommand command) {
		return command.getIds().stream().map(CartDeletionRequestedEvent::new).collect(Collectors.toList());
	}

	public void apply(CartDeletionRequestedEvent event) {

	}
}
