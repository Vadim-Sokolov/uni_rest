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
import com.rest.model.Teacher;

public class TeacherClient {

	private static final String GET_TEACHERS_ENDPOINT_URL = "http://localhost:8080/teachers";
	private static final String GET_TEACHER_ENDPOINT_URL = "http://localhost:8080/teacher/{id}";
	private static final String CREATE_TEACHER_ENDPOINT_URL = "http://localhost:8080/teacher";
	private static final String UPDATE_TEACHER_ENDPOINT_URL = "http://localhost:8080/teacher";
	private static final String DELETE_TEACHER_ENDPOINT_URL = "http://localhost:8080/teacher/{id}";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public List<Teacher> getAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<List<Teacher>> result = restTemplate.exchange(GET_TEACHERS_ENDPOINT_URL, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Teacher>>(){});
		List<Teacher> teachers = result.getBody();
		
		teachers.stream().forEach(c -> System.out.println(c));
		return teachers;
	}

	public Teacher getById(Integer id) {
        Teacher result = restTemplate.getForObject(GET_TEACHER_ENDPOINT_URL, Teacher.class, id);

        System.out.println(result);
        return result;
	}

	public Teacher create(String firstName, String lastName, Faculty faculty) {
		Teacher newTeacher = new Teacher();
		newTeacher.setFirstName(firstName);
		newTeacher.setLastName(lastName);
		newTeacher.setFaculty(faculty);

		Teacher result = restTemplate.postForObject(CREATE_TEACHER_ENDPOINT_URL, newTeacher, Teacher.class);

		System.out.println(result);
		return result;
	}

	public void update(Integer id, String firstName, String lastName, Faculty faculty) {
		Teacher updatedTeacher = new Teacher();
		updatedTeacher.setId(id);
		updatedTeacher.setFirstName(firstName);
		updatedTeacher.setLastName(lastName);
		updatedTeacher.setFaculty(faculty);
		restTemplate.put(UPDATE_TEACHER_ENDPOINT_URL, updatedTeacher);
	}

	public void delete(Integer id) {
		restTemplate.delete(DELETE_TEACHER_ENDPOINT_URL, id);
	}
}
