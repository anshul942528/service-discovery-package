package com.hs.assign.db;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.hs.assign.model.ClientService;

import lombok.Data;

@Data
@Repository
public class ClientServiceDB {
	
	private Map<Integer, ClientService> map = new ConcurrentHashMap<>();
	
	public int getMaxId() {
		if(!map.keySet().isEmpty()) {
			return map.keySet().stream().max(Comparator.naturalOrder()).get();
		}
		return 0;
	}
	
	public boolean addClientService(int id, ClientService clientService) {
		map.put(id, clientService);
		return true;
	}
	
	public List<ClientService> getClientsList(){
		return map.values().stream().collect(Collectors.toList());
	}
	
	public void removeClientService(int key) {
		map.remove(key);
	}
}
