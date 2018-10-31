package org.user.domain.service.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="org.user.command.service.aggregate.UserBulkDeleteAggregate")
public class UserDeletionRequestEvent implements Event {
	
	private String userId;
	public UserDeletionRequestEvent() {
		
	}
	public UserDeletionRequestEvent (String userId) {
		this.userId=userId;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String usrId) {
		this.userId=userId;
	}

}
