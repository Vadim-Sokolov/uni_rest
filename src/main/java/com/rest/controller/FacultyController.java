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

import com.rest.dao.FacultyDao;
import com.rest.model.Faculty;

@RestController
public class FacultyController {

	@Autowired
	private FacultyDao facultyDao;

	@PostMapping("/faculty")
	public Faculty addFaculty(@RequestBody Faculty faculty) {
		facultyDao.save(faculty);
		return faculty;
	}

	@PutMapping("/faculty")
	public Faculty updateFaculty(@RequestBody Faculty faculty) {
		facultyDao.save(faculty);
		return faculty;
	}

	@GetMapping("/faculties")
	public List<Faculty> getFacultys() {
		return facultyDao.findAll();
	}

	@GetMapping("/faculty/{id}")
	public Optional<Faculty> getFaculty(@PathVariable("id") int id) {
		return facultyDao.findById(id);
	}

	@DeleteMapping("/faculty/{id}")
	public String deleteFaculty(@PathVariable("id") int id) {
		Faculty faculty = facultyDao.getOne(id);
		String deletedInfo = "Deleted " + faculty.toString();
		facultyDao.delete(faculty);
		return deletedInfo;
	}
}
