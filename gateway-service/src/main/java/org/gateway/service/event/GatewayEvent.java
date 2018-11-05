package org.gateway.service.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.gateway.service.aggregate.GatewayAggregate")
public interface GatewayEvent extends Event {

}