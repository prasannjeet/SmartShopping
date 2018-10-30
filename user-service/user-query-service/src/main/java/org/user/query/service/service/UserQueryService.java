package org.user.query.service.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.user.domain.service.model.User;
import org.user.domain.service.repository.UserRepository;
import org.user.domain.service.userDao.UserDao;

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
	
//	public List<UserDao> findAll(){
//		List<User> users=this.userRepository.findAll();
//		List<UserDao> usersDao=new LinkedList<>();
//		users.forEach(user -> usersDao.add(new UserDao(user)));
//		return usersDao;
//	}
//	
	
	

}
