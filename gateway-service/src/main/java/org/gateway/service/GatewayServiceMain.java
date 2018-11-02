package org.gateway.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;

@Configuration
@Import({EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class GatewayServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceMain.class, args);
    }
}
