package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rest.model.Group;

public interface GroupDao extends JpaRepository<Group, Integer> {

	@Transactional
	@Modifying
	@Query("delete from Group where id>6")
	void removeTestEntries();
	
	@Query("from Group where name='testGroup'")
	Group findTestEntry();
}
