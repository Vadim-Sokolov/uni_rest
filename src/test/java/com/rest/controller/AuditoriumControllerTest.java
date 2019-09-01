package com.rest.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuditoriumControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private AuditoriumController controller;

	@Test
	public void getAuditoriums() throws Exception {
		mockMvc.perform(get("/auditoriums")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$[0].name").value("A1"));
	}
	
	@Test
	public void getOne() throws Exception {
		mockMvc.perform(get("/auditorium/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("A1"));
	}
	
	@Test
	public void create() {
		
	}
}
