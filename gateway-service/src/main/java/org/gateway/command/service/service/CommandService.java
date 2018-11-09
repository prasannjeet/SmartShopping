package org.gateway.command.service.service;

import io.eventuate.AggregateRepository;
import org.gateway.command.service.aggregate.GatewayAggregate;
import org.gateway.command.service.command.GatewayCommand;

public class CommandService {

    private AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository;

    public CommandService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }
}
