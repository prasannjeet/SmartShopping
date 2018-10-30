package org.user.domain.service.userDao;

import org.user.domain.service.model.User;

public class UserDao {
	
	private String id;
	private String userId;
	private String password;
	
	public UserDao() {
		
	}
	
	public UserDao(String id, User user) {
		this.id=id;
		this.userId=userId;
		this.password=password;
	}
	
	public UserDao(User user) {
		this.id=user.getId();
		this.userId=user.getUserId();
		this.password=user.getPassword();
	
	}
	
	public String getId() {
		return this.id;
	}
	public void SetId(String id) {
		this.id=id;
	}
	public String getUserId() {
		return this.userId;
	}
	public void getUserId(String userId) {
		this.userId=userId;
	}
	public String getPassword() {
		return password;
	}
	public void SetPassword() {
		this.password=password;
	}

}
