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
import org.store.command.service.command.ApplyScrapperChangesCommand;
import org.store.command.service.command.CreateProductCommand;
import org.store.command.service.command.CreateStoreCommand;
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
            this.aggregateRepository.save(new CreateStoreCommand(store));
        } catch(Exception e) {}
    }

    @EventHandlerMethod
    public void addProduct(DispatchedEvent<GatewayEventAddProductInStore> event) {
        if(storeRepository.isIdentified() || !this.isDestination(event.getEvent().getStoreInfos())) {
            return;
        }
        Product product = new Product();
        try {
            product.setId(event.getEntityId());
            product.setBarcode(event.getEvent().getProduct().getBarcode());
            product.setName(event.getEvent().getProduct().getName());
            product.setPrice(event.getEvent().getProduct().getPrice());
            product.setHasWeight(event.getEvent().getProduct().getHasWeight());
            this.aggregateRepository.save(new CreateProductCommand(product));
        } catch(Exception e) {}
    }

    @EventHandlerMethod
    public void updateProductPrice(DispatchedEvent<GatewayEventUpdatePriceInStore> event) {
        if(storeRepository.isIdentified() || !this.isDestination(event.getEvent().getStoreInfos())) {
            return;
        }
        try {
            PriceTag priceTag = priceTagRepository.findByBarcode(event.getEvent().getBarcode());
            if(priceTag == null) {
                return;
            }
            priceTag.setPrice(event.getEvent().getPrice());
            this.aggregateRepository.update(priceTag.getId(), new UpdateProductPriceCommand(priceTag));
        } catch(Exception e) {}
    }

    @EventHandlerMethod
    public void launchWebScrapper(DispatchedEvent<GatewayEventScrap> event) {
        if(storeRepository.isIdentified() || !this.isDestination(event.getEvent().getStoreInfos())) {
            return;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream(this.storeRepository.findAll().get(0).getWebsite());
            List<Product> webProducts = mapper.readValue(inputStream,typeReference);
            ArrayList<Product> productsCreate = new ArrayList<>();
            ArrayList<PriceTag> pricesUpdated = new ArrayList<>();
            ArrayList<PriceTag> pricesDeleted = new ArrayList<>();
            for (Product prod : webProducts) {
                PriceTag correspondingTag = this.priceTagRepository.findByBarcode(prod.getBarcode());
                if(correspondingTag == null) {
                    productsCreate.add(prod);
                }
                else if (!correspondingTag.getPrice().contentEquals(prod.getPrice())) {
                    pricesUpdated.add(correspondingTag);
                }
            }
            for (PriceTag tag : this.priceTagRepository.findAll()) {
                if(isDeleted(tag, webProducts)) {
                    pricesDeleted.add(tag);
                }
            }
            this.aggregateRepository.save(new ApplyScrapperChangesCommand(productsCreate, pricesUpdated, pricesDeleted, this.storeRepository.findAll().get(0)));
        } catch(Exception e) { System.err.println(e.getMessage());}
    }

    private Double distanceFromUser(Double userLocation) {
        double diff = userLocation - Double.parseDouble(storeRepository.findAll().get(0).getLocation());
        return (diff >= 0) ? diff : -diff;
    }

    private boolean isDestination(StoreInfos storeInfos) {
        return storeRepository.findAll().get(0).getId().contentEquals(storeInfos.getId()) 
            && storeRepository.findAll().get(0).getLocation().contentEquals(storeInfos.getLocation());
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
