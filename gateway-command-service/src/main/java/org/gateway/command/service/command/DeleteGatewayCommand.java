package org.gateway.command.service.command;

import org.user.domain.model.User;

public class DeleteGatewayCommand implements GatewayCommand {

    private User user;

    public DeleteGatewayCommand(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
