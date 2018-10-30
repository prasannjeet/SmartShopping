package org.user.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.user.domain.service.event.UserCreatedEvent;
import org.user.domain.service.event.UserDeletedEvent;
import org.user.domain.service.model.User;
import org.user.query.service.service.QueryService;

@EventSubscriber(id = "userQueryEventHandler")
public class QueryEventSubscriber {

    private QueryService service;

    public QueryEventSubscriber(QueryService service) {
        this.service = service;
    }

    @EventHandlerMethod
    public void createUser(DispatchedEvent<UserCreatedEvent> event) {
        this.service.saveUser(new User(event.getEntityId(), event.getEvent().getUser()));
    }

    @EventHandlerMethod
    public void deleteUser(DispatchedEvent<UserDeletedEvent> event) {
        this.service.deleteUser(event.getEntityId());
    }
}
