package org.user.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.user.command.service.aggregate.UserAggregate;
import org.user.command.service.command.CreateUserCommand;
import org.user.command.service.command.DeleteUserCommand;
import org.user.command.service.command.UserCommand;
import org.user.domain.service.model.User;

import java.util.concurrent.CompletableFuture;

public class CommandService {

    private AggregateRepository<UserAggregate, UserCommand> aggregateRepository;

    public CommandService(AggregateRepository<UserAggregate, UserCommand> aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> save(User user) {
        return this.aggregateRepository.save(new CreateUserCommand(user));
    }

    public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> delete(User user) {
        return this.aggregateRepository.update(user.getId(), new DeleteUserCommand(user));
    }
}
