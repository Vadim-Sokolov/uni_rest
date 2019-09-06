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
import com.rest.model.Faculty;
import com.rest.model.Teacher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TeacherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TeacherController controller;

	@Before
	public void init() {
		controller.clearTestEntries();
	}

	@Test
	public void getTeachers() throws Exception {
		mockMvc.perform(get("/teachers")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].firstName").value("Rob"))
				.andExpect(jsonPath("$[1].firstName").value("Jenna"))
				.andExpect(jsonPath("$[2].firstName").value("Lulu"));
	}

	@Test
	public void getOne() throws Exception {
		mockMvc.perform(get("/teacher/2")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("Jenna"));
	}

	@Test
	public void create() throws Exception {
		// Given
		Teacher a = new Teacher();
		a.setFirstName("testTeacher");
		a.setLastName("b");
		Faculty f = new Faculty();
		f.setId(1);
		f.setName("Maths");
		a.setFaculty(f);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.post("/teacher").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.firstName").value("testTeacher")).andExpect(jsonPath("$.faculty").value(f));
	}

	@Test
	public void update() throws Exception {
		// Given
		Teacher a = new Teacher();
		a.setFirstName("testTeacher");
		a.setLastName("b");
		Faculty f = new Faculty();
		f.setId(1);
		f.setName("Maths");
		a.setFaculty(f);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/teacher").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));

		a.setFirstName("newName");
		s = mapper.writeValueAsString(a);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.put("/teacher").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.firstName").value("newName"));
	}

	@Test
	public void delete() throws Exception {
		// Given
		Teacher a = new Teacher();
		a.setFirstName("testTeacher");
		a.setLastName("b");
		Faculty f = new Faculty();
		f.setId(1);
		f.setName("Maths");
		a.setFaculty(f);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/teacher").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));

		Integer idToDelete = controller.findTestEntry().getId();

		// Then
		mockMvc.perform(MockMvcRequestBuilders.delete("/teacher/{id}", idToDelete)).andExpect(status().isNoContent());
	}
}
