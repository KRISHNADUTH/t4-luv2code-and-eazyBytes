package com.mapping.onetoone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapping.onetoone.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{
}