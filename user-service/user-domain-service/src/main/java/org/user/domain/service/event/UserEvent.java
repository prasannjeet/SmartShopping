package org.user.domain.service.event;

import io.eventuate.EventEntity;
import io.eventuate.Event;

@EventEntity(entity ="org.user.command.user.aggregate.UserAggregate")
public interface UserEvent extends Event{
	
}
