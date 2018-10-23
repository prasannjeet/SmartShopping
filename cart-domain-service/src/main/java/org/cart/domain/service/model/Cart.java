package org.cart.domain.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Cart {

	@Id
	private String id;

	@NotBlank
	@Column(name = "user_id", unique = true, nullable = false)
	private String userId;

	public Cart() {

	}

	public Cart(String id, String userId) {
		this.id = id;
		this.userId = userId;
	}

	public Cart(String userId) {
		this.userId = userId;
	}

	public Cart(Cart cart) {
		this.userId = cart.getUserId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Cart { id = " + this.id + ", userId = " + this.userId + "}";
	}
}
