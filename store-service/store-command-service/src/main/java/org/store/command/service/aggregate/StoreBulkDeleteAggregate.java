package org.store.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.store.command.service.command.StoreCommand;
import org.store.command.service.command.DeleteStoresCommand;
import org.store.domain.service.event.StoreDeletionRequestedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class StoreBulkDeleteAggregate
        extends ReflectiveMutableCommandProcessingAggregate<StoreBulkDeleteAggregate, StoreCommand> {

    public List<Event> process(DeleteStoresCommand command) {
        return command.getIds()
                .stream()
                .map(StoreDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(StoreDeletionRequestedEvent event) {

    }
}
