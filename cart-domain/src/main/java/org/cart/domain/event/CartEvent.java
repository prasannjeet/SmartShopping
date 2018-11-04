package org.cart.domain.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.cart.command.service.aggregate.CartAggregate")
public interface CartEvent extends Event {

}
