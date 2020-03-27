package com.hs.assign.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.assign.dao.ClientServiceDao;
import com.hs.assign.model.ClientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientListingService implements Runnable {
	
	@Autowired
	private ClientServiceDao clientServiceDao;

	@PostConstruct
	public void printRegisteredClient() {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(20000);
			} catch (InterruptedException ex) {
				log.error("Interrupted exception in printer thread, ", ex);
			}
			Map<Integer, ClientService> map = clientServiceDao.getAllRegisteredClientsMap();
			for (Map.Entry<Integer, ClientService> m : map.entrySet()) {
				log.info(m.getKey() + " -> " + m.getValue());
			}
		}
	}
}
