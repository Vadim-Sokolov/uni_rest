package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rest.model.Course;

public interface CourseDao extends JpaRepository<Course, Integer> {

	@Transactional
	@Modifying
	@Query("delete from Course where id>3")
	void removeTestEntries();
	
	@Query("from Course where name='testCourse'")
	Course findTestEntry();
}
