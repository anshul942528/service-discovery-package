package com.hs.assign.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hs.assign.dao.ClientServiceDao;
import com.hs.assign.model.ClientService;
import com.hs.assign.util.HttpRestUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PingingService implements Runnable {
	
	@Autowired
	private ClientServiceDao clientServiceDao;
	
	@PostConstruct
	public void pingRegisteredServices() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException ex) {
				log.error("Interrupted exception in printer thread, ", ex);
			}
			
			Map<Integer, ClientService> map = clientServiceDao.getAllRegisteredClientsMap();
			for (Map.Entry<Integer, ClientService> m : map.entrySet()) {
				if(!checkAvailability(m.getValue().getIpAddress(), m.getValue().getPort(), 3))
					clientServiceDao.removeserviceWithKey(m.getKey());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private boolean checkAvailability(String ipAddress, int port, int retry) {
		int i = 0;
		while(i < 3) {
			try {
				ResponseEntity<Map> response = HttpRestUtils.getData("http://" + ipAddress + ":" + port +"/health", null, null, null);
				if(response.getStatusCode() == HttpStatus.OK) {
					return true;
				} else if (response.getStatusCode() != HttpStatus.OK && i++ == 3){
					return false;
				} 
			} catch(Exception ex) {
				log.error("http://" + ipAddress + ":" + port +"/health is down ");
				return false;
			}
		}
		return false;
	}
}
