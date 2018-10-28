package org.cart.query.service.service;

import org.cart.domain.service.dao.ProductDao;
import org.cart.domain.service.model.Product;
import org.cart.domain.service.repository.ProductRepository;

import java.util.LinkedList;
import java.util.List;
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

    public void save(Product product) {
        this.productRepository.save(product);
    }

    public void delete(String id) {
        this.productRepository.delete(id);
    }

    public void deleteAll() {
        this.productRepository.deleteAll();
    }
}
