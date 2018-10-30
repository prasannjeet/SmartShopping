package org.user.command.service.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.user.domain.service.model.User;
import org.user.domain.service.repository.UserRepository;

public class UserQueryService {
	
	private UserRepository userRepository;
	
	
	public UserQueryService() {
		
	}
	public UserQueryService (UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	public User findByUserId(String userId) {
		
		return Optional
				.of(this.userRepository.findByUserId(userId))
				.orElseThrow(() -> new NoSuchElementException ("No user with "+userId+" user id."));
	}
	
	public boolean IsDuplicate(String userId) {
		return this.userRepository.isDuplicate(userId);
	}

}
