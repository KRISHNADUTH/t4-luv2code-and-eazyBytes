package com.mapping.OneToMany.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mapping.OneToMany.model.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{
    @Query(value = "SELECT * FROM course where instructor_id=:id", nativeQuery = true)
    List<Course> findCoursesByInstructorId(Integer id);

    @Query(value = "SELECT c FROM Course c JOIN FETCH c.reviews WHERE c.id=:id")
    Course findCourseWithReviewByCourseIdJoinFetch(Integer id);
    
}
