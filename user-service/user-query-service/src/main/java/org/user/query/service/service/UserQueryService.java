package org.user.query.service.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.user.domain.service.model.User;
import org.user.domain.service.repository.UserRepository;


public class UserQueryService {
	
	private UserRepository  userRepository;
	public UserQueryService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public void save(User user) {
		this.userRepository.save(user);
	}
	
	public void delete(String id) {
		this.userRepository.delete(id);
	}
	
	public void deleteAll() {
		this.userRepository.deleteAll();
	}
	
	 public User findUserById(String id) {
	        return Optional
	                .of(this.userRepository.findOne(id))
	                .orElseThrow(() -> new NoSuchElementException("No user with id = " + id));
	}

	    public List<User> findAllUsers() {
	        return this.userRepository.findAll();
	}
	
	

}
