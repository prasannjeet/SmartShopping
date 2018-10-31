package org.user.query.service.subscriber;

import org.user.domain.service.event.UserCreatedEvent;
import org.user.domain.service.event.UserDeletedEvent;

import org.user.domain.service.model.User;
import org.user.query.service.service.UserQueryService;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id="userQueryEventHandlers")

public class UserQueryEventSubscriber {
	
	private UserQueryService userQueryService;
	public UserQueryEventSubscriber(UserQueryService userQueryService) {
		this.userQueryService=userQueryService;
	}

	 @EventHandlerMethod
	 public void delete(DispatchedEvent<UserDeletedEvent>dispatchedEvent)
	 {
		 this.userQueryService.delete(dispatchedEvent.getEntityId());
	 }


	 @EventHandlerMethod
	    public void create(DispatchedEvent<UserCreatedEvent> event) {
	        this.userQueryService.save(new User(event.getEntityId(), event.getEvent().getUser()));
	}

}
