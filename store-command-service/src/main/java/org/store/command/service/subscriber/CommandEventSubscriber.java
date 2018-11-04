package org.store.command.service.subscriber;

import org.cart.domain.event.CartEventSortingStarted;
import org.store.command.service.aggregate.StoreAggregate;
import org.store.command.service.command.StoreCommand;
import org.store.command.service.command.UpdateCartCommand;
import org.store.domain.dao.StoreCartDao;
import org.store.domain.dao.StoreProductDao;
import org.store.domain.repository.PriceTagRepository;
import org.store.domain.repository.StoreRepository;
import org.store.domain.model.PriceTag;

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

    private Double distanceFromUser(Double userLocation) {
        double diff = userLocation - Double.parseDouble(storeRepository.findAll().get(0).getLocation());
        return (diff >= 0) ? diff : -diff;
    }
}