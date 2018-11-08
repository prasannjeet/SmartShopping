package org.gateway.command.service.aggregate;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import org.gateway.command.service.command.AddProductInStoreCommand;
import org.gateway.command.service.command.GatewayCommand;
import org.gateway.command.service.command.InitiateStoreCommand;
import org.gateway.command.service.command.UpdatePriceInStoreCommand;
import org.gateway.domain.event.GatewayEventAddProductInStore;
import org.gateway.domain.event.GatewayEventInitializeStore;
import org.gateway.domain.event.GatewayEventUpdatePriceInStore;
import java.util.List;

public class GatewayAggregate extends ReflectiveMutableCommandProcessingAggregate<GatewayAggregate, GatewayCommand> {

    public List<Event> process(AddProductInStoreCommand command) {
        return EventUtil.events(new GatewayEventAddProductInStore());
    }

    public List<Event> process(InitiateStoreCommand command) {
        return EventUtil.events(new GatewayEventInitializeStore());
    }
    
    public List<Event> process(UpdatePriceInStoreCommand command) {
        return EventUtil.events(new GatewayEventUpdatePriceInStore());
    }

    public void apply(GatewayEventAddProductInStore event) {
        
    }
    public void apply(GatewayEventInitializeStore event) {
        
    }
    public void apply(GatewayEventUpdatePriceInStore event) {
        
    }
}
