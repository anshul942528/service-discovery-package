package com.hs.client.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hs.client.model.ClientService;
import com.hs.client.util.HttpRestUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegistrationService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	ConfigurationService configurationService;
	
	@PostConstruct
	public void registerClient() {
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			String ip = localhost.getHostAddress().trim();
			int port = Integer.parseInt(environment.getProperty("server.port"));
			log.info("Ip address of this machine is : " + ip + " port : " + port);
			ClientService client = new ClientService("localhost", port);
			HttpRestUtils.putData("http://localhost:8080/register", null, client, null);
		} catch (UnknownHostException ex) {
			log.error("Exception occurred while finding IP of the machine, ", ex);
		}
	}
}
