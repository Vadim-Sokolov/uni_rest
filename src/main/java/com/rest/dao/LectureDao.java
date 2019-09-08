package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rest.model.Lecture;

public interface LectureDao extends JpaRepository<Lecture, Integer> {

	@Transactional
	@Modifying
	@Query("delete from Lecture where id>3")
	void removeTestEntries();
	
	@Query("from Lecture where time='19:00:00'")
	Lecture findTestEntry();
}
