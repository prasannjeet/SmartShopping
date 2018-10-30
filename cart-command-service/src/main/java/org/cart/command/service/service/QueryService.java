package org.cart.command.service.service;

import org.cart.domain.service.model.Cart;
import org.cart.domain.service.model.Product;
import org.cart.domain.service.repository.CartRepository;
import org.cart.domain.service.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class QueryService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public QueryService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public boolean isDuplicateProduct(Product product) {
        Cart cart = Optional.of(this.cartRepository.findByUserId(product.getUserId())).get();
        List<Product> products = this.productRepository.findByUserId(cart.getUserId());
        for (Product prod : products) {
            if (prod.getBarcode().equals(product.getBarcode())) {
                return true;
            }
        }
        return false;
    }


    public Product findProductByBarcodeAndUserId(String barcode, String userId) {
        return Optional
                .of(this.productRepository.findByBarcodeAndUserId(barcode, userId))
                .orElseThrow(() -> new NoSuchElementException("No product with barcode = " + barcode + ", userId = " + userId));
    }
}
