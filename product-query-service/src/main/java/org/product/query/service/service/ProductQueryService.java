package org.product.query.service.service;

import org.product.domain.service.model.Product;
import org.product.domain.service.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

public class ProductQueryService {

    private ProductRepository productRepository;

    public ProductQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product findById(String id) {
        return Optional.of(this.productRepository.findOne(id)).get();
    }

    public Product findByBarcode(int barcode) {
        return Optional
        		.of(this.productRepository.findByBarcode(barcode))
        		.orElseThrow(() -> new NoSuchElementException("No product with barcode = " + barcode));
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public void delete(String id) {
        this.productRepository.delete(id);
    }

    public void deleteAll() {
        this.productRepository.deleteAll();
    }
}