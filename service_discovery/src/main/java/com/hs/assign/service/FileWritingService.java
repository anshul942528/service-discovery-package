package com.hs.assign.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.assign.constant.Constants;
import com.hs.assign.dao.ClientServiceDao;
import com.hs.assign.model.ClientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileWritingService implements Runnable {
	
	@Autowired
	private ClientServiceDao clientServiceDao;
	
	@PostConstruct
	public void writeToFile() {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(90000);
			} catch (InterruptedException ex) {
				log.error("Interrupted exception in printer thread, ", ex);
			}
			
			BufferedWriter writer = null;
			FileWriter filewriter = null;
			try {
				//File file = ResourceUtils.getFile("classpath:"+Constants.FILLE_NAME);
				File file = new File(Constants.FILLE_NAME);
				if (!file.exists()) {
					file.createNewFile();
				}
				filewriter = new FileWriter(file);
				writer = new BufferedWriter(filewriter);
				Map<Integer, ClientService> map = clientServiceDao.getAllRegisteredClientsMap();
				for(Map.Entry<Integer, ClientService> m : map.entrySet()) {
					StringBuilder build = new StringBuilder();
					build.append(m.getKey()).append(",").append(m.getValue().getIpAddress()).append(",").append(m.getValue().getPort()).append("\n");
					writer.write(build.toString());
				}
			} catch (IOException ie) {
				log.error("Error while writing to file : ", ie);
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException ne) {
						log.error("Exception while closing the reader object, ", ne);
					} finally {
						writer = null;
					}
				}
				if(filewriter != null) {
					try {
						filewriter.close();
					} catch (IOException ne) {
						log.error("Exception while closing the reader object, ", ne);
					} finally {
						filewriter = null;
					}
				}
			}
		}
	}
}
