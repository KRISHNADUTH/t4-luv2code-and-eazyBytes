package com.mapping.onetoone.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String firstName;
    String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    InstructorDetail instructorDetails;

    public Instructor(String firstName, String lastName, InstructorDetail details) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.instructorDetails = details;
    }
}    