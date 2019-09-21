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
import com.rest.dao.GroupDao;
import com.rest.model.Faculty;
import com.rest.model.Group;
import com.rest.model.Group;

@RestController
public class GroupController {

	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private FacultyDao facultyDao;

	@PostMapping("/group/faculty/{facultyId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Group addGroup(@RequestBody Group group, @PathVariable("facultyId") int facultyId) {
		Faculty facultyToSet = facultyDao.findById(facultyId).get();
		group.setFaculty(facultyToSet);
		groupDao.save(group);
		return group;
	}

	@PutMapping("/group/faculty/{facultyId}")
	public Group updateGroup(@RequestBody Group group, @PathVariable("facultyId") int facultyId) {
		Faculty facultyToSet = facultyDao.findById(facultyId).get();
		group.setFaculty(facultyToSet);
		groupDao.save(group);
		return group;
	}

	@GetMapping("/groups")
	public List<Group> getGroups() {
		return groupDao.findAll();
	}

	@GetMapping("/group/{id}")
	public Optional<Group> getGroup(@PathVariable("id") int id) {
		return groupDao.findById(id);
	}

	@DeleteMapping("/group/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteGroup(@PathVariable("id") int id) {
		Group group = groupDao.getOne(id);
		String deletedInfo = "Deleted " + group.toString();
		groupDao.delete(group);
		return deletedInfo;
	}
	
	@RequestMapping("group/clearTest")
	public void clearTestEntries() {
		groupDao.removeTestEntries();
	}
	
	@RequestMapping("group/findTestEntry")
	public Group findTestEntry() {
		return groupDao.findTestEntry();
	}
}
