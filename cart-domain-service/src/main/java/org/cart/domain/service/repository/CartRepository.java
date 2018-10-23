package org.cart.domain.service.repository;

import org.cart.domain.service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

	Cart findByUserId(String userId);

	// List<Product> findByProductsId(String id);
}
