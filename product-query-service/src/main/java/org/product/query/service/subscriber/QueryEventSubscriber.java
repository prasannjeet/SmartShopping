package org.product.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.product.domain.model.Product;
import org.product.domain.repository.ProductRepository;
import org.store.domain.event.StoreEventProductCreated;

@EventSubscriber(id = "productQueryEventHandlers")
public class QueryEventSubscriber {

    private ProductRepository productRepository;

    public QueryEventSubscriber(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandlerMethod
    public void createProduct(DispatchedEvent<StoreEventProductCreated> event) throws Exception {
        org.store.domain.model.Product productFromStore = event.getEvent().getProduct();
        if (!this.productRepository.isDuplicate(productFromStore.getBarcode())) {
            Product product = new Product();
            product.setId(event.getEntityId());
            product.setHasWeight(productFromStore.getHasWeight());
            product.setBarcode(productFromStore.getBarcode());
            product.setName(productFromStore.getName());
            this.productRepository.save(product);
        }
    }
}