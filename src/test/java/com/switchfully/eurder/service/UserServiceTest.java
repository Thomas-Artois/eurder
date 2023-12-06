package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Role;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.dto.CreateUserDto;
import com.switchfully.eurder.dto.UserDto;
import com.switchfully.eurder.exception.EmailAlreadyExistsException;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {
//    @Autowired
    private UserService userService;
//    @Autowired
    private UserMapper userMapper;
//    @Autowired
    private UserRepository userRepository;

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

    @Test
    void givenUserService_whenCreateUserWithDuplicateEmail_thenEmailAlreadyExistsExceptionIsThrown() {
        //GIVEN
        Address validAddress = new Address("Street", "Number", "Postalcode", "City");
        User validUser = new User("firstName", "lastName", "email@host.com", validAddress, "0494556677", Role.CUSTOMER, "validPassword");
        User userWithDuplicateEmail = new User("ertertert", "cfgdfgdfg", "jozef@hotmail.com", validAddress, "0494556677", Role.CUSTOMER, "validPassword");
        CreateUserDto createUserDto = new CreateUserDto(userWithDuplicateEmail.getFirstName(), userWithDuplicateEmail.getLastName(),
                userWithDuplicateEmail.getEmail(), userWithDuplicateEmail.getAddress(), userWithDuplicateEmail.getPhoneNumber(),
                userWithDuplicateEmail.getRole(), userWithDuplicateEmail.getPassword());


        //WHEN
        userRepository.createUser(validUser);

        //THEN
        assertThrows(EmailAlreadyExistsException.class, () -> userService.createCustomer(createUserDto));
    }
    @Test
    void givenUserService_whenGetAllCustomers_thenReturnListOfCustomerUserDto() {
        //GIVEN

        //WHEN & THEN
        assertThat(userService.getAllCustomers()).allSatisfy(userDto -> assertThat(userDto).isInstanceOf(UserDto.class));
        assertThat(userService.getAllCustomers()).allSatisfy(userDto -> assertThat(userDto.getRole()).isEqualTo(Role.CUSTOMER));
    }

}
