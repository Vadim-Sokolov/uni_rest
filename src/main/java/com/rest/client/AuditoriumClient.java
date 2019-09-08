package com.rest.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rest.model.Auditorium;

public class AuditoriumClient {

	private static final String GET_AUDITORIUMS_ENDPOINT_URL = "http://localhost:8080/auditoriums";
	private static final String GET_AUDITORIUM_ENDPOINT_URL = "http://localhost:8080/auditorium/{id}";
	private static final String CREATE_AUDITORIUM_ENDPOINT_URL = "http://localhost:8080/auditorium";
	private static final String UPDATE_AUDITORIUM_ENDPOINT_URL = "http://localhost:8080/auditorium";
	private static final String DELETE_AUDITORIUM_ENDPOINT_URL = "http://localhost:8080/auditorium/{id}";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public List<Auditorium> getAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<List<Auditorium>> result = restTemplate.exchange(GET_AUDITORIUMS_ENDPOINT_URL, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Auditorium>>(){});
		List<Auditorium> auditoriums = result.getBody();
		
		auditoriums.stream().forEach(c -> System.out.println(c));
		return auditoriums;
	}

	public Auditorium getById(Integer id) {
        Auditorium result = restTemplate.getForObject(GET_AUDITORIUM_ENDPOINT_URL, Auditorium.class, id);

        System.out.println(result);
        return result;
	}

	public Auditorium create(String name, Integer capacity) {
		Auditorium newAuditorium = new Auditorium();
		newAuditorium.setName(name);
		newAuditorium.setCapacity(capacity);

		Auditorium result = restTemplate.postForObject(CREATE_AUDITORIUM_ENDPOINT_URL, newAuditorium, Auditorium.class);

		System.out.println(result);
		return result;
	}

	public void update(Integer id, String name, Integer capacity) {
		Auditorium updatedAuditorium = new Auditorium();
		updatedAuditorium.setId(id);
		updatedAuditorium.setName(name);
		updatedAuditorium.setCapacity(capacity);
		restTemplate.put(UPDATE_AUDITORIUM_ENDPOINT_URL, updatedAuditorium);
	}

	public void delete(Integer id) {
		restTemplate.delete(DELETE_AUDITORIUM_ENDPOINT_URL, id);
	}
}
