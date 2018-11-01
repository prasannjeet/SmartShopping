package org.product.command.service.service;

import org.product.domain.service.model.Product;
import org.product.domain.service.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    
    public boolean isDuplicate(int barcode) {
    		return this.productRepository.isDuplicate(barcode);
    }
}