package org.user.domain.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
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
        this.id = (id == null || id.isEmpty()) ? this.id : id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = (username == null || username.isEmpty()) ? this.username : username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = (password == null || password.isEmpty()) ? this.password : password;
    }
}
