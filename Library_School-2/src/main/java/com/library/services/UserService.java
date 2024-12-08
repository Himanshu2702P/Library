package com.library.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.dto.UserDto;
@Service
public interface UserService {

	UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();
}
