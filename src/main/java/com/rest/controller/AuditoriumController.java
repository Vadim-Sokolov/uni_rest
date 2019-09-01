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

import com.rest.dao.AuditoriumDao;
import com.rest.model.Auditorium;

@RestController
public class AuditoriumController {
	
	@Autowired
	private AuditoriumDao auditoriumDao;
	
	@PostMapping("/auditorium")
	public Auditorium addAuditorium(@RequestBody Auditorium auditorium) {
		auditoriumDao.save(auditorium);
		return auditorium;
	}
	
	@PutMapping("/auditorium") 
	public Auditorium updateAuditorium(@RequestBody Auditorium auditorium) {
		auditoriumDao.save(auditorium);
		return auditorium;
	}

	@GetMapping("/auditoriums")
	public List<Auditorium> getAuditoriums() {
		return auditoriumDao.findAll();
	}

	@GetMapping("/auditorium/{id}")
	public Optional<Auditorium> getAuditorium(@PathVariable("id") int id) {
		return auditoriumDao.findById(id);
	}

	@DeleteMapping("/auditorium/{id}")
	public String deleteAuditorium(@PathVariable("id") int id) {
		Auditorium auditorium = auditoriumDao.getOne(id);
		String deletedInfo = "Deleted " + auditorium.toString();
		auditoriumDao.delete(auditorium);
		return deletedInfo;
	}
}
