package com.hs.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ClientApp {
	public static void main(String[] args) {
		log.info("Client application starting");
		SpringApplication.run(ClientApp.class, args);
	}
}
