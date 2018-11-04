package org.user.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.user.domain.event.UserEventUserCreated;
import org.user.domain.event.UserEventUserDeleted;
import org.user.domain.model.User;
import org.user.domain.repository.UserRepository;

@EventSubscriber(id = "userQueryEventHandler")
public class QueryEventSubscriber {

    private UserRepository userRepository;

    public QueryEventSubscriber(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandlerMethod
    public void createUser(DispatchedEvent<UserEventUserCreated> event) {
        this.userRepository.save(new User(event.getEntityId(), event.getEvent().getUser()));
    }

    @EventHandlerMethod
    public void deleteUser(DispatchedEvent<UserEventUserDeleted> event) {
        this.userRepository.delete(event.getEvent().getUser());
    }
}
