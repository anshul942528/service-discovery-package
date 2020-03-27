package com.hs.client.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hs.client.model.EnvironmentConfig;
import com.hs.client.util.HttpRestUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConfigurationService {
	
	@Autowired
	private Environment environment;
	
	@PostConstruct
	public void getEnvironmentConfiguration() {
		Map<String, String> map = new HashMap<>();
		map.put("dev", "");
		map.put("stage", "");
		map.put("preprod", "");
		map.put("prod", "");
		String env = environment.getProperty("ENV");
		if(!map.containsKey(env.toLowerCase()))
			env = "dev";
		ResponseEntity<EnvironmentConfig> config = HttpRestUtils.getData("http://localhost:8080/config?env=" + env.toLowerCase(), null, null, EnvironmentConfig.class);
		if(map != null && config.getBody() != null) {
			for(Map.Entry<String, Object> m : config.getBody().getMap().entrySet()) {
				log.info(m.getKey() + " -> " + m.getValue().toString());
			}
		} else {
			log.error("No configuration to print");
		}
	}
}
