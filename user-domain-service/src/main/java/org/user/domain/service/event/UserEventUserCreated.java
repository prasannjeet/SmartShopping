package org.user.domain.service.event;

import org.user.domain.service.model.User;

public class UserEventUserCreated implements UserEvent {

    private User user;

    public UserEventUserCreated() {

    }

    public UserEventUserCreated(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
