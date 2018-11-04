package org.store.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.cart.domain.event.CartEventProductsPricesAsked;

import org.cart.domain.dao.CartDaoForStore;
import org.cart.domain.dao.ProductDaoForStore;
import org.store.domain.dao.PriceListDao;
import org.store.domain.dao.PriceTagDao;
import org.store.domain.event.StoreEventProductCreated;
import org.store.domain.event.StoreEventProductPriceUpdated;
import org.store.domain.event.StoreEventStoreCreated;
import org.store.domain.model.PriceTag;
import org.store.domain.model.Store;
import org.store.domain.repository.PriceTagRepository;
import org.store.domain.repository.StoreRepository;

@EventSubscriber(id = "storeQueryEventHandler")
public class QueryEventSubscriber {


    private StoreRepository storeRepository;
    private PriceTagRepository priceTagRepository;

    public QueryEventSubscriber(StoreRepository storeRepository, PriceTagRepository priceTagRepository) {
        this.storeRepository = storeRepository;
        this.priceTagRepository = priceTagRepository;
    }

    @EventHandlerMethod
    public void createStore(DispatchedEvent<StoreEventStoreCreated> event) {
        this.storeRepository.save(new Store(event.getEntityId(), event.getEvent().getStore()));
    }

    @EventHandlerMethod
    public void createPriceTag(DispatchedEvent<StoreEventProductCreated> event) {
        try {
            this.priceTagRepository.save(new PriceTag(event.getEntityId(), event.getEvent().getProduct()));
        } catch (Exception e) {
            System.err.println("Cannot create price tag. " + e.getMessage());
        }
    }

    @EventHandlerMethod
    public void updatePriceTag(DispatchedEvent<StoreEventProductPriceUpdated> event) {
        try {
            this.priceTagRepository.save(new PriceTag(event.getEntityId(), event.getEvent().getPriceTag()));
        } catch (Exception e) {
            System.err.println("Cannot update price tag. " + e.getMessage());
        }
    }

    @EventHandlerMethod
    public void fillProductsPrice(DispatchedEvent<CartEventProductsPricesAsked> event) {
        CartDaoForStore cart = event.getEvent().getCart();
        if(this.storeRepository.isIdentified() && this.isClose(cart.getUserLocation())) {
            PriceListDao list = new PriceListDao(cart.getUserId(), this.storeRepository.findAll().get(0), this.distance(cart.getUserLocation()));
            for(ProductDaoForStore prod : cart.getProducts()) {
                PriceTag tag = this.priceTagRepository.findByBarcode(prod.getBarcode());
                if(tag == null) {
                    return;
                }
                list.addPriceTag(new PriceTagDao(tag));
                /* TODO
                 * How do I create an event from here ??*/
            }
        }
    }

    private boolean isClose(String userLocation) {
        // TODO
        return true;
    }

    private String distance(String userLocation) {
        // TODO
        return "500";
    }
}
