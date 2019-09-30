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
import com.rest.model.LectureDTO;

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

	@PostMapping("/lectures")
	@ResponseStatus(HttpStatus.CREATED)
	public Lecture addLecture(@RequestBody LectureDTO lectureDTO) {
		Lecture lectureToSave = new Lecture();
		lectureToSave.setAuditorium(auditoriumDao.findById(lectureDTO.getAuditoriumId()).get());
		lectureToSave.setCourse(courseDao.findById(lectureDTO.getCourseId()).get());
		lectureToSave.setGroup(groupDao.findById(lectureDTO.getGroupId()).get());
		lectureToSave.setTeacher(teacherDao.findById(lectureDTO.getTeacherId()).get());
		lectureToSave.setTime(lectureDTO.getTime());
		return lectureDao.save(lectureToSave);
	}

	@PutMapping("/lectures/{id}")
	public Lecture updateLecture(@RequestBody LectureDTO lectureDTO, @PathVariable("id") int id) {
		Lecture lectureToSave = new Lecture();
		lectureToSave.setId(id);
		lectureToSave.setAuditorium(auditoriumDao.findById(lectureDTO.getAuditoriumId()).get());
		lectureToSave.setCourse(courseDao.findById(lectureDTO.getCourseId()).get());
		lectureToSave.setGroup(groupDao.findById(lectureDTO.getGroupId()).get());
		lectureToSave.setTeacher(teacherDao.findById(lectureDTO.getTeacherId()).get());
		lectureToSave.setTime(lectureDTO.getTime());
		return lectureDao.save(lectureToSave);
	}

	@GetMapping("/lectures")
	public List<Lecture> getLectures() {
		return lectureDao.findAll();
	}

	@GetMapping("/lectures/{id}")
	public Optional<Lecture> getLecture(@PathVariable("id") int id) {
		return lectureDao.findById(id);
	}

	@DeleteMapping("/lectures/{id}")
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
