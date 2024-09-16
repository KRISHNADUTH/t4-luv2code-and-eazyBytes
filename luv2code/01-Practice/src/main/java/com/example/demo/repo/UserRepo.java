package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserData;

@Repository
public interface UserRepo extends JpaRepository<UserData,Integer>{

    boolean existsByName(String name);

    UserData findByName(String name);
}
