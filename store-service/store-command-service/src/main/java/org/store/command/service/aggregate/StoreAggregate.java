package org.store.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.store.command.service.command.StoreCommand;
import org.store.command.service.command.CreateStoreCommand;
import org.store.command.service.command.DeleteStoreCommand;
import org.store.command.service.command.UpdateStoreCommand;
import org.store.domain.service.event.StoreCreatedEvent;
import org.store.domain.service.event.StoreDeletedEvent;
import org.store.domain.service.event.StoreUpdatedEvent;
import org.store.domain.service.model.Store;

import java.util.Collections;
import java.util.List;

public class StoreAggregate extends ReflectiveMutableCommandProcessingAggregate<StoreAggregate, StoreCommand> {

    private Store store;
    private boolean deleted;

    public List<Event> process(CreateStoreCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreCreatedEvent(command.getStore()));
    }

    public List<Event> process(UpdateStoreCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreUpdatedEvent(command.getStore()));
    }

    public List<Event> process(DeleteStoreCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreDeletedEvent());
    }

    public void apply(StoreCreatedEvent event) {
        this.store = event.getStore();
    }

    public void apply(StoreUpdatedEvent event) {
        this.store = event.getStore();
    }

    public void apply(StoreDeletedEvent event) {
        this.deleted = true;
    }

    public Store getStore() {
        return this.store;
    }
}
