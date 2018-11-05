package org.gateway.service.service;

import org.gateway.service.aggregate.GatewayAggregate;
import org.gateway.service.command.GatewayCommand;
import org.gateway.service.repository.GatewayRepository;

import io.eventuate.AggregateRepository;

public class GatewayService {

	public GatewayService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository,
			GatewayRepository gatewayRepository) {
		// TODO Auto-generated constructor stub
	}

}
