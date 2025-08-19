package com.metro.metro.service.Impl;

import com.metro.metro.dto.UserDto;
import com.metro.metro.entity.User;
import com.metro.metro.exception.ResourceNotFoundException;
import com.metro.metro.repository.UserRepository;
import com.metro.metro.service.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    // create user
    @Override
    public UserDto createUser(UserDto userDto) {

        log.debug("Converting UserDto to User entity: {}", userDto);
        User user = userDtointoUser(userDto);

        User savedUsed = userRepository.save(user);

        UserDto savedUserDto = userintoUserDto(savedUsed);

        log.info("User created successfully with ID: {}", savedUserDto.getUserId());

        return savedUserDto;
    }

    // update user
    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found !!"));

        user.setUserName(userDto.getUserName());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(user);

        UserDto updateduserDto = userintoUserDto(updatedUser);


        return updateduserDto;
    }

    // patch logic
    @Override
    public UserDto patchUser(UserDto userDto, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found !!"));

        user.setAddress(userDto.getAddress());
        User patchUser = userRepository.save(user);

        UserDto patchUserDto = mapper.map(patchUser, UserDto.class);

        return patchUserDto;
    }

    @Override
    public User getSingleUser(Long UserId) {
        return userRepository.findById(UserId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

    }

    @Override
    public List<UserDto> getAll() {
        List<User> allusers = userRepository.findAll();
        List<UserDto> userDtos = allusers.stream().map((user) -> this.mapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUserById(Long userId) {

        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found !!"));
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }


    private User userDtointoUser(UserDto userDto) {

        return mapper.map(userDto, User.class);
    }

    private UserDto userintoUserDto(User user) {
        return mapper.map(user, UserDto.class);
    }


}
