package com.library.mapper;

import com.library.dto.UserDto;
import com.library.entities.User;

public class UserMapper {

	  public static UserDto mapToUserDto(User user){
	        UserDto userDto = new UserDto();
	        userDto.setId(user.getId());
	        userDto.setName(user.getName());
	        return userDto;
	    }

	    public static User mapToUser(UserDto userDto){
	        User user = new User();
	        user.setId(userDto.getId());
	        user.setName(userDto.getName());
	        return user;
	    }
}
