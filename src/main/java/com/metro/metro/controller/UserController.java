package com.metro.metro.controller;

import com.metro.metro.dto.UserDto;
import com.metro.metro.entity.User;
import com.metro.metro.service.UserServiceI;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceI userServiceI;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto saveUserDto = userServiceI.createUser(userDto);
        logger.info("User created successfully with ID: {}", saveUserDto.getUserId());
        return new ResponseEntity<>(saveUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{user_id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long user_id) {
        UserDto saveUserDto = userServiceI.updateUser(userDto, user_id);
        return new ResponseEntity<>(saveUserDto, HttpStatus.OK);
    }

    @PatchMapping("/patchUser/{user_id}")
    public ResponseEntity<UserDto> patchUser(@RequestBody UserDto userDto, @PathVariable Long user_id) {
        UserDto saveUserDto = userServiceI.patchUser(userDto, user_id);
        return new ResponseEntity<>(saveUserDto, HttpStatus.OK);

    }

    @GetMapping("/singleUser/{UserId}")
    public ResponseEntity<User> getSingleUser(@PathVariable("UserId") Long UserId) {
        User singleUser = userServiceI.getSingleUser(UserId);
        return new ResponseEntity<>(singleUser, HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userServiceI.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @DeleteMapping("/singleUser/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        userServiceI.deleteUserById(userId);
        return new ResponseEntity<>("User Deleted Successfully ", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUsers")
    public ResponseEntity<String> deleteAllUsers() {
        userServiceI.deleteAllUsers();
        return new ResponseEntity<>("All Users Deleted Successfully ", HttpStatus.OK);
    }


}
