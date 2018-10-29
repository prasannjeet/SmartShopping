package org.store.domain.service.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "org.store.command.service.aggregate.StoreBulkDeleteAggregate")
public class StoreDeletionRequestedEvent implements Event {

    private String storeId;

    public StoreDeletionRequestedEvent() {

    }

    public StoreDeletionRequestedEvent(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
