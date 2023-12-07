package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.EurderLine;
import com.switchfully.eurder.dto.CreateEurderDto;
import com.switchfully.eurder.dto.EurderDto;
import com.switchfully.eurder.mapper.EurderMapper;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.EurderRepository;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EurderServiceTest {
    private ItemService itemService;
    private UserService userService;
    private ItemMapper itemMapper;
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private EurderService eurderService;
    private EurderRepository eurderRepository;
    private EurderMapper eurderMapper;

    @BeforeEach
    void setUpEurderService() {
        itemMapper = new ItemMapper();
        itemRepository = new ItemRepository();
        userMapper = new UserMapper();
        userRepository = new UserRepository();
        userService = new UserService(userMapper, userRepository);
        itemService = new ItemService(itemMapper, itemRepository, userService);
        eurderMapper = new EurderMapper();
        eurderRepository = new EurderRepository(itemRepository, userRepository);
        eurderService = new EurderService(eurderMapper, eurderRepository, userRepository, itemRepository);

    }

    @Test
    void whenCreateEurder_thenReturnEurderDtoWithSameFields() {
        List<EurderLine> eurderLineList = List.of(
                new EurderLine(itemRepository.getItemByName("Chair"), 2),
                new EurderLine(itemRepository.getItemByName("Closet"), 1)
        );

        CreateEurderDto createEurderDto = new CreateEurderDto(eurderLineList, userRepository.getUserByEmail("maria@hotmail.com"));



        //WHEN & THEN
        EurderDto eurderDto = eurderService.createEurder(createEurderDto, "maria@hotmail.com");

        assertThat(eurderDto.getUser()).isEqualTo(userRepository.getUserByEmail("maria@hotmail.com"));
        assertThat(eurderDto.getTotalPrice()).isEqualTo(eurderMapper.mapCreateEurderDtoToEurder(createEurderDto).getTotalPrice());

    }
}
