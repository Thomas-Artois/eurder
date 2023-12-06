package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.Role;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.dto.CreateUserDto;
import com.switchfully.eurder.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapCreateUserDtoToUser(CreateUserDto createUserDto, Role role) {
        return new User(createUserDto.getFirstName(), createUserDto.getLastName(), createUserDto.getEmail(), createUserDto.getAddress(), createUserDto.getPhoneNumber(), role, createUserDto.getPassword());
    }

    public UserDto mapUserToUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getPhoneNumber(), user.getRole());
    }
}
