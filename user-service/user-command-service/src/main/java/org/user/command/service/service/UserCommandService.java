package org.user.command.service.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.user.command.service.aggregate.UserAggregate;
import org.user.command.service.aggregate.UserBulkDeleteAggregate;
import org.user.command.service.command.CreateUserCommand;
import org.user.command.service.command.DeleteUserCommand;
import org.user.command.service.command.DeleteUsersCommand;
import org.user.command.service.command.UpdateUserCommand;
import org.user.command.service.command.UserCommand;
import org.user.domain.service.model.User;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

public class UserCommandService {

	
	private final AggregateRepository<UserAggregate, UserCommand> aggregateRepository;
	private final AggregateRepository<UserBulkDeleteAggregate , UserCommand> bulkDeleteAggregateRepository;

	public UserCommandService(AggregateRepository<UserAggregate, UserCommand> aggregateRepository,
			AggregateRepository<UserBulkDeleteAggregate,UserCommand> bulkDeleteAggregateRepository ) {
		this.aggregateRepository=aggregateRepository;
		this.bulkDeleteAggregateRepository=bulkDeleteAggregateRepository;
		
	}
	public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> save(User user){
		return this.aggregateRepository.save(new CreateUserCommand(user));
	}
	
	public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> update (String id, User user){
		return this.aggregateRepository.update(id, new UpdateUserCommand(id,user));
	}
	public CompletableFuture<EntityWithIdAndVersion<UserAggregate>> delete(String id){
		return this.aggregateRepository.update(id,new DeleteUserCommand());
	}
	public CompletableFuture<EntityWithIdAndVersion<UserBulkDeleteAggregate>> deleteAll(List<String> moreThanOneId){
		return this.bulkDeleteAggregateRepository.save(new DeleteUsersCommand(moreThanOneId));
	}




}
