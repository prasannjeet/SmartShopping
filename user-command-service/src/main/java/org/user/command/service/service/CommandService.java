package org.user.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.user.command.service.aggregate.UserAggregate;
import org.user.command.service.command.CreateUserCommand;
import org.user.command.service.command.DeleteUserCommand;
import org.user.command.service.command.UserCommand;
import org.user.domain.service.model.User;
import org.user.domain.service.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

public class CommandService {

    private AggregateRepository<UserAggregate, UserCommand> aggregateRepository;
    private UserRepository userRepository;

    public CommandService(AggregateRepository<UserAggregate, UserCommand> aggregateRepository,
                          UserRepository userRepository) {
        this.aggregateRepository = aggregateRepository;
        this.userRepository = userRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> save(User user) throws Exception {
        if (this.userRepository.findByUsername(user.getUsername()) != null) {
            throw new Exception("Duplicate username = " + user.getUsername());
        }
        return this.aggregateRepository.save(new CreateUserCommand(user));
    }

    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> delete(String id) {
        return this.aggregateRepository.update(id, new DeleteUserCommand());
    }
}
