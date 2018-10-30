package org.store.domain.service.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.store.command.service.aggregate.ProductAggregate")
public interface ProductEvent extends Event {

}
