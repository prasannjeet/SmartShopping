package org.store.query.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/store/test", produces = "application/json")
@ResponseBody
public class TestController {

	int counter = 0;
	
	@GetMapping(value = "/{message}")
	public TestEntity Test(@PathVariable String message){
		return new TestEntity(message, ++counter);
	}
	
	@GetMapping(value = "/hello")
	public TestEntity Simple(){
		return new TestEntity("Hello world!", -1);
	}
}