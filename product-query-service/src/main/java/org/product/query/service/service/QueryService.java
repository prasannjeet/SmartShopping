package org.product.query.service.service;

import org.product.domain.model.Product;
import org.product.domain.repository.ProductRepository;

import java.util.List;

public class QueryService {

    private ProductRepository productRepository;

    public QueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product findByBarcode(String barcode) {
        return this.productRepository.findByBarcode(barcode);
    }
}