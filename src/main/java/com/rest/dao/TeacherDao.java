package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rest.model.Teacher;
import com.rest.model.Teacher;

public interface TeacherDao extends JpaRepository<Teacher, Integer>{

	@Transactional
	@Modifying
	@Query("delete from Teacher where id>7")
	void removeTestEntries();
	
	@Query("from Teacher where firstName='testTeacher'")
	Teacher findTestEntry();
}
