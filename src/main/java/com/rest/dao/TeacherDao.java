package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.Teacher;

public interface TeacherDao extends JpaRepository<Teacher, Integer>{

}
