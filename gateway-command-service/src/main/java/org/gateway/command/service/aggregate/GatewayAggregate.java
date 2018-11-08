package org.gateway.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import org.gateway.command.service.command.CreateGatewayCommand;
import org.gateway.command.service.command.DeleteGatewayCommand;
import org.gateway.command.service.command.GatewayCommand;
import org.user.domain.event.UserEventUserCreated;
import org.user.domain.event.UserEventUserDeleted;
import org.user.domain.model.User;

import java.util.Collections;
import java.util.List;

public class GatewayAggregate extends ReflectiveMutableCommandProcessingAggregate<GatewayAggregate, GatewayCommand> {

    private User user;
    private boolean deleted;


    public List<Event> process(CreateGatewayCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new UserEventUserCreated(command.getUser()));
    }

    public List<Event> process(DeleteGatewayCommand command) {
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
