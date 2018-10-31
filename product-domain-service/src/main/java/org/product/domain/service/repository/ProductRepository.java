package org.product.domain.service.repository;

import org.product.domain.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByBarcode(String barcode);
    
    default boolean isDuplicate(String barcode) {
        return this.findByBarcode(barcode) != null;
    }
}