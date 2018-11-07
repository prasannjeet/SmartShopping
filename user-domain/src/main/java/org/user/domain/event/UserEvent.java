package org.user.domain.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.gateway.command.service.aggregate.UserAggregate")
public interface UserEvent extends Event {

}
