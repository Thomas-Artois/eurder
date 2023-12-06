package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Role;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {
    UserService userService;
    UserMapper userMapper;
    UserRepository userRepository;

    @BeforeEach
    void setUpUserService() {
        userMapper = new UserMapper();
        userRepository = new UserRepository();
        userService = new UserService(userMapper, userRepository);
    }

    @Test
    void givenValidUser_whenCreateUser_thenCustomerIsAdded() {
        //GIVEN
        Address validAddress = new Address("Street", "Number", "Postalcode", "City");
        User validUser = new User("firstName", "lastName", "email@host.com", validAddress, "0494556677", Role.CUSTOMER, "validPassword");

        //WHEN
        userRepository.createUser(validUser);

        //THEN
        assertThat(userRepository.getAllUsers()).allSatisfy(user -> assertThat(user).isInstanceOf(User.class));
        assertThat(userRepository.getAllUsers()).contains(validUser);
    }
}
