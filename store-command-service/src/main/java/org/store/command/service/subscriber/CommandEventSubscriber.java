package org.store.command.service.subscriber;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.cart.domain.event.CartEventSortingStarted;
import org.gateway.domain.model.StoreInfos;
import org.gateway.domain.event.GatewayEventInitializeStore;
import org.gateway.domain.event.GatewayEventAddProductInStore;
import org.gateway.domain.event.GatewayEventUpdatePriceInStore;
import org.gateway.domain.event.GatewayEventScrap;
import org.store.command.service.aggregate.StoreAggregate;
import org.store.command.service.command.ScrapperInvokedCommand;
import org.store.command.service.command.CreateProductCommand;
import org.store.command.service.command.CreateStoreCommand;
import org.store.command.service.command.DeleteProductCommand;
import org.store.command.service.command.StoreCommand;
import org.store.command.service.command.UpdateCartCommand;
import org.store.command.service.command.UpdateProductPriceCommand;
import org.store.domain.dao.StoreCartDao;
import org.store.domain.dao.StoreProductDao;
import org.store.domain.repository.PriceTagRepository;
import org.store.domain.repository.StoreRepository;
import org.store.domain.model.PriceTag;
import org.store.domain.model.Product;
import org.store.domain.model.Store;

import io.eventuate.AggregateRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

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

    @EventHandlerMethod
    public void updateCart(DispatchedEvent<CartEventSortingStarted> event) {
        if(!storeRepository.isIdentified()) {
            return;
        }
        Double distance = this.distanceFromUser(event.getEvent().getCartDao().getUserLocation());
        if(distance > 3) {  // Maybe change ?
            return;
        }
        StoreCartDao storeCartDao = new StoreCartDao();
        storeCartDao.setUserId(event.getEvent().getCartDao().getUserId());
        storeCartDao.setStoreName(this.storeRepository.findAll().get(0).getName());
        storeCartDao.setStoreDistance(distance);
        event.getEvent().getCartDao().getProducts()
                .forEach(product -> {
                    StoreProductDao storeProductDao = new StoreProductDao();
                    storeProductDao.setBarcode(product.getBarcode());
                    storeProductDao.setHasWeight(product.getHasWeight());
                    storeProductDao.setName(product.getName());
                    storeProductDao.setQuantity(product.getQuantity());
                    PriceTag priceTag = this.priceTagRepository.findByBarcode(product.getBarcode());
                    if(priceTag == null) {
                        return;
                    }
                    storeProductDao.setPrice(Double.parseDouble(priceTag.getPrice()));
                    storeCartDao.getStoreProductDaos().add(storeProductDao);
                });
        this.aggregateRepository.save(new UpdateCartCommand(storeCartDao));
    }

    @EventHandlerMethod
    public void initializeStore(DispatchedEvent<GatewayEventInitializeStore> event) {
        if(storeRepository.isIdentified()) {
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
        } catch(Exception e) {
            System.err.println(e.getMessage());
            // TODO Add error command
        }
    }

    @EventHandlerMethod
    public void addProduct(DispatchedEvent<GatewayEventAddProductInStore> event) {
        if(!storeRepository.isIdentified() || !this.isDestination(event.getEvent().getStoreInfos())) {
            return;
        }
        System.err.println("identified and destination");
        Product product = new Product();
        try {
            product.setId(event.getEntityId());
            product.setBarcode(event.getEvent().getProduct().getBarcode());
            product.setName(event.getEvent().getProduct().getName());
            product.setPrice(event.getEvent().getProduct().getPrice());
            product.setHasWeight(event.getEvent().getProduct().getHasWeight());
            this.priceTagRepository.save(new PriceTag(event.getEntityId(), product));
            this.aggregateRepository.save(new CreateProductCommand(product));
        } catch(Exception e) {
            System.err.println(e.getMessage());
            // TODO Add error command
        }
    }

    @EventHandlerMethod
    public void updateProductPrice(DispatchedEvent<GatewayEventUpdatePriceInStore> event) {
        if(!storeRepository.isIdentified() || !this.isDestination(event.getEvent().getStoreInfos())) {
            return;
        }
        try {
            PriceTag priceTag = priceTagRepository.findByBarcode(event.getEvent().getBarcode());
            if(priceTag == null) {
                return;
            }
            priceTag.setPrice(event.getEvent().getPrice());
            this.priceTagRepository.save(new PriceTag(event.getEntityId(), priceTag));
            this.aggregateRepository.update(priceTag.getId(), new UpdateProductPriceCommand(priceTag));
        } catch(Exception e) {
            System.err.println(e.getMessage());
            // TODO Add error command
        }
    }

    @EventHandlerMethod
    public void launchWebScrapper(DispatchedEvent<GatewayEventScrap> event) {
        if(!storeRepository.isIdentified() || !this.isDestination(event.getEvent().getStoreInfos())) {
            return;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream(this.storeRepository.findAll().get(0).getWebsite());
            List<Product> webProducts = mapper.readValue(inputStream,typeReference);
            for (Product prod : webProducts) {
                PriceTag correspondingTag = this.priceTagRepository.findByBarcode(prod.getBarcode());
                if(correspondingTag == null) {
                    Product newProduct = new Product();
                    newProduct.setId(event.getEntityId());
                    newProduct.setBarcode(prod.getBarcode());
                    newProduct.setName(prod.getName());
                    newProduct.setPrice(prod.getPrice());
                    newProduct.setHasWeight(prod.getHasWeight());
                    this.priceTagRepository.save(new PriceTag(event.getEntityId(), newProduct));
                    this.aggregateRepository.save(new CreateProductCommand(newProduct));
                }
                else if (!correspondingTag.getPrice().contentEquals(prod.getPrice())) {
                    correspondingTag.setPrice(prod.getPrice());
                    this.priceTagRepository.save(new PriceTag(event.getEntityId(), correspondingTag));
                    this.aggregateRepository.update(correspondingTag.getId(), new UpdateProductPriceCommand(correspondingTag));
                }
            }
            for (PriceTag tag : this.priceTagRepository.findAll()) {
                if(isDeleted(tag, webProducts)) {
                    this.priceTagRepository.delete(tag);
                    this.aggregateRepository.update(tag.getId(), new DeleteProductCommand(tag));
                }
            }
            this.aggregateRepository.save(new ScrapperInvokedCommand(this.storeRepository.findAll().get(0)));
        } catch(Exception e) { System.err.println(e.getMessage());}
    }

    private Double distanceFromUser(Double userLocation) {
        double diff = userLocation - Double.parseDouble(storeRepository.findAll().get(0).getLocation());
        return (diff >= 0) ? diff : -diff;
    }

    private boolean isDestination(StoreInfos storeInfos) {
        String id1 = storeRepository.findAll().get(0).getId();
        String id2 = storeInfos.getId();
        return id1.equals(id2);
    }

    private static boolean isDeleted(PriceTag tag, List<Product> webProducts) {
        for(Product product : webProducts) {
            if(product.getBarcode().contentEquals(tag.getBarcode())) {
                return false;
            }
        }
        return true;
    }
}
