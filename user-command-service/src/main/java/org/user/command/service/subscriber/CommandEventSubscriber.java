package org.user.command.service.subscriber;

import io.eventuate.AggregateRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.cart.domain.event.CartEventSortingStarted;
import org.user.command.service.aggregate.UserAggregate;
import org.user.command.service.command.UpdateCartCommand;
import org.user.command.service.command.UserCommand;
import org.user.domain.dao.StoreCartDao;
import org.user.domain.dao.StoreProductDao;

@EventSubscriber(id = "userCommandEventHandler")
public class CommandEventSubscriber {

    private AggregateRepository<UserAggregate, UserCommand> aggregateRepository;
    //TODO private PriceTagRepository
    // TODO private StoreRepository
    // TODO dont forget to update the bean of this class in main class

    public CommandEventSubscriber(AggregateRepository<UserAggregate, UserCommand> aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }

    @EventHandlerMethod
    public void updateCart(DispatchedEvent<CartEventSortingStarted> event) {
        // TODO 1. Calculate store distance from user distance. 2. Only run below code if within range
        StoreCartDao storeCartDao = new StoreCartDao();
        storeCartDao.setUserId(event.getEvent().getCartDao().getUserId());
        storeCartDao.setStoreName("Store name"); // get it from store repository
        storeCartDao.setStoreDistance(12.2); // add the distance here
        event.getEvent().getCartDao().getProducts()
                .forEach(product -> {
                    StoreProductDao storeProductDao = new StoreProductDao();
                    storeProductDao.setBarcode(product.getBarcode());
                    storeProductDao.setHasWeight(product.getHasWeight());
                    storeProductDao.setName(product.getName());
                    storeProductDao.setQuantity(product.getQuantity());
                    //TODO find the price by barcode and include it, if not found then return
                    storeProductDao.setPrice(12.22);
                    storeCartDao.getStoreProductDaos().add(storeProductDao);
                });
        this.aggregateRepository.save(new UpdateCartCommand(storeCartDao));
    }
}
