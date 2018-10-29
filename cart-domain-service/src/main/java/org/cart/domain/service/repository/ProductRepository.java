package org.cart.domain.service.repository;

import org.cart.domain.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByUserId(String userId);

    List<Product> findByBarcode(String barcode);

    Product findByIdAndUserId(String id, String userId);
}
