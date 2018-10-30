package org.user.domain.service.event;

import org.user.domain.service.model.User;

public class UserCreatedEvent implements UserEvent {

    private User user;

    public UserCreatedEvent() {

    }

    public UserCreatedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
