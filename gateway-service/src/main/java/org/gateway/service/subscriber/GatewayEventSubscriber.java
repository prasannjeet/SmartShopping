package org.gateway.service.subscriber;

import org.gateway.service.aggregate.GatewayAggregate;
import org.gateway.service.command.GatewayCommand;

import io.eventuate.AggregateRepository;

public class GatewayEventSubscriber {

	public GatewayEventSubscriber(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository) {
		// TODO Auto-generated constructor stub
	}

}
