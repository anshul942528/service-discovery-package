package com.hs.assign.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hs.assign.db.ClientServiceDB;
import com.hs.assign.model.ClientService;

@Component
public class ClientServiceDao {
	
	@Autowired
	private ClientServiceDB clientServiceDB;
	
	public void addClientService(ClientService clientService) {
		int id = clientServiceDB.getMaxId();
		addClientService(id + 1, clientService);
	}
	
	public void addClientService(int id, ClientService clientService) {
		clientServiceDB.addClientService(id, clientService);
	}
	
	public Map<Integer, ClientService> getAllRegisteredClientsMap(){
		return clientServiceDB.getMap();
	}
	
	public List<ClientService> getRegisteredClients(){
		return clientServiceDB.getClientsList();
	}
	
	public void removeserviceWithKey(int key) {
		clientServiceDB.removeClientService(key);
	}
}
