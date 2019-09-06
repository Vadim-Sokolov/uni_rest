package com.rest.controller;

import org.junit.After;
import org.junit.AfterClass;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class FacultyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FacultyController controller;

	@Before
	public void init() {
		controller.clearTestEntries();
	}
	
	@After
	public void clear(){
		controller.clearTestEntries();
	}

	@Test
	public void getFacultys() throws Exception {
		mockMvc.perform(get("/faculties")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].name").value("Biology"))
				.andExpect(jsonPath("$[1].name").value("Maths"))
				.andExpect(jsonPath("$[2].name").value("History"));
	}

	@Test
	public void getOne() throws Exception {
		mockMvc.perform(get("/faculty/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Maths"));
	}

	@Test
	public void create() throws Exception {
		// Given
		Faculty a = new Faculty();
		a.setName("testFaculty");

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.post("/faculty").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("testFaculty"));
	}

	@Test
	public void update() throws Exception {
		// Given
		Faculty a = new Faculty();
		a.setName("testFaculty");

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// When
		mockMvc.perform(MockMvcRequestBuilders.post("/faculty").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));

		a.setName("newName");
		s = mapper.writeValueAsString(a);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.put("/faculty").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("newName"));
	}

	@Test
	public void delete() throws Exception {
		// Given
		Faculty a = new Faculty();
		a.setName("testFaculty");

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// When
		mockMvc.perform(MockMvcRequestBuilders.post("/faculty").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));

		Integer idToDelete = controller.findTestEntry().getId();

		// Then
		mockMvc.perform(MockMvcRequestBuilders.delete("/faculty/{id}", idToDelete)).andExpect(status().isNoContent());
	}
}
