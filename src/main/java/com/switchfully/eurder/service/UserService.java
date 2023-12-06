package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Role;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.dto.CreateUserDto;
import com.switchfully.eurder.dto.UserDto;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDto createUser(CreateUserDto createUserDto, Role role) {

        User user = userRepository.createUser(userMapper.mapCreateUserDtoToUser(createUserDto, role));
        return userMapper.mapUserToUserDto(user);
    }

    public UserDto createCustomer(CreateUserDto createUserDto) {
        return createUser(createUserDto, Role.CUSTOMER);
    }
}
