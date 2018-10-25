package org.cart.domain.service.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.cart.command.service.aggregate.CartBulkDeleteAggregate")
public class CartDeletionRequestedEvent implements Event {

    private String cartId;

    public CartDeletionRequestedEvent() {

    }

    public CartDeletionRequestedEvent(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return this.cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
