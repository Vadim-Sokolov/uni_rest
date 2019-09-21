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

import com.rest.dao.FacultyDao;
import com.rest.dao.TeacherDao;
import com.rest.model.Teacher;

@RestController
public class TeacherController {

	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private FacultyDao facultyDao; 

	@PostMapping("/teacher/faculty/{facultyId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Teacher addTeacher(@RequestBody Teacher teacher, @PathVariable("facultyId") int facultyId) {
		teacher.setFaculty(facultyDao.findById(facultyId).get());
		teacherDao.save(teacher);
		return teacher;
	}

	@PutMapping("/teacher/{teacherId}/faculty/{facultyId}")
	public Teacher updateTeacher(@RequestBody Teacher teacher,
			@PathVariable("teacherId") int teacherId, @PathVariable("facultyId") int facultyId) {
		teacher.setId(teacherId);
		teacher.setFaculty(facultyDao.findById(facultyId).get());
		teacherDao.save(teacher);
		return teacher;
	}

	@GetMapping("/teachers")
	public List<Teacher> getTeachers() {
		return teacherDao.findAll();
	}

	@GetMapping("/teacher/{id}")
	public Optional<Teacher> getTeacher(@PathVariable("id") int id) {
		return teacherDao.findById(id);
	}

	@DeleteMapping("/teacher/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteTeacher(@PathVariable("id") int id) {
		Teacher teacher = teacherDao.getOne(id);
		String deletedInfo = "Deleted " + teacher.toString();
		teacherDao.delete(teacher);
		return deletedInfo;
	}
	
	@RequestMapping("teacher/clearTest")
	public void clearTestEntries() {
		teacherDao.removeTestEntries();
	}
	
	@RequestMapping("teacher/findTestEntry")
	public Teacher findTestEntry() {
		return teacherDao.findTestEntry();
	}
}
