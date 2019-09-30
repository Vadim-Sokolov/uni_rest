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
import com.rest.model.Auditorium;

@RestController
public class AuditoriumController {
	
	@Autowired
	private AuditoriumDao auditoriumDao;
	
	@PostMapping("/auditoriums")
	@ResponseStatus(HttpStatus.CREATED)
	public Auditorium addAuditorium(@RequestBody Auditorium auditorium) {
		return auditoriumDao.save(auditorium);
		//return auditorium;
	}
	
	@PutMapping("/auditoriums/{id}") 
	public Auditorium updateAuditorium(@RequestBody Auditorium auditorium, @PathVariable("id") int id) {
		auditorium.setId(id);
		auditoriumDao.save(auditorium);
		return auditorium;
	}

	@GetMapping("/auditoriums")
	public List<Auditorium> getAuditoriums() {
		return auditoriumDao.findAll();
	}

	@GetMapping("/auditoriums/{id}")
	public Optional<Auditorium> getAuditorium(@PathVariable("id") int id) {
		return auditoriumDao.findById(id);
	}

	@DeleteMapping("/auditoriums/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteAuditorium(@PathVariable("id") int id) {
		Auditorium auditorium = auditoriumDao.getOne(id);
		String deletedInfo = "Deleted " + auditorium.toString();
		auditoriumDao.delete(auditorium);
		return deletedInfo;
	}
	
	@RequestMapping("auditorium/clearTest")
	public void clearTestEntries() {
		auditoriumDao.removeTestEntries();
	}
	
	@RequestMapping("auditorium/findTestEntry")
	public Auditorium findTestEntry() {
		return auditoriumDao.findTestEntry();
	}
}
