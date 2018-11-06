package org.gateway.service.service;

import org.gateway.service.aggregate.GatewayAggregate;
import org.gateway.service.command.GatewayCommand;

import io.eventuate.AggregateRepository;

public class GatewayService {

	public GatewayService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository) {
		// TODO Auto-generated constructor stub
	}

}
