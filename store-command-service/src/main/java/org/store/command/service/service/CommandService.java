package org.store.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.store.command.service.aggregate.StoreAggregate;
import org.store.command.service.command.CreateProductCommand;
import org.store.command.service.command.CreateStoreCommand;
import org.store.command.service.command.DeleteProductCommand;
import org.store.command.service.command.StoreCommand;
import org.store.command.service.command.UpdateProductPriceCommand;
import org.store.domain.model.PriceTag;
import org.store.domain.model.Product;
import org.store.domain.model.Store;
import org.store.domain.repository.PriceTagRepository;
import org.store.domain.repository.StoreRepository;

import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommandService {

    private AggregateRepository<StoreAggregate, StoreCommand> aggregateRepository;
    private StoreRepository storeRepository;
    private PriceTagRepository priceTagRepository;

    public CommandService(AggregateRepository<StoreAggregate, StoreCommand> aggregateRepository,
                          StoreRepository storeRepository, PriceTagRepository priceTagRepository) {
        this.aggregateRepository = aggregateRepository;
        this.storeRepository = storeRepository;
        this.priceTagRepository = priceTagRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<StoreAggregate>> createStore(Store store) throws Exception {
        if (!this.storeRepository.isIdentified()) {
            return this.aggregateRepository.save(new CreateStoreCommand(store));
        }
        throw new Exception("This store has been already created");
    }

    public CompletableFuture<EntityWithIdAndVersion<StoreAggregate>> createProduct(Product product) throws Exception {
        if (this.priceTagRepository.findByBarcode(product.getBarcode()) != null) {
            throw new Exception("This product has been already added to this store");
        }
        return this.aggregateRepository.save(new CreateProductCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<StoreAggregate>> deleteProduct(String barcode) throws Exception {
        PriceTag priceTag = Optional
                .of(this.priceTagRepository.findByBarcode(barcode))
                .orElseThrow(() -> new NoSuchElementException("No product with barcode = " + barcode));
        return this.aggregateRepository.update(priceTag.getId(), new DeleteProductCommand(priceTag));
    }

    public CompletableFuture<EntityWithIdAndVersion<StoreAggregate>> updateProductPrice(String barcode, String price)
            throws Exception {
        PriceTag priceTag = Optional
                .of(this.priceTagRepository.findByBarcode(barcode))
                .orElseThrow(() -> new NoSuchElementException("No product with barcode = " + barcode));
        priceTag.setPrice(price);
        return this.aggregateRepository.update(priceTag.getId(), new UpdateProductPriceCommand(priceTag));
    }

    public void scrapeWebsite() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
        try {
        InputStream inputStream = TypeReference.class.getResourceAsStream(this.storeRepository.findAll().get(0).getWebsite());
        List<Product> webProducts = mapper.readValue(inputStream,typeReference);
        System.err.println(webProducts.size() + " products found ! ");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
