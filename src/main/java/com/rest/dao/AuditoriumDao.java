package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rest.model.Auditorium;

public interface AuditoriumDao extends JpaRepository<Auditorium, Integer> {

	@Transactional
	@Modifying
	@Query("delete from Auditorium where id>3")
	void removeTestEntries();
	
	@Query("from Auditorium where name='testAuditorium'")
	Auditorium findTestEntry();
}
