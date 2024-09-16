package com.mapping.OneToMany.model;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")  // With this line column named "course_id" will be created in table which is in Many side of the relationship - https://chat.openai.com/share/aa04ecac-8111-460e-bc37-1c9cec30cfa7
    private List<Review> reviews;

    public Course(String title) {
        this.title = title;
    }

    // Convinience method - 
    public void addReview(Review newReview)
    {
        if(reviews == null)
            reviews = new ArrayList<>();
        reviews.add(newReview);
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }

}
