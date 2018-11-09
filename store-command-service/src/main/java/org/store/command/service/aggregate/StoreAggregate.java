package org.store.command.service.aggregate;

import java.util.Collections;
import java.util.List;

import org.store.command.service.command.ApplyScrapperChangesCommand;
import org.store.command.service.command.CreateProductCommand;
import org.store.command.service.command.CreateStoreCommand;
import org.store.command.service.command.DeleteProductCommand;
import org.store.command.service.command.StoreCommand;
import org.store.command.service.command.UpdateCartCommand;
import org.store.command.service.command.UpdateProductPriceCommand;
import org.store.domain.dao.StoreCartDao;
import org.store.domain.event.StoreEventCartUpdated;
import org.store.domain.event.StoreEventProductCreated;
import org.store.domain.event.StoreEventProductDeleted;
import org.store.domain.event.StoreEventProductPriceUpdated;
import org.store.domain.event.StoreEventScrapperLaunched;
import org.store.domain.event.StoreEventStoreCreated;
import org.store.domain.model.PriceTag;
import org.store.domain.model.Product;
import org.store.domain.model.Store;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class StoreAggregate extends ReflectiveMutableCommandProcessingAggregate<StoreAggregate, StoreCommand> {

    private Store store;
    private Product product;
    private PriceTag priceTag;
    private StoreCartDao storeCartDao;
    private boolean deleted;

    public List<Event> process(CreateProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventProductCreated(command.getProduct()));
    }

    public List<Event> process(UpdateProductPriceCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventProductPriceUpdated(command.getPriceTag()));
    }

    public List<Event> process(DeleteProductCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventProductDeleted(command.getPriceTag()));
    }

    public List<Event> process(CreateStoreCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventStoreCreated(command.getStore()));
    }

    public List<Event> process(UpdateCartCommand command) {
        return this.deleted ? Collections.emptyList() : EventUtil.events(new StoreEventCartUpdated(command.getStoreCartDao()));
    }

    public List<Event> process(ApplyScrapperChangesCommand command) {
        if(this.deleted) {
            return Collections.emptyList();
        }
        List<Event> events = EventUtil.events();
        command.getProductsCreated()
            .forEach(product -> {
                events.add(new StoreEventProductCreated(product));
            });
        command.getPricesUpdated()
            .forEach(tag -> {
                events.add(new StoreEventProductPriceUpdated(priceTag));
            });
        command.getPricesDeleted()
        .forEach(tag -> {
            events.add(new StoreEventProductDeleted(priceTag));
        });
        events.add(new StoreEventScrapperLaunched(command.getStore()));
        return events;
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

