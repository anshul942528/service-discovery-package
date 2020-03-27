package com.hs.assign.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnvironmentConfig {

	private Map<String, Object> map;
}
