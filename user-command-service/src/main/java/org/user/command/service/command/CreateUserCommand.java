package org.user.command.service.command;


import org.user.domain.model.User;

public class CreateUserCommand implements UserCommand {

    private User user;

    public CreateUserCommand(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
