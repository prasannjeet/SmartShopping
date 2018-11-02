package org.product.domain.repository;

import org.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByBarcode(String barcode);

    List<Product> findByStoreId(String storeId);

    Product findByStoreIdAndBarcode(String storeId, String barcode);

    default boolean isDuplicate(String storeId, String barcode) {
        return this.findByStoreIdAndBarcode(storeId, barcode) != null;
    }
}