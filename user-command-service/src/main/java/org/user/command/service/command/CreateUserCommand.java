package org.user.command.service.command;

import org.user.domain.service.model.User;

public class CreateUserCommand implements UserCommand {

    private User cart;

    public CreateUserCommand(User cart) {
        this.cart = cart;
    }

    public User getUser() {
        return this.cart;
    }
}
