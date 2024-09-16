package com.mapping.manytomany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mapping.manytomany.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id=:id")
    Student findStudentAndCoursesByStudentId(Integer id);
    
}
