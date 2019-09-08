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
import com.rest.model.Group;

public class GroupClient {

	private static final String GET_GROUPS_ENDPOINT_URL = "http://localhost:8080/groups";
	private static final String GET_GROUP_ENDPOINT_URL = "http://localhost:8080/group/{id}";
	private static final String CREATE_GROUP_ENDPOINT_URL = "http://localhost:8080/group";
	private static final String UPDATE_GROUP_ENDPOINT_URL = "http://localhost:8080/group";
	private static final String DELETE_GROUP_ENDPOINT_URL = "http://localhost:8080/group/{id}";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public List<Group> getAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<List<Group>> result = restTemplate.exchange(GET_GROUPS_ENDPOINT_URL, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Group>>(){});
		List<Group> groups = result.getBody();
		
		groups.stream().forEach(c -> System.out.println(c));
		return groups;
	}

	public Group getById(Integer id) {
        Group result = restTemplate.getForObject(GET_GROUP_ENDPOINT_URL, Group.class, id);

        System.out.println(result);
        return result;
	}

	public Group create(String name, Faculty faculty) {
		Group newGroup = new Group();
		newGroup.setName(name);
		newGroup.setFaculty(faculty);

		Group result = restTemplate.postForObject(CREATE_GROUP_ENDPOINT_URL, newGroup, Group.class);

		System.out.println(result);
		return result;
	}

	public void update(Integer id, String name, Faculty faculty) {
		Group updatedGroup = new Group();
		updatedGroup.setId(id);
		updatedGroup.setName(name);
		updatedGroup.setFaculty(faculty);
		restTemplate.put(UPDATE_GROUP_ENDPOINT_URL, updatedGroup);
	}

	public void delete(Integer id) {
		restTemplate.delete(DELETE_GROUP_ENDPOINT_URL, id);
	}
}
