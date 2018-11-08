package org.gateway.command.service.service;

import io.eventuate.AggregateRepository;
import org.gateway.command.service.aggregate.GatewayAggregate;
import org.gateway.command.service.command.GatewayCommand;
import org.gateway.domain.repository.UserRepository;

public class CommandService {

    private AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository;
    private UserRepository userRepository;

    public CommandService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository,
                          UserRepository userRepository) {
        this.aggregateRepository = aggregateRepository;
        this.userRepository = userRepository;
    }
}
