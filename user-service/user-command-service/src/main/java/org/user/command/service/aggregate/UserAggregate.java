package org.user.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;



import java.util.Collections;
import java.util.List;

import org.user.command.service.command.CreateUserCommand;
import org.user.command.service.command.DeleteUserCommand;
import org.user.command.service.command.UpdateUserCommand;
import org.user.command.service.command.UserCommand;
import org.user.domain.service.event.UserCreatedEvent;
import org.user.domain.service.event.UserDeletedEvent;
import org.user.domain.service.event.UserUpdatedEvent;
import org.user.domain.service.model.User;

public class UserAggregate extends ReflectiveMutableCommandProcessingAggregate<UserAggregate, UserCommand> {
	
	private User user;
	private boolean deleted;
	
	public List<Event> process (CreateUserCommand command){
		return this.deleted ? Collections.emptyList() : EventUtil.events(new UserCreatedEvent (command.getUser()));
	}
	
	public List<Event> process (DeleteUserCommand command){
		return this.deleted ? Collections.emptyList() : EventUtil.events(new UserDeletedEvent());
	}
	
	public List<Event> process (UpdateUserCommand command){
		return this.deleted ? Collections.emptyList() : EventUtil.events(new UserUpdatedEvent(command.getUser()));
	}
	public void apply(UserCreatedEvent event) {
		this.user=event.getUser();
	}
	public void apply(UserDeletedEvent event) {
		this.deleted=true;
		
	}
	public void apply(UserUpdatedEvent event) {
		this.user=event.getUser();
	}
	public User getUser() {
		return this.user;
	}
}
