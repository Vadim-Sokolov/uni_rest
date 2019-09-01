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

import com.rest.dao.LectureDao;
import com.rest.model.Lecture;

@RestController
public class LectureController {

	@Autowired
	private LectureDao lectureDao;

	@PostMapping("/lecture")
	public Lecture addLecture(@RequestBody Lecture lecture) {
		lectureDao.save(lecture);
		return lecture;
	}

	@PutMapping("/lecture")
	public Lecture updateLecture(@RequestBody Lecture lecture) {
		lectureDao.save(lecture);
		return lecture;
	}

	@GetMapping("/lectures")
	public List<Lecture> getLectures() {
		return lectureDao.findAll();
	}

	@GetMapping("/lecture/{id}")
	public Optional<Lecture> getLecture(@PathVariable("id") int id) {
		return lectureDao.findById(id);
	}

	@DeleteMapping("/lecture/{id}")
	public String deleteLecture(@PathVariable("id") int id) {
		Lecture lecture = lectureDao.getOne(id);
		String deletedInfo = "Deleted " + lecture.toString();
		lectureDao.delete(lecture);
		return deletedInfo;
	}
}
