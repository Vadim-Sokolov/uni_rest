package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.Auditorium;

public interface AuditoriumDao extends JpaRepository<Auditorium, Integer> {

}
