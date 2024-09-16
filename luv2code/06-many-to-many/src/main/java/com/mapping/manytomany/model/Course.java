package com.mapping.manytomany.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String title;

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable( name = "course_student",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name="student_id") )
    private List<Student> students;

    
    public Course(String title) {
        this.title = title;
    }
    
    // convinience method - 
    public void addStudent(Student newStudent)
    {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(newStudent);
    }
    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }

}
