package com.switchfully.eurder.controller;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.dto.CreateItemDto;
import com.switchfully.eurder.dto.ItemDto;
import com.switchfully.eurder.exception.NotAnAdminException;
import com.switchfully.eurder.exception.PasswordIsIncorrectException;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.UserRepository;
import com.switchfully.eurder.service.ItemService;
import com.switchfully.eurder.service.UserService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ItemControllerIntegrationTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserController userController;
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemController itemController;

    @LocalServerPort
    private int port;

    @Test
    void whenCreateItemAsAdmin_thenReturnItemDtoAndAddItemToItemRepository() {
        CreateItemDto createItemDto = new CreateItemDto("Door", "Opens the room", 20.90, 5);

        //THEN
        ItemDto itemDto =
                RestAssured
                        .given()
                        .body(createItemDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/items?email=admin@eurder.com&password=adminPassword")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(ItemDto.class);

        assertThat(itemDto.getName()).isEqualTo("Door");
        assertThat(itemDto.getId()).isNotNull();
    }

    @Test
    void whenCreateItemAsCustomer_thenThrowNotAnAdminException() {
        CreateItemDto createItemDto = new CreateItemDto("Door", "Opens the room", 20.90, 5);

        assertThrows(NotAnAdminException.class, () -> itemController.createItem(createItemDto, "jozef@hotmail.com", "jozefPassword"));
    }

    @Test
    void whenCreateItemAsAdminWithWrongPassword_thenThrowPasswordIsIncorrectException() {
        CreateItemDto createItemDto = new CreateItemDto("Door", "Opens the room", 20.90, 5);

        assertThrows(PasswordIsIncorrectException.class, () -> itemController.createItem(createItemDto, "admin@eurder.com", "wrongpassword"));
    }


}
