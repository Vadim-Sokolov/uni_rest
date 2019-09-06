package com.rest.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rest.model.Auditorium;

public class AuditoriumClient {

	private static final String GET_AUDITORIUMS_ENDPOINT_URL = "http://localhost:8080/auditoriums";
	private static final String GET_AUDITORIUM_ENDPOINT_URL = "http://localhost:8080/api/v1/auditoriums/{id}";
	private static final String CREATE_AUDITORIUM_ENDPOINT_URL = "http://localhost:8080/auditorium";
	private static final String UPDATE_AUDITORIUM_ENDPOINT_URL = "http://localhost:8080/auditorium";
	private static final String DELETE_AUDITORIUM_ENDPOINT_URL = "http://localhost:8080/auditorium/{id}";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity<String> getAuditoriums() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_AUDITORIUMS_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
		return result;
	}

	public void getAuditoriumById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		Auditorium result = restTemplate.getForObject(GET_AUDITORIUM_ENDPOINT_URL, Auditorium.class, params);

		System.out.println(result);
	}

	public void createAuditorium() {

		Auditorium newAuditorium = new Auditorium();

		RestTemplate restTemplate = new RestTemplate();
		Auditorium result = restTemplate.postForObject(CREATE_AUDITORIUM_ENDPOINT_URL, newAuditorium, Auditorium.class);

		System.out.println(result);
	}

	public void updateAuditorium() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		Auditorium updatedAuditorium = new Auditorium();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_AUDITORIUM_ENDPOINT_URL, updatedAuditorium, params);
	}

	public void deleteAuditorium() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_AUDITORIUM_ENDPOINT_URL, params);
	}
}
