package com.hs.assign.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegistrationRequest {
	
	@NotNull
	@NotEmpty
	private String ipAddress;
	
	private int port;
}
