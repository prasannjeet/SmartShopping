package org.cart.domain.service.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.cart.command.service.aggregate.ProductBulkDeleteAggregate")
public class ProductDeletionRequestedEvent implements Event {

    private String productId;

    public ProductDeletionRequestedEvent() {

    }

    public ProductDeletionRequestedEvent(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
