package org.store.domain.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.cart.command.service.aggregate.StoreAggregate")
public interface StoreEvent extends Event {

}
