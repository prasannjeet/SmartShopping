package org.cart.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.cart.command.service.command.CartCommand;
import org.cart.command.service.command.DeleteCartsCommand;
import org.cart.domain.service.event.CartDeletionRequestedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class CartBulkDeleteAggregate extends
        ReflectiveMutableCommandProcessingAggregate<CartBulkDeleteAggregate, CartCommand> {

    public List<Event> process(DeleteCartsCommand deleteCartsCommand) {
        return deleteCartsCommand.getIds().stream()
                .map(CartDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(CartDeletionRequestedEvent cartDeletionRequestedEvent) {

    }
}
