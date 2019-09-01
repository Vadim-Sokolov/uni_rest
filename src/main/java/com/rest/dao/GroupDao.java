package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.Group;

public interface GroupDao extends JpaRepository<Group, Integer> {

}
