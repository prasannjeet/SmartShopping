package org.gateway.domain.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.gateway.command.service.aggregate.GatewayAggregate")
public interface GatewayEvent extends Event {

}