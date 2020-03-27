package com.hs.assign.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.assign.constant.Constants;
import com.hs.assign.dao.ClientServiceDao;
import com.hs.assign.dto.RegistrationRequest;
import com.hs.assign.model.ClientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegistrationService {

	@Autowired
	private ClientServiceDao clientServiceDao;

	@PostConstruct
	public void loadAllClientServices() {
		BufferedReader reader = null;
		FileReader fileReder = null;
		
		try {
			//File file = ResourceUtils.getFile("classpath:" + Constants.FILLE_NAME);
			File file = new File(Constants.FILLE_NAME);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileReder = new FileReader(file);
			reader = new BufferedReader(fileReder);
			String line = reader.readLine();
			while (line != null) {
				String[] input = line.split(",");
				ClientService clientService = new ClientService(input[1], Integer.parseInt(input[2]));
				clientServiceDao.addClientService(Integer.parseInt(input[0]), clientService);
				line = reader.readLine();
			}
		} catch (IOException ie) {
			log.error("Error while reading the service file : ", ie);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ne) {
					log.error("Exception while closing the reader object, ", ne);
				} finally {
					reader = null;
				}
			}
			if(fileReder != null) {
				try {
					fileReder.close();
				} catch (IOException ne) {
					log.error("Exception while closing the reader object, ", ne);
				} finally {
					fileReder = null;
				}
			}
		}
	}

	public void registerClient(RegistrationRequest request) {
		log.info("Registration service started");
		ClientService clientService = new ClientService(request.getIpAddress(), request.getPort());
		if(!isClientPresent(clientService)) {
			clientServiceDao.addClientService(clientService);
		} else {
			System.out.println("This client is already present in the client's list");
		}
	}
	
	public boolean isClientPresent(ClientService clientService) {
		List<ClientService> clientList = clientServiceDao.getRegisteredClients();
		for(ClientService client : clientList) {
			if(client.equals(clientService)) {
				return true;
			}
		}
		return false;
	}
}
