package org.store.query.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import(value = { MyConfiguration.class})
@EnableAutoConfiguration
public class StoreQueryServiceMain {

	public static void main(String[] args) {
		SpringApplication.run(StoreQueryServiceMain.class, args);
	}
}

@Configuration
@ComponentScan(basePackages = "{org.store.query.service}")
class MyConfiguration extends WebMvcConfigurerAdapter {

}