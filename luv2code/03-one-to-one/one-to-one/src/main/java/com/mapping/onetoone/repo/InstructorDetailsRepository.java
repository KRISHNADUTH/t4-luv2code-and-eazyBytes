package com.mapping.onetoone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapping.onetoone.model.InstructorDetail;

@Repository
public interface InstructorDetailsRepository extends JpaRepository<InstructorDetail,Integer>{
    
}
