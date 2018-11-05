package org.gateway.service.aggregate;


import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.gateway.service.command.*;
import org.gateway.service.model.User;

import java.util.Collections;
import java.util.List;



public class GatewayAggregate extends ReflectiveMutableCommandProcessingAggregate<GatewayAggregate, GatewayCommand> {
	private User user;
    private boolean deleted;

    public User getUser() {
        return this.user;
    }
}
