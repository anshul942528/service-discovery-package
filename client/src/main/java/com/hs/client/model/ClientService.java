package com.hs.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientService {

	private String ipAddress;

	private int port;
}
