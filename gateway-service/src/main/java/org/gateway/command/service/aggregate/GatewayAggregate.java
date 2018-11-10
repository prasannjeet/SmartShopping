package org.gateway.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import org.gateway.command.service.command.*;
import org.gateway.domain.event.GatewayEventAddProductInStore;
import org.gateway.domain.event.GatewayEventInitializeStore;
import org.gateway.domain.event.GatewayEventScrap;
import org.gateway.domain.event.GatewayEventUpdatePriceInStore;
import org.gateway.domain.model.StoreInfos;

import java.util.List;

public class GatewayAggregate extends ReflectiveMutableCommandProcessingAggregate<GatewayAggregate, GatewayCommand> {

    public List<Event> process(AddProductInStoreCommand command) {
        return EventUtil.events(new GatewayEventAddProductInStore(new StoreInfos(command.getStoreId()), command.getProduct()));
    }

    public List<Event> process(InitiateStoreCommand command) {
        return EventUtil.events(new GatewayEventInitializeStore(command.getstoreInfo()));
    }

    public List<Event> process(UpdatePriceInStoreCommand command) {
        return EventUtil.events(new GatewayEventUpdatePriceInStore(new StoreInfos(command.getStoreId()), command.getProduct().getBarcode(), command.getProduct().getPrice()));
    }

    public List<Event> process(ScrapProductCommand command) {
        return EventUtil.events(new GatewayEventScrap(new StoreInfos(command.getStoreId())));
    }

    public void apply(GatewayEventAddProductInStore event) {

    }

    public void apply(GatewayEventInitializeStore event) {

    }

    public void apply(GatewayEventUpdatePriceInStore event) {

    }

    public void apply(GatewayEventScrap event) {

    }
}
