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

import com.rest.model.Course;

public class CourseClient {

	private static final String GET_COURSES_ENDPOINT_URL = "http://localhost:8080/courses";
	private static final String GET_COURSE_ENDPOINT_URL = "http://localhost:8080/course/{id}";
	private static final String CREATE_COURSE_ENDPOINT_URL = "http://localhost:8080/course";
	private static final String UPDATE_COURSE_ENDPOINT_URL = "http://localhost:8080/course";
	private static final String DELETE_COURSE_ENDPOINT_URL = "http://localhost:8080/course/{id}";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public List<Course> getAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<List<Course>> result = restTemplate.exchange(GET_COURSES_ENDPOINT_URL, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Course>>(){});
		List<Course> courses = result.getBody();
		
		courses.stream().forEach(c -> System.out.println(c));
		return courses;
	}

	public Course getById(Integer id) {
        Course result = restTemplate.getForObject(GET_COURSE_ENDPOINT_URL, Course.class, id);

        System.out.println(result);
        return result;
	}

	public Course create(String name, Integer numberOfWeeks, String description) {
		Course newCourse = new Course();
		newCourse.setName(name);
		newCourse.setNumberOfWeeks(numberOfWeeks);
		newCourse.setDescription(description);

		Course result = restTemplate.postForObject(CREATE_COURSE_ENDPOINT_URL, newCourse, Course.class);

		System.out.println(result);
		return result;
	}

	public void update(Integer id, String name, Integer numberOfWeeks, String description) {
		Course updatedCourse = new Course();
		updatedCourse.setId(id);
		updatedCourse.setName(name);
		updatedCourse.setNumberOfWeeks(numberOfWeeks);
		updatedCourse.setDescription(description);
		restTemplate.put(UPDATE_COURSE_ENDPOINT_URL, updatedCourse);
	}

	public void delete(Integer id) {
		restTemplate.delete(DELETE_COURSE_ENDPOINT_URL, id);
	}
}
