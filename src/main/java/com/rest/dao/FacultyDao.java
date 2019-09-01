package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.Faculty;

public interface FacultyDao extends JpaRepository<Faculty, Integer> {

}
