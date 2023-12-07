package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Eurder;
import com.switchfully.eurder.dto.CreateEurderDto;
import com.switchfully.eurder.dto.EurderDto;
import com.switchfully.eurder.exception.UserNotOrderingForThemselfException;
import com.switchfully.eurder.mapper.EurderMapper;
import com.switchfully.eurder.repository.EurderRepository;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EurderService {
    private EurderMapper eurderMapper;
    private EurderRepository eurderRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    public EurderService(EurderMapper eurderMapper, EurderRepository eurderRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.eurderMapper = eurderMapper;
        this.eurderRepository = eurderRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public EurderDto createEurder(CreateEurderDto createEurderDto, String email) throws UserNotOrderingForThemselfException {
        if (!userRepository.getUserByEmail(email).getId().equals(createEurderDto.getUser().getId())) {
            throw new UserNotOrderingForThemselfException();
        }

        Eurder eurder = eurderRepository.createEurder(eurderMapper.mapCreateEurderDtoToEurder(createEurderDto));
        return eurderMapper.mapEurderToEurderDto(eurder);
    }

    public List<EurderDto> getAllEurders() {
        return eurderRepository.getAllEurders().stream().map(eurder -> eurderMapper.mapEurderToEurderDto(eurder)).collect(Collectors.toList());
    }

//    public LocalDate calculateShippingDate(EurderDto eurderDto) {
//
//        if (itemRepository.getItemById(eurderDto.getItemId()).getAmountInStock() <= 0) {
//            return LocalDate.now().plusDays(7);
//        } else {
//            return LocalDate.now().plusDays(1);
//        }
//    }

}
