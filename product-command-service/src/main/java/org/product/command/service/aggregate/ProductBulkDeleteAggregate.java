package org.product.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.product.command.service.command.ProductCommand;
import org.product.command.service.command.DeleteProductsCommand;
import org.product.domain.service.event.ProductDeletionRequestedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class ProductBulkDeleteAggregate extends
        ReflectiveMutableCommandProcessingAggregate<ProductBulkDeleteAggregate, ProductCommand> {

    public List<Event> process(DeleteProductsCommand deleteProductsCommand) {
        return deleteProductsCommand.getIds().stream()
                .map(ProductDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(ProductDeletionRequestedEvent productDeletionRequestedEvent) {

    }
}