package com.library.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.dto.UserDto;
import com.library.entities.User;
import com.library.mapper.UserMapper;
import com.library.repositories.UserRepository;
import com.library.services.UserService;

public class UserServiceImpl implements UserService{

	 @Autowired
	    private UserRepository userRepository;

	    @Override
	    public UserDto createUser(UserDto userDto) {
	        User user = UserMapper.mapToUser(userDto);
	        User savedUser = userRepository.save(user);
	        return UserMapper.mapToUserDto(savedUser);
	    }

	    @Override
	    public List<UserDto> getUsers() {
	        List<User> users = userRepository.findAll();
	        return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
	    }
}
