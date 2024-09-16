package com.mapping.ManyToOne.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mapping.ManyToOne.model.Course;
import com.mapping.ManyToOne.model.Instructor;

public interface InstructorRepo extends JpaRepository<Instructor, Integer> {

    @Query(value="SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id=:id")
    Instructor findInstructorWithCoursesbyInstructorIdJoinFetch(Integer id);
    
}
