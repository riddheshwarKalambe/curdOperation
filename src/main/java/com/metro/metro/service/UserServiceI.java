package com.metro.metro.service;

import com.metro.metro.dto.UserDto;
import com.metro.metro.entity.User;

import java.util.List;


public interface UserServiceI {

    // create User
    UserDto createUser(UserDto userDto);

    //Update User
    UserDto updateUser(UserDto userDto, Long UserId);

    //Patch User
    UserDto patchUser(UserDto userDto, Long UserId);

    // get user By UserId
    User getSingleUser(Long userId);

    // get all users
    List<UserDto> getAll();

    // Delete User By Id
    void deleteUserById(Long UserId);

    // Delete All users // formate Data
    void deleteAllUsers();


}
