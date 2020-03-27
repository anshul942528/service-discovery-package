package com.hs.assign.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hs.assign.db.ConfigurationDB;
import com.hs.assign.model.EnvironmentConfig;

@Component
public class ConfigurationDao {

	@Autowired
	private ConfigurationDB configurationDB;
	
	public EnvironmentConfig getConfigByEnvId(String env) {
		return configurationDB.getConfigByEnvId(env);
	}
}
