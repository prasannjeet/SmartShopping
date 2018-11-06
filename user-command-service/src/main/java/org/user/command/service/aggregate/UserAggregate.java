package org.user.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.user.command.service.command.CreateUserCommand;
import org.user.command.service.command.DeleteUserCommand;
import org.user.command.service.command.UserCommand;
import org.user.domain.event.UserEventUserCreated;
import org.user.domain.event.UserEventUserDeleted;
import org.user.domain.model.User;

import java.util.Collections;
import java.util.List;

public class UserAggregate extends ReflectiveMutableCommandProcessingAggregate<UserAggregate, UserCommand> {

    private User user;
    private boolean deleted;


    public List<Event> process(CreateUserCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new UserEventUserCreated(command.getUser()));
    }

    public List<Event> process(DeleteUserCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new UserEventUserDeleted(command.getUser()));
    }

    public void apply(UserEventUserCreated event) {
        this.user = event.getUser();
    }

    public void apply(UserEventUserDeleted event) {
        this.deleted = true;
    }

    public User getUser() {
        return this.user;
    }
}
