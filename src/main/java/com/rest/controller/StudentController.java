package com.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dao.StudentDao;
import com.rest.model.Student;
import com.rest.model.Student;

@RestController
public class StudentController {

	@Autowired
	private StudentDao studentDao;

	@PostMapping("/student")
	@ResponseStatus(HttpStatus.CREATED)
	public Student addStudent(@RequestBody Student student) {
		studentDao.save(student);
		return student;
	}
	
	@PutMapping("/student") 
	public Student updateStudent(@RequestBody Student student) {
		studentDao.save(student);
		return student;
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
		return studentDao.findAll();
	}

	@GetMapping("/student/{id}")
	public Optional<Student> getStudent(@PathVariable("id") int id) {
		return studentDao.findById(id);
	}

	@DeleteMapping("/student/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteStudent(@PathVariable("id") int id) {
		Student student = studentDao.getOne(id);
		String deletedInfo = "Deleted " + student.toString();
		studentDao.delete(student);
		return deletedInfo;
	}
	
	@RequestMapping("student/clearTest")
	public void clearTestEntries() {
		studentDao.removeTestEntries();
	}
	
	@RequestMapping("student/findTestEntry")
	public Student findTestEntry() {
		return studentDao.findTestEntry();
	}
}
