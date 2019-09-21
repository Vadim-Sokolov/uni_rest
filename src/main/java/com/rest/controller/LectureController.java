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

import com.rest.dao.AuditoriumDao;
import com.rest.dao.CourseDao;
import com.rest.dao.GroupDao;
import com.rest.dao.LectureDao;
import com.rest.dao.TeacherDao;
import com.rest.model.Lecture;

@RestController
public class LectureController {

	@Autowired
	private LectureDao lectureDao;
	@Autowired
	private AuditoriumDao auditoriumDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private TeacherDao teacherDao;

	@PostMapping("/lecture/auditorium/{auditoriumId}/course/{courseId}/group/{groupId}/teacher/{teacherId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Lecture addLecture(@RequestBody Lecture lecture, @PathVariable("auditoriumId") int auditoriumId, 
			@PathVariable("courseId") int courseId, @PathVariable("groupId") int groupId, 
			@PathVariable("teacherId") int teacherId) {
		lecture.setAuditorium(auditoriumDao.findById(auditoriumId).get());
		lecture.setCourse(courseDao.findById(courseId).get());
		lecture.setGroup(groupDao.findById(groupId).get());
		lecture.setTeacher(teacherDao.findById(teacherId).get());
		lectureDao.save(lecture);
		return lecture;
	}

	@PutMapping("/lecture/auditorium/{auditoriumId}/course/{courseId}/group/{groupId}/teacher/{teacherId}")
	public Lecture updateLecture(@RequestBody Lecture lecture, @PathVariable("auditoriumId") int auditoriumId, 
			@PathVariable("courseId") int courseId, @PathVariable("groupId") int groupId, 
			@PathVariable("teacherId") int teacherId) {
		lecture.setAuditorium(auditoriumDao.findById(auditoriumId).get());
		lecture.setCourse(courseDao.findById(courseId).get());
		lecture.setGroup(groupDao.findById(groupId).get());
		lecture.setTeacher(teacherDao.findById(teacherId).get());
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
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteLecture(@PathVariable("id") int id) {
		Lecture lecture = lectureDao.getOne(id);
		String deletedInfo = "Deleted " + lecture.toString();
		lectureDao.delete(lecture);
		return deletedInfo;
	}
	
	@RequestMapping("lecture/clearTest")
	public void clearTestEntries() {
		lectureDao.removeTestEntries();
	}
	
	@RequestMapping("lecture/findTestEntry")
	public Lecture findTestEntry() {
		return lectureDao.findTestEntry();
	}
}
