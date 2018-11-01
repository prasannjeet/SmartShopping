package org.cart.domain.service.repository;

import org.cart.domain.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByUserId(String userId);

    Product findByBarcodeAndUserId(String barcode, String userId);

    default boolean isDuplicate(Product product) {
        return this.findByBarcodeAndUserId(product.getBarcode(), product.getUserId()) != null;
    }
}
