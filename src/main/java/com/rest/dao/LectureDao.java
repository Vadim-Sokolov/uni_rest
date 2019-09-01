package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.Lecture;

public interface LectureDao extends JpaRepository<Lecture, Integer> {

}
