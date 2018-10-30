package org.user.domain.service.event;

import org.user.domain.service.model.User;

public class UserUpdatedEvent implements UserEvent{
	
	
	private User user;
	
	public UserUpdatedEvent() {
		
	}

	public UserUpdatedEvent(User user) {
		this.user=user;
	}
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user=user;
	}
}
