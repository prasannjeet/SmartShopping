package org.user.command.service.command;

import java.util.List;

public class DeleteUsersCommand implements UserCommand{
	
	private List<String> moreThanOneId;
	
	public DeleteUsersCommand() {
		
	}
	
	public DeleteUsersCommand(List<String> moreThanOneId) {
		this.moreThanOneId=moreThanOneId;
	}
	public List<String> getMoreThanOneId(){
		return this.moreThanOneId;
	}
}
