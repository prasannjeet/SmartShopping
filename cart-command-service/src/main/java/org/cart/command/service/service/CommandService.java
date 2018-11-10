package org.cart.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.command.*;
import org.cart.command.service.subscriber.CommandEventSubscriber;
import org.cart.domain.dao.*;
import org.cart.domain.model.Product;
import org.cart.domain.repository.CartRepository;
import org.cart.domain.repository.ProductRepository;
import org.store.domain.dao.StoreCartDao;
import org.store.domain.dao.StoreProductDao;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class CommandService {

    private AggregateRepository<CartAggregate, CartCommand> aggregateRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private CommandEventSubscriber commandEventSubscriber;

    public CommandService(AggregateRepository<CartAggregate, CartCommand> aggregateRepository,
                          ProductRepository productRepository, CartRepository cartRepository,
                          CommandEventSubscriber commandEventSubscriber) {
        this.aggregateRepository = aggregateRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.commandEventSubscriber = commandEventSubscriber;
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> addProduct(Product product) throws Exception {
        this.cartRepository.shouldBeAvailable(product.getUserId());
        this.productRepository.shouldNotBeDuplicate(product);
        product.verifyQuantity(product);
        return this.aggregateRepository.save(new AddProductCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> updateProductQuantity(Product product) throws Exception {
        this.cartRepository.shouldBeAvailable(product.getUserId());
        this.productRepository.shouldBeAvailable(product);
        product.verifyQuantity(product);
        return this.aggregateRepository.update(product.getId(), new UpdateProductQuantityCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> deleteProduct(String userId, String barcode) {
        Product product = Optional
                .of(this.productRepository.findByBarcodeAndUserId(barcode, userId))
                .orElseThrow(() -> new NoSuchElementException("No product with barcode = " + barcode + ", userId = " + userId));
        return this.aggregateRepository.update(product.getId(), new DeleteProductCommand(product));
    }

    public CompletableFuture<?> getProductsPrices(CartDao cartDao) throws Exception {
        this.cartRepository.shouldBeAvailable(cartDao.getUserId());
        cartDao.setProducts(this.productRepository.findByUserId(cartDao.getUserId()));
        if (cartDao.getProducts().isEmpty()) {
            throw new Exception("Cannot sort, cart is empty");
        }
        this.commandEventSubscriber.subscribe();
        return this.aggregateRepository.save(new SortCartCommand(cartDao))
                .thenApply(entity -> {
                    try {
                        Thread.sleep(5000);
                        this.commandEventSubscriber.unsubscribe();
                        if (this.commandEventSubscriber.isEmpty()) {
                            throw new CompletionException(new Exception("No store has all the products of this cart"));
                        }
                        return this.commandEventSubscriber.getStoresCartsDaos();
                    } catch (InterruptedException e) {
                        throw new CompletionException(e);
                    }
                });
    }

    public CompletableFuture<?> sortByStoresDistance() {
        return CompletableFuture
                .supplyAsync(() -> {
                    List<StoreCartDao> storesCartsDaos = this.commandEventSubscriber.getStoresCartsDaos();
                    Collections.sort(storesCartsDaos, (o1, o2) -> (int) (o1.getStoreDistance() - o2.getStoreDistance()));
                    SortByStoresDistanceDao sortByStoresDistanceDao = new SortByStoresDistanceDao();
                    for (StoreCartDao storeCartDao : storesCartsDaos) {
                        CartStoreDao cartStoreDao = new CartStoreDao();
                        cartStoreDao.setName(storeCartDao.getStoreName());
                        cartStoreDao.setDistance(storeCartDao.getStoreDistance() + " km");
                        Double grandTotal = 0.0;
                        for (StoreProductDao storeProductDao : storeCartDao.getStoreProductDaos()) {
                            CartProductDao cartProductDao = new CartProductDao();
                            cartProductDao.setName(storeProductDao.getName());
                            cartProductDao.setPrice(storeProductDao.getPrice() + " kr/" + (storeProductDao.getHasWeight() ? "kg" : "item"));
                            cartProductDao.setQuantity(storeProductDao.getQuantity());
                            Double total = storeProductDao.getPrice() * (storeProductDao.getHasWeight()
                                    ? Double.parseDouble(storeProductDao.getQuantity())
                                    : Integer.parseInt(storeProductDao.getQuantity()));
                            cartProductDao.setTotal("" + total + " kr");
                            grandTotal += total;
                            cartStoreDao.getProducts().add(cartProductDao);
                        }
                        cartStoreDao.setGrandTotal(grandTotal + " kr");
                        sortByStoresDistanceDao.getStores().add(cartStoreDao);
                    }
                    return sortByStoresDistanceDao;
                });
    }

    public CompletableFuture<?> sortByProductsPrice(String userId) {
        return CompletableFuture
                .supplyAsync(() -> {
                    List<StoreCartDao> storesCartsDaos = this.commandEventSubscriber.getStoresCartsDaos();
                    SortByProductsPriceDao sortByProductsPriceDao = new SortByProductsPriceDao();
                    for (Product product : this.productRepository.findByUserId(userId)) {
                        SortByProductPriceDao sortByProductPriceDao = new SortByProductPriceDao();
                        sortByProductPriceDao.setName(product.getName());
                        for (StoreCartDao storeCartDao : storesCartsDaos) {
                            CartPriceTagDao cartPriceTagDao = new CartPriceTagDao();
                            cartPriceTagDao.setName(storeCartDao.getStoreName());
                            for (StoreProductDao storeProductDao : storeCartDao.getStoreProductDaos()) {
                                if (storeProductDao.getBarcode().equals(product.getBarcode())) {
                                    cartPriceTagDao.setPrice(storeProductDao.getPrice() + " kr/" + (storeProductDao.getHasWeight() ? "kg" : "item"));
                                    cartPriceTagDao.setRawPrice(storeProductDao.getPrice());
                                }
                            }
                            sortByProductPriceDao.getPrices().add(cartPriceTagDao);
                        }
                        Collections.sort(sortByProductPriceDao.getPrices(), (o1, o2) -> (int) (o1.getRawPrice() - o2.getRawPrice()));
                        sortByProductsPriceDao.getProducts().add(sortByProductPriceDao);
                    }
                    return sortByProductsPriceDao;
                });
    }
}
