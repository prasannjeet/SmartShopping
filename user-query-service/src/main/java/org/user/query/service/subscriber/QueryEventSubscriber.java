package org.user.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.user.domain.service.event.UserEventUserCreated;
import org.user.domain.service.event.UserEventUserDeleted;
import org.user.domain.service.model.User;
import org.user.domain.service.repository.UserRepository;
import org.user.query.service.service.QueryService;

import java.util.concurrent.CompletableFuture;

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
