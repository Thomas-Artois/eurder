package com.switchfully.eurder.controller;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Role;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.dto.CreateUserDto;
import com.switchfully.eurder.dto.UserDto;
import com.switchfully.eurder.repository.UserRepository;
import com.switchfully.eurder.service.UserService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerIntegrationTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @LocalServerPort
    private int port;

    @Test
    void whenCreateCustomer_thenReturnCustomerUserDto() {
        //GIVEN
        String firstName = "first";
        String lastName = "last";
        String email = "first@hotmail.com";
        Address address = new Address("streetname", "streetnumber", "postalcode", "city");
        String phoneNumber = "0454667788";
        Role role = Role.CUSTOMER;
        String password = "password";
        CreateUserDto createUserDto = new CreateUserDto(firstName, lastName, email, address, phoneNumber, role, password);

        //WHEN
        UserDto userDto =
                RestAssured
                        .given()
                        .body(createUserDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/users")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(UserDto.class);

        //THEN
        assertThat(userDto.getFirstName()).isEqualTo("first");
        assertThat(userDto.getAddress().getCity()).isEqualTo("city");
        assertThat(userDto.getRole()).isEqualTo(Role.CUSTOMER);

    }

    @Test
    void whenAdminViewsAllCustomers_thenReturnListOfAllCustomers() {
        //GIVEN
        User adminUser = userRepository.getUserByEmail("admin@eurder.com");

        //WHEN
        List<UserDto> userDtoList =
                RestAssured
                        .given()
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .get("/users?email="+adminUser.getEmail()+"&password=adminPassword")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", UserDto.class);

        assertThat(userDtoList).allSatisfy(userDto -> assertThat(userDto).isInstanceOf(UserDto.class));
    }
}
