package com.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.model.Auditorium;
import com.rest.model.Course;
import com.rest.model.Faculty;
import com.rest.model.Group;
import com.rest.model.Lecture;
import com.rest.model.Teacher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalTime;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class LectureControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private LectureController controller;

	@Before
	public void init() {
		controller.clearTestEntries();
	}

	@Test
	public void getLectures() throws Exception {
		mockMvc.perform(get("/lectures")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].time").value("09:00"))
				.andExpect(jsonPath("$[1].time").value("11:00"))
				.andExpect(jsonPath("$[2].time").value("14:00"));
	}

	@Test
	public void getOne() throws Exception {
		mockMvc.perform(get("/lecture/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.time").value("09:00"));
	}

	@Test
	public void create() throws Exception {
		//Given
		Lecture a = new Lecture();
		Faculty f = new Faculty();
		f.setId(2);
		f.setName("Biology");
		Auditorium auditorium = new Auditorium();
		auditorium.setId(2);
		auditorium.setName("A2");
		auditorium.setCapacity(50);
		Course course = new Course();
		course.setId(2);
		course.setName("Primates");
		course.setNumberOfWeeks(20);
		course.setDescription("Biology 2d year");
		Group group = new Group();
		group.setId(4);
		group.setFaculty(f);
		group.setName("B1");
		Teacher teacher = new Teacher();
		teacher.setId(2);
		teacher.setFaculty(f);
		teacher.setFirstName("Jenna");
		teacher.setLastName("Marbles");
		LocalTime time = LocalTime.of(19, 00);
		a.setAuditorium(auditorium);
		a.setCourse(course);
		a.setGroup(group);
		a.setTeacher(teacher);
		a.setTime(time);
		
		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		//Then
		mockMvc.perform(
				MockMvcRequestBuilders.post("/lecture").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.auditorium").value(auditorium))
				.andExpect(jsonPath("$.course").value(course))
				.andExpect(jsonPath("$.group").value(group))
				.andExpect(jsonPath("$.teacher").value(teacher))
				.andExpect(jsonPath("$.time").value("19:00"));
	}

	@Test
	public void update() throws Exception {
		//Given
		Lecture a = new Lecture();
		Faculty f = new Faculty();
		f.setId(2);
		f.setName("Biology");
		Auditorium auditorium = new Auditorium();
		auditorium.setId(2);
		auditorium.setName("A2");
		auditorium.setCapacity(50);
		Course course = new Course();
		course.setId(2);
		course.setName("Primates");
		course.setNumberOfWeeks(20);
		course.setDescription("Biology 2d year");
		Group group = new Group();
		group.setId(4);
		group.setFaculty(f);
		group.setName("B1");
		Teacher teacher = new Teacher();
		teacher.setId(2);
		teacher.setFaculty(f);
		teacher.setFirstName("Jenna");
		teacher.setLastName("Marbles");
		LocalTime time = LocalTime.of(19, 00);
		a.setAuditorium(auditorium);
		a.setCourse(course);
		a.setGroup(group);
		a.setTeacher(teacher);
		a.setTime(time);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		//When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/lecture").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));
		
		s = mapper.writeValueAsString(a);

		//Then
		mockMvc.perform(
				MockMvcRequestBuilders.put("/lecture").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("newName"))
				.andExpect(jsonPath("$.numberOfWeeks").value(3))
				.andExpect(jsonPath("$.description").value("newDescription"));
	}
	
	@Test
	public void delete() throws Exception {
		//Given
		Lecture a = new Lecture();

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		//When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/lecture").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));
		
		Integer idToDelete = controller.findTestEntry().getId();
		
		//Then
		mockMvc.perform(MockMvcRequestBuilders.delete("/lecture/{id}", idToDelete)).andExpect(status().isNoContent());
	}
}

