package org.product.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.store.domain.service.event.StoreSeviceProductAdded;
import org.store.domain.service.dao.ProductInfo;
import org.product.domain.service.model.Product;
import org.product.query.service.service.ProductQueryService;

@EventSubscriber(id = "productQueryEventHandlers")
public class ProductQueryEventSubscriber {

    private ProductQueryService productQueryService;

    public ProductQueryEventSubscriber(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<StoreSeviceProductAdded> dispatchedEvent) {
    		ProductInfo infos = dispatchedEvent.getEvent().getProductInfo();
        Product product = new Product(infos.getBarcode(), infos.getProductName(), infos.isWeightable());
        this.productQueryService.save(product);
    }
}