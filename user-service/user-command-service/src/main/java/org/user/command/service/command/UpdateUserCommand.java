package org.user.command.service.command;

import org.user.domain.service.model.User;

public class UpdateUserCommand implements UserCommand{
	
	private String id;
	private User user;
	
	public UpdateUserCommand() {
		
	}
	
	public UpdateUserCommand(String id, User user) {
		this.id=id;
		this.user=user;
	}
	
	public String getId() {
		return this.id;
	}
	
	public User getUser() {
		return this.user;
	}
	

}
