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

import com.rest.model.Group;
import com.rest.model.Student;

public class StudentClient {

	private static final String GET_STUDENTS_ENDPOINT_URL = "http://localhost:8080/students";
	private static final String GET_STUDENT_ENDPOINT_URL = "http://localhost:8080/student/{id}";
	private static final String CREATE_STUDENT_ENDPOINT_URL = "http://localhost:8080/student";
	private static final String UPDATE_STUDENT_ENDPOINT_URL = "http://localhost:8080/student";
	private static final String DELETE_STUDENT_ENDPOINT_URL = "http://localhost:8080/student/{id}";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public List<Student> getAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<List<Student>> result = restTemplate.exchange(GET_STUDENTS_ENDPOINT_URL, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Student>>(){});
		List<Student> students = result.getBody();
		
		students.stream().forEach(c -> System.out.println(c));
		return students;
	}

	public Student getById(Integer id) {
        Student result = restTemplate.getForObject(GET_STUDENT_ENDPOINT_URL, Student.class, id);

        System.out.println(result);
        return result;
	}

	public Student create(String firstName, String lastName, String studentCardNumber, Group group) {
		Student newStudent = new Student();
		newStudent.setFirstName(firstName);
		newStudent.setLastName(lastName);
		newStudent.setStudentCardNumber(studentCardNumber);
		newStudent.setGroup(group);

		Student result = restTemplate.postForObject(CREATE_STUDENT_ENDPOINT_URL, newStudent, Student.class);

		System.out.println(result);
		return result;
	}

	public void update(Integer id, String firstName, String lastName, String studentCardNumber, Group group) {
		Student updatedStudent = new Student();
		updatedStudent.setId(id);
		updatedStudent.setFirstName(firstName);
		updatedStudent.setLastName(lastName);
		updatedStudent.setStudentCardNumber(studentCardNumber);
		updatedStudent.setGroup(group);
		restTemplate.put(UPDATE_STUDENT_ENDPOINT_URL, updatedStudent);
	}

	public void delete(Integer id) {
		restTemplate.delete(DELETE_STUDENT_ENDPOINT_URL, id);
	}
}
