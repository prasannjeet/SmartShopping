package org.cart.command.service.service;

import org.cart.domain.service.dao.ProductDao;
import org.cart.domain.service.model.Product;
import org.cart.domain.service.repository.ProductRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductQueryService {

    private ProductRepository productRepository;

    public ProductQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDao> findByUserId(String userId) {
        List<Product> products = Optional.of(this.productRepository.findByUserId(userId)).get();
        List<ProductDao> productDaos = new LinkedList<>();
        products.forEach(product -> productDaos.add(new ProductDao(product)));
        return productDaos;
    }

    public Product findByIdAndUserId(String id, String userId) {
        return Optional
                .of(this.productRepository.findByIdAndUserId(id, userId))
                .orElseThrow(() -> new NoSuchElementException("No product with id = " + id + ", userId = " + userId));
    }

    public List<Product> findByBarcode(String barcode) {
        return Optional
                .of(this.productRepository.findByBarcode(barcode))
                .get();
    }

    public List<String> getIdsByBarcode(String barcode) {
        List<Product> products = this.findByBarcode(barcode);
        List<String> ids = new LinkedList<>();
        products.forEach(product -> ids.add(product.getId()));
        return ids;
    }

}
