package com.mapping.OneToMany.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer id;

    private String comment;

    public Review(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + ", comment=" + comment + "]";
    }
}
