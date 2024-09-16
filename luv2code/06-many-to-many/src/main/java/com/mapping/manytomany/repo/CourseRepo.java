package com.mapping.manytomany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mapping.manytomany.model.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c JOIN FETCH c.students WHERE c.id=:id")
    Course findCourseAndStudentsByCourseId(Integer id);
    
}
