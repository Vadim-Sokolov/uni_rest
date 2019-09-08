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

import com.rest.model.Faculty;

public class FacultyClient {

	private static final String GET_FACULTIES_ENDPOINT_URL = "http://localhost:8080/faculties";
	private static final String GET_FACULTY_ENDPOINT_URL = "http://localhost:8080/faculty/{id}";
	private static final String CREATE_FACULTY_ENDPOINT_URL = "http://localhost:8080/faculty";
	private static final String UPDATE_FACULTY_ENDPOINT_URL = "http://localhost:8080/faculty";
	private static final String DELETE_FACULTY_ENDPOINT_URL = "http://localhost:8080/faculty/{id}";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public List<Faculty> getAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<List<Faculty>> result = restTemplate.exchange(GET_FACULTIES_ENDPOINT_URL, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Faculty>>(){});
		List<Faculty> faculties = result.getBody();
		
		faculties.stream().forEach(c -> System.out.println(c));
		return faculties;
	}

	public Faculty getById(Integer id) {
        Faculty result = restTemplate.getForObject(GET_FACULTY_ENDPOINT_URL, Faculty.class, id);

        System.out.println(result);
        return result;
	}

	public Faculty create(String name) {
		Faculty newFaculty = new Faculty();
		newFaculty.setName(name);

		Faculty result = restTemplate.postForObject(CREATE_FACULTY_ENDPOINT_URL, newFaculty, Faculty.class);

		System.out.println(result);
		return result;
	}

	public void update(Integer id, String name) {
		Faculty updatedFaculty = new Faculty();
		updatedFaculty.setId(id);
		updatedFaculty.setName(name);
		restTemplate.put(UPDATE_FACULTY_ENDPOINT_URL, updatedFaculty);
	}

	public void delete(Integer id) {
		restTemplate.delete(DELETE_FACULTY_ENDPOINT_URL, id);
	}
}
