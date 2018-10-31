package org.product.domain.service.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.product.command.service.aggregate.ProductAggregate")
public interface ProductEvent extends Event {

}