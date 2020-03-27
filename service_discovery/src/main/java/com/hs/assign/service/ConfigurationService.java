package com.hs.assign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.assign.dao.ConfigurationDao;
import com.hs.assign.model.EnvironmentConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConfigurationService {
	
	@Autowired
	private ConfigurationDao configurationDao;
	
	public EnvironmentConfig getConfiguration(String env) {
		log.info("Configuration service started with environment : {}", env);
		EnvironmentConfig environmentConfig = configurationDao.getConfigByEnvId(env);
		log.info("Configuration service complmeted");
		return environmentConfig;
	}
}
