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
import com.rest.model.Group;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class GroupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GroupController controller;

	@Before
	public void init() {
		controller.clearTestEntries();
	}

	@Test
	public void getGroups() throws Exception {
		mockMvc.perform(get("/groups")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(6)))
				.andExpect(jsonPath("$[0].name").value("M1"))
				.andExpect(jsonPath("$[1].name").value("M2"))
				.andExpect(jsonPath("$[2].name").value("M3"));
	}

	@Test
	public void getOne() throws Exception {
		mockMvc.perform(get("/group/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("M1"));
	}

	@Test
	public void create() throws Exception {
		// Given
		Group a = new Group();
		a.setName("testGroup");
		Faculty f = new Faculty();
		f.setId(11);
		f.setName("History");
		a.setFaculty(f);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.post("/group").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("testGroup"))
				.andExpect(jsonPath("$.faculty").value(f));
	}

	@Test
	public void update() throws Exception {
		// Given
		Group a = new Group();
		a.setName("testGroup");
		Faculty f = new Faculty();
		f.setId(11);
		a.setFaculty(f);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// When
		mockMvc.perform(MockMvcRequestBuilders.post("/group").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));

		a.setName("newName");
		s = mapper.writeValueAsString(a);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.put("/group").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("newName"));
	}

	@Test
	public void delete() throws Exception {
		// Given
		Group a = new Group();
		a.setName("testGroup");

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// When
		mockMvc.perform(MockMvcRequestBuilders.post("/group").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));

		Integer idToDelete = controller.findTestEntry().getId();

		// Then
		mockMvc.perform(MockMvcRequestBuilders.delete("/group/{id}", idToDelete)).andExpect(status().isNoContent());
	}
}
