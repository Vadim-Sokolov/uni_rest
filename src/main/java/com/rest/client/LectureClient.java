package com.rest.client;

import java.time.LocalTime;
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
import com.rest.model.Course;
import com.rest.model.Faculty;
import com.rest.model.Group;
import com.rest.model.Lecture;
import com.rest.model.Teacher;

public class LectureClient {

	private static final String GET_LECTURES_ENDPOINT_URL = "http://localhost:8080/lectures";
	private static final String GET_LECTURE_ENDPOINT_URL = "http://localhost:8080/lecture/{id}";
	private static final String CREATE_LECTURE_ENDPOINT_URL = "http://localhost:8080/lecture";
	private static final String UPDATE_LECTURE_ENDPOINT_URL = "http://localhost:8080/lecture";
	private static final String DELETE_LECTURE_ENDPOINT_URL = "http://localhost:8080/lecture/{id}";
	
	private static RestTemplate restTemplate = new RestTemplate();

	public List<Lecture> getAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<List<Lecture>> result = restTemplate.exchange(GET_LECTURES_ENDPOINT_URL, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Lecture>>(){});
		List<Lecture> lectures = result.getBody();
		
		lectures.stream().forEach(c -> System.out.println(c));
		return lectures;
	}

	public Lecture getById(Integer id) {
        Lecture result = restTemplate.getForObject(GET_LECTURE_ENDPOINT_URL, Lecture.class, id);

        System.out.println(result);
        return result;
	}

	public Lecture create(Auditorium auditorium, Course course, Group group, Teacher teacher, LocalTime time) {
		Lecture newLecture = new Lecture();
		newLecture.setAuditorium(auditorium);
		newLecture.setCourse(course);
		newLecture.setGroup(group);
		newLecture.setTeacher(teacher);
		newLecture.setTime(time);
		
		Lecture result = restTemplate.postForObject(CREATE_LECTURE_ENDPOINT_URL, newLecture, Lecture.class);

		System.out.println(result);
		return result;
	}

	public void update(Integer id, Auditorium auditorium, Course course, Group group, Teacher teacher, LocalTime time) {
		Lecture updatedLecture = new Lecture();
		updatedLecture.setId(id);
		updatedLecture.setAuditorium(auditorium);
		updatedLecture.setCourse(course);
		updatedLecture.setGroup(group);
		updatedLecture.setTeacher(teacher);
		updatedLecture.setTime(time);
		restTemplate.put(UPDATE_LECTURE_ENDPOINT_URL, updatedLecture);
	}

	public void delete(Integer id) {
		restTemplate.delete(DELETE_LECTURE_ENDPOINT_URL, id);
	}
}
