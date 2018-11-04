package org.cart.domain.repository;

import org.cart.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    Cart findByUserId(String userId);

    default boolean isDuplicate(String userId) {
        return this.findByUserId(userId) != null;
    }

    default void shouldBeAvailable(String userId) {
        Optional
                .of(this.findByUserId(userId))
                .orElseThrow(() -> new NoSuchElementException("No cart with userId = " + userId));
    }
}
