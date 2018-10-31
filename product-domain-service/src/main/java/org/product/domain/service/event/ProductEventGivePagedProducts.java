package org.product.domain.service.event;

import org.product.domain.service.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductEventGivePagedProducts implements ProductEvent {

    private Page<Product> products;
    
    private Pageable pageable; // The Pageable used to get this page by the "calling" event.

    public ProductEventGivePagedProducts() {
    		
    }

    public ProductEventGivePagedProducts(Page<Product> products, Pageable pageable) {
        this.products = products;
        this.pageable = pageable;
    }

    public Page<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Page<Product> product) {
        this.products = products;
    }

    public Pageable getPageable() {
        return this.pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable; 
    }
}