package com.hs.assign.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hs.assign.dto.RegistrationRequest;
import com.hs.assign.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void registerClient(@RequestBody @Valid RegistrationRequest request) {
		log.info("Registration controller started with rquest : {}", request);
		service.registerClient(request);
		log.info("Registration controller completed");
	}
}
