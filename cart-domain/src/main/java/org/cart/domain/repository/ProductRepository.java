package org.cart.domain.repository;

import org.cart.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByUserId(String userId);

    Product findByBarcodeAndUserId(String barcode, String userId);

    Product findByIdAndUserIdAndBarcode(String id, String userId, String barcode);

    default void shouldNotBeDuplicate(Product product) throws Exception {
        if (this.findByBarcodeAndUserId(product.getBarcode(), product.getUserId()) != null) {
            throw new Exception("Cart already has a product with barcode = " + product.getBarcode());
        }
    }

    default void shouldBeAvailable(Product product) {
        Optional
                .of(this.findByIdAndUserIdAndBarcode(product.getId(), product.getUserId(), product.getBarcode()))
                .orElseThrow(() -> new NoSuchElementException("No product with id = " + product.getId() +
                        ", userId = " + product.getUserId() + ", barcode = " + product.getBarcode()));
    }
}
