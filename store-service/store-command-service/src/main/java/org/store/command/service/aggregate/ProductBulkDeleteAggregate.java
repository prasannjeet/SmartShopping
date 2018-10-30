package org.store.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.store.command.service.command.DeleteProductsCommand;
import org.store.command.service.command.ProductCommand;
import org.store.domain.service.event.ProductDeletionRequestedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class ProductBulkDeleteAggregate
        extends ReflectiveMutableCommandProcessingAggregate<ProductBulkDeleteAggregate, ProductCommand> {

    public List<Event> process(DeleteProductsCommand command) {
        return command.getIds()
                .stream()
                .map(ProductDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(ProductDeletionRequestedEvent event) {

    }
}
