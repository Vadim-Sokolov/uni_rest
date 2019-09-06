package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rest.model.Faculty;
import com.rest.model.Faculty;

public interface FacultyDao extends JpaRepository<Faculty, Integer> {

	@Transactional
	@Modifying
	@Query("delete from Faculty where id>11")
	void removeTestEntries();
	
	@Query("from Faculty where name='testFaculty'")
	Faculty findTestEntry();
}
