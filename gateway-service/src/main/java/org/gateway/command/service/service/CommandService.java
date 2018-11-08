package org.gateway.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.gateway.command.service.aggregate.GatewayAggregate;
import org.gateway.command.service.command.CreateGatewayCommand;
import org.gateway.command.service.command.DeleteGatewayCommand;
import org.gateway.command.service.command.GatewayCommand;
import org.user.domain.model.User;
import org.user.domain.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class CommandService {

    private AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository;
    private UserRepository userRepository;

    public CommandService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository,
                          UserRepository userRepository) {
        this.aggregateRepository = aggregateRepository;
        this.userRepository = userRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<GatewayAggregate>> createUser(User user) throws Exception {
        this.userRepository.shouldNotBeDuplicate(user);
        return this.aggregateRepository.save(new CreateGatewayCommand(user));
    }

    public CompletableFuture<EntityWithIdAndVersion<GatewayAggregate>> deleteUser(String id) throws NoSuchElementException {
        User user = Optional
                .of(this.userRepository.findOne(id))
                .orElseThrow(() -> new NoSuchElementException("No user with id = " + id));
        return this.aggregateRepository.update(user.getId(), new DeleteGatewayCommand(user));
    }
}
