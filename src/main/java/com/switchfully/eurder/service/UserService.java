package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Role;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.dto.CreateUserDto;
import com.switchfully.eurder.dto.UserDto;
import com.switchfully.eurder.exception.EmailAlreadyExistsException;
import com.switchfully.eurder.exception.NotACustomerException;
import com.switchfully.eurder.exception.NotAnAdminException;
import com.switchfully.eurder.exception.PasswordIsIncorrectException;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDto createUser(CreateUserDto createUserDto, Role role) throws EmailAlreadyExistsException {
        userRepository.checkIfEmailExists(createUserDto.getEmail());


        User user = userRepository.createUser(userMapper.mapCreateUserDtoToUser(createUserDto, role));
        return userMapper.mapUserToUserDto(user);
    }

    public UserDto createCustomer(CreateUserDto createUserDto) {
        return createUser(createUserDto, Role.CUSTOMER);
    }

    public List<UserDto> getAllCustomers() {
        return userRepository.getAllCustomers().stream().map(user -> userMapper.mapUserToUserDto(user)).collect(Collectors.toList());
    }

    public void checkIfUserIsAdmin(String email, String password) throws NotAnAdminException {
        User user = userRepository.getUserByEmail(email);
        checkIfPasswordIsCorrect(user, password);

        if (user.getRole() != Role.ADMIN) {
            throw new NotAnAdminException();
        }
    }

    public void checkIfUserIsCustomer(String email, String password) throws NotACustomerException {
        User user = userRepository.getUserByEmail(email);
        checkIfPasswordIsCorrect(user, password);

        if (user.getRole() != Role.CUSTOMER) {
            throw new NotACustomerException();
        }
    }

    public void checkIfPasswordIsCorrect(User user, String password) throws PasswordIsIncorrectException {
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new PasswordIsIncorrectException();
        }
    }

    public UserDto getCustomerBasedOnId(String id) {
        User user = userRepository.getCustomerBasedOnId(id);
        return userMapper.mapUserToUserDto(user);
    }
}
