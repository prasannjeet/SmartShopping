package org.cart.domain.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @NotBlank
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    public Cart() {

    }

    public Cart(String userId) {
        this.setUserId(userId);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = (userId == null || userId.isEmpty()) ? this.userId : userId;
    }
}
