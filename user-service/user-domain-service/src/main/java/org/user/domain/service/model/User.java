package org.user.domain.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="user")
public class User {
	
	@Id
	private String id;
	
	@NotBlank
	@Column(name="user-id" , unique=true , nullable=false)
	private String userId;
	
	@NotBlank
	@Column(name="password", nullable=false)
	private String password;
	
	
	
	public User() {
		
	}
	
	public User(String id, String userId , String password) throws Exception {
		this.setId(id);
		this.setUserId(userId);
		this.setPassword(password);
	}
	public User(User user) throws Exception{
		this.setUserId(user.userId);
		this.setPassword(user.password);
	}
	public User(String userId, String password) {
		this.setUserId(userId);
		this.setPassword(password);
	}
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id=(id==null) ? this.id :(id.isEmpty() ? this.id : id);
	}
	
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId=(userId==null) ? this.userId : ( userId.isEmpty() ? this.userId:userId);
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password=(password==null) ? this.password : (password.isEmpty() ? this.password:password);
	}
	
	
	public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

}
