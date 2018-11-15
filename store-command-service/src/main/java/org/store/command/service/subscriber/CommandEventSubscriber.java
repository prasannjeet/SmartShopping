package org.store.command.service.subscriber;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.eventuate.AggregateRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.cart.domain.event.CartEventSortingStarted;
import org.gateway.domain.event.GatewayEventAddProductInStore;
import org.gateway.domain.event.GatewayEventInitializeStore;
import org.gateway.domain.event.GatewayEventScrap;
import org.gateway.domain.event.GatewayEventUpdatePriceInStore;
import org.gateway.domain.model.StoreInfos;
import org.store.command.service.aggregate.StoreAggregate;
import org.store.command.service.command.*;
import org.store.domain.dao.StoreCartDao;
import org.store.domain.dao.StoreProductDao;
import org.store.domain.model.PriceTag;
import org.store.domain.model.Product;
import org.store.domain.model.Store;
import org.store.domain.repository.PriceTagRepository;
import org.store.domain.repository.StoreRepository;

import java.io.InputStream;
import java.util.List;

@EventSubscriber(id = "storeCommandEventHandler")
public class CommandEventSubscriber {

    private AggregateRepository<StoreAggregate, StoreCommand> aggregateRepository;
    private PriceTagRepository priceTagRepository;
    private StoreRepository storeRepository;

    public CommandEventSubscriber(AggregateRepository<StoreAggregate, StoreCommand> aggregateRepository,
                                  PriceTagRepository priceTagRepository, StoreRepository storeRepository) {
        this.aggregateRepository = aggregateRepository;
        this.storeRepository = storeRepository;
        this.priceTagRepository = priceTagRepository;
    }

    private static boolean isDeleted(PriceTag tag, List<Product> webProducts) {
        for (Product product : webProducts) {
            if (product.getBarcode().contentEquals(tag.getBarcode())) {
                return false;
            }
        }
        return true;
    }

    @EventHandlerMethod
    public void updateCart(DispatchedEvent<CartEventSortingStarted> event) {
        if (!storeRepository.isIdentified()) {
            return;
        }
        Double distance = this.distanceFromUser(event.getEvent().getCartDao().getUserLocation());
        if (distance > 3) {
            return;
        }
        StoreCartDao storeCartDao = new StoreCartDao();
        storeCartDao.setUserId(event.getEvent().getCartDao().getUserId());
        storeCartDao.setStoreName(this.storeRepository.getSingleton().getName());
        storeCartDao.setStoreDistance(distance);
        for(org.cart.domain.model.Product product : event.getEvent().getCartDao().getProducts()) {
                    StoreProductDao storeProductDao = new StoreProductDao();
                    storeProductDao.setBarcode(product.getBarcode());
                    storeProductDao.setHasWeight(product.getHasWeight());
                    storeProductDao.setName(product.getName());
                    storeProductDao.setQuantity(product.getQuantity());
                    PriceTag priceTag = this.priceTagRepository.findByBarcode(product.getBarcode());
                    if (priceTag == null) {
                        return;
                    }
                    storeProductDao.setPrice(Double.parseDouble(priceTag.getPrice()));
                    storeCartDao.getStoreProductDaos().add(storeProductDao);
                };
        this.aggregateRepository.save(new UpdateCartCommand(storeCartDao));
    }

    @EventHandlerMethod
    public void initializeStore(DispatchedEvent<GatewayEventInitializeStore> event) {
        if (storeRepository.isIdentified()) {
            return;
        }
        Store store = new Store();
        try {
            store.setId(event.getEntityId());
            store.setLocation(event.getEvent().getStoreInfos().getLocation());
            store.setName(event.getEvent().getStoreInfos().getName());
            store.setWebsite(event.getEvent().getStoreInfos().getWebsite());
            this.storeRepository.save(store);
            this.aggregateRepository.save(new CreateStoreCommand(store));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @EventHandlerMethod
    public void addProduct(DispatchedEvent<GatewayEventAddProductInStore> event) {
        if (!storeRepository.isIdentified() || !this.isDestination(event.getEvent().getStoreInfos())) {
            return;
        }
        Product product = new Product();
        try {
            product.setId(event.getEntityId());
            product.setBarcode(event.getEvent().getProduct().getBarcode());
            product.setName(event.getEvent().getProduct().getName());
            product.setPrice(event.getEvent().getProduct().getPrice());
            product.setHasWeight(event.getEvent().getProduct().getHasWeight());
            this.priceTagRepository.save(new PriceTag(event.getEntityId(), product));
            this.aggregateRepository.save(new CreateProductCommand(this.storeRepository.getSingleton(), product));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @EventHandlerMethod
    public void updateProductPrice(DispatchedEvent<GatewayEventUpdatePriceInStore> event) {
        if (!storeRepository.isIdentified() || !this.isDestination(event.getEvent().getStoreInfos())) {
            return;
        }
        try {
            PriceTag priceTag = priceTagRepository.findByBarcode(event.getEvent().getBarcode());
            if (priceTag == null) {
                return;
            }
            this.priceTagRepository.delete(priceTag);
            priceTag.setPrice(event.getEvent().getPrice());
            this.priceTagRepository.save(new PriceTag(event.getEntityId(), priceTag));
            this.aggregateRepository.update(priceTag.getId(), new UpdateProductPriceCommand(this.storeRepository.getSingleton(), priceTag));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @EventHandlerMethod
    public void launchWebScrapper(DispatchedEvent<GatewayEventScrap> event) {
        if (!storeRepository.isIdentified() || !this.isDestination(event.getEvent().getStoreInfos())) {
            return;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream(this.storeRepository.getSingleton().getWebsite());
            List<Product> webProducts = mapper.readValue(inputStream, typeReference);
            for (Product prod : webProducts) {
                PriceTag correspondingTag = this.priceTagRepository.findByBarcode(prod.getBarcode());
                if (correspondingTag == null) {
                    Product newProduct = new Product();
                    newProduct.setId(event.getEntityId());
                    newProduct.setBarcode(prod.getBarcode());
                    newProduct.setName(prod.getName());
                    newProduct.setPrice(prod.getPrice());
                    newProduct.setHasWeight(prod.getHasWeight());
                    this.priceTagRepository.save(new PriceTag(event.getEntityId(), newProduct));
                    this.aggregateRepository.save(new CreateProductCommand(this.storeRepository.getSingleton(), newProduct));
                } else if (!correspondingTag.getPrice().contentEquals(prod.getPrice())) {
                    this.priceTagRepository.delete(correspondingTag);
                    correspondingTag.setPrice(prod.getPrice());
                    this.priceTagRepository.save(new PriceTag(event.getEntityId(), correspondingTag));
                    this.aggregateRepository.update(correspondingTag.getId(), new UpdateProductPriceCommand(this.storeRepository.getSingleton(), correspondingTag));
                }
            }
            for (PriceTag tag : this.priceTagRepository.findAll()) {
                if (isDeleted(tag, webProducts)) {
                    this.priceTagRepository.delete(tag);
                    this.aggregateRepository.update(tag.getId(), new DeleteProductCommand(tag));
                }
            }
            this.aggregateRepository.save(new ScrapperInvokedCommand(this.storeRepository.getSingleton()));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private Double distanceFromUser(Double userLocation) {
        double diff = userLocation - Double.parseDouble(storeRepository.getSingleton().getLocation());
        return (diff >= 0) ? diff : -diff;
    }

    private boolean isDestination(StoreInfos storeInfos) {
        String id1 = storeRepository.getSingleton().getId();
        String id2 = storeInfos.getId();
        return id1.equals(id2);
    }
}
