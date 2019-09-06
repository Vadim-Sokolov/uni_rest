package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rest.model.Student;

public interface StudentDao extends JpaRepository<Student, Integer> {

	@Transactional
	@Modifying
	@Query("delete from Student where id>40")
	void removeTestEntries();
	
	@Query("from Student where firstName='testStudent'")
	Student findTestEntry();
}
