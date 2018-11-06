package org.user.domain.event;

import org.user.domain.model.User;

public class UserEventUserDeleted implements UserEvent {

    private User user;

    public UserEventUserDeleted() {

    }

    public UserEventUserDeleted(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
