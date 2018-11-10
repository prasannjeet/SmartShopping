package org.store.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.store.command.service.command.*;
import org.store.domain.dao.StoreCartDao;
import org.store.domain.event.*;
import org.store.domain.model.PriceTag;
import org.store.domain.model.Product;
import org.store.domain.model.Store;

import java.util.Collections;
import java.util.List;

public class StoreAggregate extends ReflectiveMutableCommandProcessingAggregate<StoreAggregate, StoreCommand> {

    private Store store;
    private Product product;
    private PriceTag priceTag;
    private StoreCartDao storeCartDao;
    private boolean deleted;

    public List<Event> process(CreateProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventProductCreated(command.getStore(), command.getProduct()));
    }

    public List<Event> process(UpdateProductPriceCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventProductPriceUpdated(command.getStore(), command.getPriceTag()));
    }

    public List<Event> process(DeleteProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventProductDeleted(command.getStore(), command.getPriceTag()));
    }

    public List<Event> process(CreateStoreCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventStoreCreated(command.getStore()));
    }

    public List<Event> process(UpdateCartCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventCartUpdated(command.getStoreCartDao()));
    }

    public List<Event> process(ScrapperInvokedCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventScrapperLaunched(command.getStore()));
    }

    public void apply(StoreEventProductCreated event) {
        this.product = event.getProduct();
    }

    public void apply(StoreEventProductPriceUpdated event) {
        this.priceTag = event.getPriceTag();
    }

    public void apply(StoreEventStoreCreated event) {
        this.store = event.getStore();
    }

    public void apply(StoreEventCartUpdated event) {
        this.storeCartDao = event.getStoreCartDao();
    }

    public void apply(StoreEventProductDeleted event) {
        this.priceTag = event.getPriceTag();
    }

    public void apply(StoreEventScrapperLaunched event) {
        this.store = event.getStore();
    }

    public Product getProduct() {
        return this.product;
    }

    public Store getStore() {
        return this.store;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }

    public StoreCartDao getStoreCartDao() {
        return this.storeCartDao;
    }
}

