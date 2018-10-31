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
	@Column(name="username" , unique=true , nullable=false)
	private String username;
	
	@NotBlank
	@Column(name="password", nullable=false)
	private String password;
	
	
	
	public User() {
		
	}
	
	public User(String id, User user) {
        this.setId(id);
        this.setUsername(user.username);
        this.setPassword(user.password);

	}
	public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = (id == null) ? this.id : (id.isEmpty() ? this.id : id);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = (username == null) ? this.username : (username.isEmpty() ? this.username : username);
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = (password == null) ? this.password : (password.isEmpty() ? this.password : password);
}
	
	public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

}
