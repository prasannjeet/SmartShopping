package org.gateway.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.gateway.command.service.aggregate.UserAggregate;
import org.gateway.command.service.command.CreateUserCommand;
import org.gateway.command.service.command.DeleteUserCommand;
import org.gateway.command.service.command.UserCommand;
import org.user.domain.model.User;
import org.user.domain.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class CommandService {

    private AggregateRepository<UserAggregate, UserCommand> aggregateRepository;
    private UserRepository userRepository;

    public CommandService(AggregateRepository<UserAggregate, UserCommand> aggregateRepository,
                          UserRepository userRepository) {
        this.aggregateRepository = aggregateRepository;
        this.userRepository = userRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> createUser(User user) throws Exception {
        this.userRepository.shouldNotBeDuplicate(user);
        return this.aggregateRepository.save(new CreateUserCommand(user));
    }

    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> deleteUser(String id) throws NoSuchElementException {
        User user = Optional
                .of(this.userRepository.findOne(id))
                .orElseThrow(() -> new NoSuchElementException("No user with id = " + id));
        return this.aggregateRepository.update(user.getId(), new DeleteUserCommand(user));
    }
}
