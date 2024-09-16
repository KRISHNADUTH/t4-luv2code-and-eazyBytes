package com.mapping.OneToMany.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    
    public Instructor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;


    // Convinence method for bidirectional relationship, with this method new course can be added to an instructor.
    public void addCourse(Course newCourse)
    {
        if(courses == null)
            courses = new ArrayList<>();
        courses.add(newCourse);
        newCourse.setInstructor(this);
    }

    @Override
    public String toString() {
        return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

}
