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
import com.rest.model.Student;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudentController controller;

	@Before
	public void init() {
		controller.clearTestEntries();
	}

	@Test
	public void getStudents() throws Exception {
		mockMvc.perform(get("/students")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(6)))
				.andExpect(jsonPath("$[0].firstName").value("Roy")).andExpect(jsonPath("$[1].firstName").value("Zoid"))
				.andExpect(jsonPath("$[2].firstName").value("Lee"));
	}

	@Test
	public void getOne() throws Exception {
		mockMvc.perform(get("/student/2")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("Peter"));
	}

	@Test
	public void create() throws Exception {
		// Given
		Student a = new Student();
		a.setFirstName("testStudent");
		a.setLastName("b");
		a.setStudentCardNumber("ll");
		Group g = new Group();
		g.setId(1);
		g.setName("M1");
		Faculty f = new Faculty();
		f.setId(1);
		f.setName("Maths");
		g.setFaculty(f);
		a.setGroup(g);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.post("/student").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.firstName").value("testStudent")).andExpect(jsonPath("$.group").value(g));
	}

	@Test
	public void update() throws Exception {
		// Given
		Student a = new Student();
		a.setFirstName("testStudent");
		a.setLastName("b");
		a.setStudentCardNumber("ll");
		Group g = new Group();
		g.setId(1);
		g.setName("M1");
		Faculty f = new Faculty();
		f.setId(1);
		f.setName("Maths");
		g.setFaculty(f);
		a.setGroup(g);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/student").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));

		a.setFirstName("newName");
		s = mapper.writeValueAsString(a);

		// Then
		mockMvc.perform(MockMvcRequestBuilders.put("/student").contentType(MediaType.APPLICATION_JSON_UTF8).content(s))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.firstName").value("newName"));
	}

	@Test
	public void delete() throws Exception {
		// Given
		Student a = new Student();
		a.setFirstName("testStudent");
		a.setLastName("b");
		a.setStudentCardNumber("ll");
		Group g = new Group();
		g.setId(1);
		g.setName("M1");
		Faculty f = new Faculty();
		f.setId(1);
		f.setName("Maths");
		g.setFaculty(f);
		a.setGroup(g);

		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(a);

		// When
		mockMvc.perform(
				MockMvcRequestBuilders.post("/student").contentType(MediaType.APPLICATION_JSON_UTF8).content(s));

		Integer idToDelete = controller.findTestEntry().getId();

		// Then
		mockMvc.perform(MockMvcRequestBuilders.delete("/student/{id}", idToDelete)).andExpect(status().isNoContent());
	}
}
