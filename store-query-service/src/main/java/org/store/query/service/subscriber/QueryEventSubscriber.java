package org.store.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import org.store.domain.service.dao.PriceList;
import org.store.domain.service.event.PriceTagAddedEvent;
import org.store.domain.service.event.PriceTagDeletedEvent;
import org.store.domain.service.event.PriceTagUpdatedEvent;
import org.store.domain.service.model.PriceTag;
import org.store.query.service.service.MapService;
import org.store.query.service.service.StoreQueryService;
import org.cart.domain.service.event.CartEventProductsPricesAsked;

@EventSubscriber(id = "storeQueryEventHandler")
public class QueryEventSubscriber {
    private StoreQueryService queryService;

    public QueryEventSubscriber(StoreQueryService queryService) {
        this.queryService = queryService;
    }
    
    @EventHandlerMethod
    public void savePriceTag(DispatchedEvent<PriceTagAddedEvent> event) throws Exception {
        this.queryService.savePriceTag(new PriceTag(event.getEntityId(), event.getEvent().getPriceTag()));
    }

    @EventHandlerMethod
    public void updatePriceTagQuantity(DispatchedEvent<PriceTagUpdatedEvent> event) throws Exception {
        this.queryService.savePriceTag(new PriceTag(event.getEntityId(), event.getEvent().getPriceTag()));
    }

    @EventHandlerMethod
    public void deletePriceTag(DispatchedEvent<PriceTagDeletedEvent> event) {
        this.queryService.deletePriceTag(event.getEvent().getPriceTag().getId());
    }
    
    
    @EventHandlerMethod
    public PriceList createPriceList(DispatchedEvent<CartEventProductsPricesAsked> event){
    	PriceList priceList = this.queryService.getPriceList(event.getEvent().getCart());
    	if (priceList == null)
    		return null; //TODO: How to ignore event?
    	else return priceList;
    }
    
}
