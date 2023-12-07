package com.switchfully.eurder.controller;

import com.switchfully.eurder.dto.CreateEurderDto;
import com.switchfully.eurder.dto.EurderDto;
import com.switchfully.eurder.repository.UserRepository;
import com.switchfully.eurder.service.EurderService;
import com.switchfully.eurder.service.ItemService;
import com.switchfully.eurder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/orders")
public class EurderController {
    private EurderService eurderService;
    private UserService userService;
    private UserRepository userRepository;
    private ItemService itemService;

    public EurderController(EurderService eurderService, UserService userService, UserRepository userRepository, ItemService itemService) {
        this.eurderService = eurderService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.itemService = itemService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EurderDto createEurder(@Valid @RequestBody CreateEurderDto createEurderDto, @RequestParam String email, @RequestParam String password) {
        userService.checkIfPasswordIsCorrect(userRepository.getUserByEmail(email), password);
        return eurderService.createEurder(createEurderDto, email);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<EurderDto> viewAllEurders(@RequestParam String email, @RequestParam String password) {
        userService.checkIfUserIsAdmin(email, password);
        return eurderService.getAllEurders();
    }
}
