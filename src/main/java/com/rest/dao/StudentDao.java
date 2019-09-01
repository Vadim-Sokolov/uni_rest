package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.Student;

public interface StudentDao extends JpaRepository<Student, Integer> {

}
