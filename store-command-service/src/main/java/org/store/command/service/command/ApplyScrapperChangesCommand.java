package org.store.command.service.command;

import java.util.List;

import org.store.domain.model.PriceTag;
import org.store.domain.model.Product;
import org.store.domain.model.Store;

public class ApplyScrapperChangesCommand implements StoreCommand {

    private Store store;
    private List<Product> productsCreated;
    private List<PriceTag> pricesUpdated;
    private List<PriceTag> pricesDeleted;

    public ApplyScrapperChangesCommand(List<Product> productsCreated, List<PriceTag> pricesUpdated, List<PriceTag> pricesDeleted, Store store) {
        this.productsCreated = productsCreated;
        this.pricesUpdated = pricesUpdated;
        this.pricesDeleted = pricesDeleted;
    }

    public List<Product> getProductsCreated() {
        return this.productsCreated;
    }

    public List<PriceTag> getPricesUpdated() {
        return this.pricesUpdated;
    }

    public List<PriceTag> getPricesDeleted() {
        return this.pricesDeleted;
    }

    public Store getStore() {
        return this.store;
    }
}