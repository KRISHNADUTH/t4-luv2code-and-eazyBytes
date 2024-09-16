package com.mapping.ManyToOne.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mapping.ManyToOne.model.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {
    @Query(value = "SELECT c FROM Course c where instructor.id=:id")
    List<Course> findCoursesByInstructorId(Integer id);
    
}
