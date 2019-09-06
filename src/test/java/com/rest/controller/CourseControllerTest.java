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
import com.rest.model.Course;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CourseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CourseController controller;

	@Before
	public void init() {
		controller.clearTestEntries();
	}

	@Test
	public void getCourses() throws Exception {
		mockMvc.perform(get("/courses")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].name").value("Bismark"))
				.andExpect(jsonPath("$[1].name").value("Primates"))
				.andExpect(jsonPath("$[2].name").value("Calculus"));
	}

	@Test
	public void getOne() throws Exception {
		mockMvc.perform(get("/course/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Calculus"));
	}

	@Test
	public void create() throws Exception {
		//Given
		Course a = new Course();
		a.setName("testCourse");
		a.setNumberOfWeeks(5);
		a.setDescription("description");

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		//Then
		mockMvc.perform(
				MockMvcRequestBuilders.post("/course").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("testCourse"))
				.andExpect(jsonPath("$.numberOfWeeks").value(5))
				.andExpect(jsonPath("$.description").value("description"));
	}

	@Test
	public void update() throws Exception {
		//Given
		Course a = new Course();
		a.setName("testCourse");
		a.setNumberOfWeeks(5);
		a.setDescription("description");

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		//When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/course").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));
		
		a.setName("newName");
		a.setNumberOfWeeks(3);
		a.setDescription("newDescription");
		s = mapper.writeValueAsString(a);

		//Then
		mockMvc.perform(
				MockMvcRequestBuilders.put("/course").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("newName"))
				.andExpect(jsonPath("$.numberOfWeeks").value(3))
				.andExpect(jsonPath("$.description").value("newDescription"));
	}
	
	@Test
	public void delete() throws Exception {
		//Given
		Course a = new Course();
		a.setName("testCourse");
		a.setNumberOfWeeks(5);
		a.setDescription("description");

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		//When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/course").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));
		
		Integer idToDelete = controller.findTestEntry().getId();
		
		//Then
		mockMvc.perform(MockMvcRequestBuilders.delete("/course/{id}", idToDelete)).andExpect(status().isNoContent());
	}
}
