package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserData;
import com.example.demo.model.UserDto;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {
    UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public Object registerUser(UserDto userDto) {
        try {
            if (userDto == null) {
                return new ResponseEntity<>("Nothing present in User data", HttpStatus.BAD_REQUEST);
            } else {
                if (repo.existsByName(userDto.getName())) {
                    return new ResponseEntity<>("User name already exists"+repo.findByName(userDto.getName().toString()), HttpStatus.BAD_REQUEST);
                }
                UserData newUser = new UserData();
                newUser.setHobby(userDto.getHobby());
                newUser.setName(userDto.getName());
                repo.save(newUser);
                return new ResponseEntity<>("User added - " , HttpStatus.OK);
            }
        } 
        catch (Exception e) {
            return new ResponseEntity<>("Something went wrong " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        
    }
    

}
