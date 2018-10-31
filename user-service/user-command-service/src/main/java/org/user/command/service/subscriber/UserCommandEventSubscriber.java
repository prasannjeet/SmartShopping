package org.user.command.service.subscriber;

import java.util.concurrent.CompletableFuture;

import org.user.command.service.aggregate.UserAggregate;
import org.user.command.service.command.DeleteUserCommand;
import org.user.domain.service.event.UserDeletionRequestEvent;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id="userCommandEventHandlers")
public class UserCommandEventSubscriber {
	
	@EventHandlerMethod
	public CompletableFuture<EntityWithIdAndVersion<UserAggregate>>
	delete(EventHandlerContext<UserDeletionRequestEvent> eventEventHandlerContext){
		return eventEventHandlerContext.update(UserAggregate.class,eventEventHandlerContext.getEvent().getUserId(), new DeleteUserCommand());
	}

}
