package org.user.command.service.controller;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.user.command.service.service.UserCommandService;
import org.user.command.service.service.UserQueryService;
import org.user.domain.service.model.User;
import org.user.domain.service.userDao.UserDao;

@RestController
@RequestMapping(value="/users",produces="application/json")
@ResponseBody
public class controller {
	
	private UserCommandService userCommandService;
	private UserQueryService userQueryService;
	
	public controller(UserCommandService userCommandService, UserQueryService userQueryService) {
		this.userCommandService=userCommandService;
		this.userQueryService=userQueryService;
	}	
	@PostMapping(consumes = "application/json")
	public CompletableFuture<UserDao> createUser(@RequestBody @Valid User user) throws Exception{
		if (this.userQueryService.IsDuplicate(user.getUserId())) {
			throw new Exception ("Duplicate UserId= "+user.getUserId());
		}
		return this.userCommandService
				.save(user)
				.thenApply(entity -> new UserDao(entity.getEntityId(),entity.getAggregate().getUser()));
	}
	
	@DeleteMapping (value="/{userId}")
	public CompletableFuture<ResponseEntity<?>> deleteUser (@NotBlank @PathVariable String userId){
		return this.userCommandService
				.delete(this.userQueryService.findByUserId(userId).getId())
				.thenApply(entity -> ResponseEntity.ok().build());
	
	}
	
}

