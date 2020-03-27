package com.hs.assign.util;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpRestUtils {

	final static Duration connectTimeout = Duration.ofMillis(10 * 1000L);
	final static Duration readTimeout = Duration.ofMillis(60 * 1000L);

	private static RestTemplate getRestTemplate(Duration connectTimeout, Duration readTimeout) {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		return restTemplateBuilder.setConnectTimeout(connectTimeout).setReadTimeout(readTimeout).build();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <U> ResponseEntity<U> getData(RestTemplate restTemplate, String url, Map<String, String> headerMap, Map<String, Object> uriVariables, Class<U> responseType) throws RestClientException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		if (headerMap != null)
			headerMap.forEach((k, v) -> httpHeaders.set(k, v));
		HttpEntity httpEntity = new HttpEntity(httpHeaders);

		ResponseEntity<U> responseEntity = (uriVariables == null) ? restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType)
				: restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType, uriVariables);
		if (responseEntity.getStatusCode() == HttpStatus.OK)
			return responseEntity;
		return null;
	}

	public static <U> ResponseEntity<U> getData(String url, Map<String, String> headerMap, Map<String, Object> uriVariables, Class<U> responseType) throws RestClientException {
		log.info("Get request, url: {}, connetionTimeout: {}, readTimeout: {}", url, connectTimeout, readTimeout);
		RestTemplate restTemplate = getRestTemplate(connectTimeout, readTimeout);
		ResponseEntity<U> u = getData(restTemplate, url, headerMap, uriVariables, responseType);
		log.info("Response for the get request made on url: {}, response: {}", url, u);
		return u;
	}
}
