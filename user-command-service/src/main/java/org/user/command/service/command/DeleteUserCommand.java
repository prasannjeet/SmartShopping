package org.user.command.service.command;

import org.user.domain.model.User;

public class DeleteUserCommand implements UserCommand {

    private User user;

    public DeleteUserCommand(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
