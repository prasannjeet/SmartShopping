package org.gateway.command.service.command;

import org.user.domain.model.User;

public class CreateGatewayCommand implements GatewayCommand {

    private User user;

    public CreateGatewayCommand(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
