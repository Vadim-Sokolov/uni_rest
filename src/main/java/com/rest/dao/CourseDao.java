package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.Course;

public interface CourseDao extends JpaRepository<Course, Integer> {

}
