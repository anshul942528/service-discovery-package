package com.hs.assign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ServiceDiscoverApp {
	public static void main(String[] args) {
		log.info("Application start will happen now");
		SpringApplication.run(ServiceDiscoverApp.class, args);
	}
}
