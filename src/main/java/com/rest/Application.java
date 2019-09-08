package com.rest;

import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rest.client.AuditoriumClient;
import com.rest.client.FacultyClient;
import com.rest.client.GroupClient;
import com.rest.client.LectureClient;
import com.rest.client.StudentClient;
import com.rest.client.TeacherClient;
import com.rest.model.Auditorium;
import com.rest.model.Course;
import com.rest.model.Faculty;
import com.rest.model.Group;
import com.rest.model.Teacher;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		TeacherClient a = new TeacherClient();
		
		//a.getAll();
		System.out.println("**************");
		System.out.println();
		
		//a.getById(2);
		System.out.println("**************");
		System.out.println();
		
		Faculty f = new Faculty();
		f.setId(2);
		f.setName("Biology");
		Auditorium aud = new Auditorium();
		aud.setId(2);
		aud.setName("A2");
		aud.setCapacity(50);
		Course c = new Course();
		c.setId(2);
		c.setName("Primates");
		c.setNumberOfWeeks(20);
		c.setDescription("Biology 2d year");
		Group g = new Group();
		g.setId(4);
		g.setFaculty(f);
		g.setName("B1");
		Teacher t = new Teacher();
		t.setId(2);
		t.setFaculty(f);
		t.setFirstName("Jenna");
		t.setLastName("Marbles");
		//a.create("first", "last", f);
		System.out.println("**************");
		System.out.println();
		
		a.update(19, "newFirst", "newLast", f);
		System.out.println("**************");
		System.out.println("updated");
		System.out.println();
		a.getAll();
		
		a.delete(19);
		System.out.println("**************");
		System.out.println("deleted");
		System.out.println();
		a.getAll();
	}
}
