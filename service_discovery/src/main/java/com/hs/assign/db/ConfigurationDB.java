package com.hs.assign.db;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import com.hs.assign.model.EnvironmentConfig;

import lombok.Data;

@Data
@Repository
@ConfigurationProperties(prefix="conf")
public class ConfigurationDB {
	
	private Map<String, EnvironmentConfig> config = new HashMap<>();
	
	private Map<String, Object> dev;
	
	private Map<String, Object> stage;
	
	private Map<String, Object> preprod;
	
	private Map<String, Object> prod;
	
	@PostConstruct
	public void loadConfigMap() {
		config.put("dev", new EnvironmentConfig(dev));
		config.put("stage", new EnvironmentConfig(stage));
		config.put("preprod", new EnvironmentConfig(preprod));
		config.put("prod", new EnvironmentConfig(prod));
	}
	
	public EnvironmentConfig getConfigByEnvId(String env) {
		if(config.containsKey(env.toLowerCase())) {
			return config.get(env.toLowerCase());
		}
		return new EnvironmentConfig(new HashMap<>());
	}
}
