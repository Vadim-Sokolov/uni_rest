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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuditoriumControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuditoriumController controller;

	@Before
	public void init() {
		controller.clearTestEntries();
	}

	@Test
	public void getAuditoriums() throws Exception {
		mockMvc.perform(get("/auditoriums")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].name").value("A1")).andExpect(jsonPath("$[1].name").value("A3"))
				.andExpect(jsonPath("$[2].name").value("A2"));
	}

	@Test
	public void getOne() throws Exception {
		mockMvc.perform(get("/auditorium/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("A1"));
	}

	@Test
	public void create() throws Exception {
		//Given
		Auditorium a = new Auditorium();
		a.setName("testAuditorium");
		a.setCapacity(100);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		//Then
		mockMvc.perform(
				MockMvcRequestBuilders.post("/auditorium").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("testAuditorium"));
	}

	@Test
	public void update() throws Exception {
		//Given
		Auditorium a = new Auditorium();
		a.setName("testAuditorium");
		a.setCapacity(100);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		//When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/auditorium").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));
		
		a.setName("newName");
		s = mapper.writeValueAsString(a);

		//Then
		mockMvc.perform(
				MockMvcRequestBuilders.put("/auditorium").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value("newName"));
	}
	
	@Test
	public void delete() throws Exception {
		//Given
		Auditorium a = new Auditorium();
		a.setName("testAuditorium");
		a.setCapacity(100);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		//When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/auditorium").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));
		
		Integer idToDelete = controller.findTestEntry().getId();
		
		//Then
		mockMvc.perform(MockMvcRequestBuilders.delete("/auditorium/{id}", idToDelete)).andExpect(status().isNoContent());
	}
}
