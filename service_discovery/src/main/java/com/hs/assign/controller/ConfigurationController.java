package com.hs.assign.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hs.assign.model.EnvironmentConfig;
import com.hs.assign.service.ConfigurationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigurationController {
	
	@Autowired
	private ConfigurationService configurationService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody EnvironmentConfig getConfiguration(@RequestParam(name = "env") @NotBlank String env){
		log.info("Configuration controller started with environment : {}", env);
		EnvironmentConfig environmentConfig = configurationService.getConfiguration(env);
		log.info("Configuration controller completed");
		return environmentConfig;
	}
	
}
