package com.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dao.CourseDao;
import com.rest.model.Course;

@RestController
public class CourseController {

	@Autowired
	private CourseDao courseDao;
	
	@PostMapping("/course")
	public Course addCourse(@RequestBody Course course) {
		courseDao.save(course);
		return course;
	}
	
	@PutMapping("/course") 
	public Course updateCourse(@RequestBody Course course) {
		courseDao.save(course);
		return course;
	}

	@GetMapping("/courses")
	public List<Course> getCourses() {
		return courseDao.findAll();
	}

	@GetMapping("/course/{id}")
	public Optional<Course> getCourse(@PathVariable("id") int id) {
		return courseDao.findById(id);
	}

	@DeleteMapping("/course/{id}")
	public String deleteCourse(@PathVariable("id") int id) {
		Course course = courseDao.getOne(id);
		String deletedInfo = "Deleted " + course.toString();
		courseDao.delete(course);
		return deletedInfo;
	}
}
