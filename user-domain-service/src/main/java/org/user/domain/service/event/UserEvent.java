package org.user.domain.service.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.user.command.service.aggregate.UserAggregate")
public interface UserEvent extends Event {

}
