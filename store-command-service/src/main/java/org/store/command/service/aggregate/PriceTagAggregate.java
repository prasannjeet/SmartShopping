package org.store.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import org.store.command.service.command.AddPriceTagCommand;
import org.store.command.service.command.DeletePriceTagCommand;
import org.store.command.service.command.PriceTagCommand;
import org.store.command.service.command.UpdatePriceTagCommand;
import org.store.domain.service.event.PriceTagAddedEvent;
import org.store.domain.service.event.PriceTagDeletedEvent;
import org.store.domain.service.event.PriceTagUpdatedEvent;
import org.store.domain.service.model.PriceTag;

import java.util.Collections;
import java.util.List;

public class PriceTagAggregate extends ReflectiveMutableCommandProcessingAggregate<PriceTagAggregate, PriceTagCommand> {

    private PriceTag priceTag;
    private boolean deleted;

    public List<Event> process(AddPriceTagCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new PriceTagAddedEvent(command.getPriceTag()));
    }

    public List<Event> process(UpdatePriceTagCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new PriceTagUpdatedEvent(command.getPriceTag()));
    }

    public List<Event> process(DeletePriceTagCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new PriceTagDeletedEvent());
    }

    public void apply(PriceTagAddedEvent event) {
        this.priceTag = event.getPriceTag();
    }

    public void apply(PriceTagUpdatedEvent event) {
        this.priceTag = event.getPriceTag();
    }

    public void apply(PriceTagDeletedEvent event) {
        this.deleted = true;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }
}

