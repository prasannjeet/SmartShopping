package org.store.command.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/store", produces = "application/json")
public class Controller {

	int counter = 0;
	
	@GetMapping(value = "/test/{message}")
	public TestEntity Test(@PathVariable String message){
		return new TestEntity(message, counter);
	}
}

class TestEntity {
	String message;
	int count;
	
	public TestEntity(String message, int count){
		this.count = count;
		this.message = message;
	}
}


