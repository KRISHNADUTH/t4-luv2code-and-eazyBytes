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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer primary_key;
    private String address;
    private String hobby;

    // @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)  // Deleting instructorDetail will also delete Instructor.
    // private Instructor instructor;

    @OneToOne(mappedBy = "instructorDetails", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })    // Only deletes an entry from InstructorDetail.
    private Instructor instructor;

    public InstructorDetail(String address, String hobby) {
        this.address = address;
        this.hobby = hobby;
    }

    @Override
    public String toString() {                  // Since we have given mappedBy = "instructorDetail" for instructor it will not be considered as a parameter here.
        return "InstructorDetail [primary_key=" + primary_key + ",address=" + address + ", hobby=" + hobby + "]";
    }

    // @Override    - This toString() method will throw error because instructor is not having any value here. Initially only address and hobby will have values. primary_key will get value automatically.
    // public String toString() {
    //     return "InstructorDetail [primary_key=" + primary_key + ", address=" + address + ", hobby=" + hobby + ", instructor=" + instructor
    //             + "]";
    // }
}
