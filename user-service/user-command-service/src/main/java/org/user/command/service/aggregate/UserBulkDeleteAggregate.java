package org.user.command.service.aggregate;

import java.util.List;
import java.util.stream.Collectors;

import org.user.command.service.command.DeleteUsersCommand;
import org.user.command.service.command.UserCommand;
import org.user.domain.service.event.UserDeletionRequestEvent;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class UserBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<UserBulkDeleteAggregate, UserCommand>{
	
	public List<Event> process (DeleteUsersCommand command){
		return command.getMoreThanOneId()
				.stream()
				.map(UserDeletionRequestEvent::new)
				.collect(Collectors.toList());
		
	}
	public void apply(UserDeletionRequestEvent event) {
}

}
