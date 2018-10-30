package org.user.query.service.subscriber;

import org.user.domain.service.event.UserDeletedEvent;
import org.user.domain.service.event.UserUpdatedEvent;
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
	    public void create(DispatchedEvent<UserUpdatedEvent>dispatchedEvent) throws Exception {
	    User user=new User(dispatchedEvent.getEvent().getUser());
	    user.setId(dispatchedEvent.getEntityId());
	    this.userQueryService.save(user);
}
	 @EventHandlerMethod
	 public void delete(DispatchedEvent<UserDeletedEvent>dispatchedEvent)
	 {
		 this.userQueryService.delete(dispatchedEvent.getEntityId());
	 }
	 @EventHandlerMethod
	    public void update(DispatchedEvent<UserUpdatedEvent> dispatchedEvent) throws Exception {
		 User user=new User(dispatchedEvent.getEvent().getUser());
		 user.setId(dispatchedEvent.getEntityId());
		 this.userQueryService.save(user);
	 }
}
