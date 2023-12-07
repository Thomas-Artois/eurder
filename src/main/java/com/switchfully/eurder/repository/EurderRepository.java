package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Eurder;
import com.switchfully.eurder.domain.EurderLine;
import com.switchfully.eurder.dto.EurderDto;
import com.switchfully.eurder.exception.OrderDoesntExistException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EurderRepository {
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private Map<String, Eurder> eurders = new HashMap<>();

    public EurderRepository(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        List<EurderLine> listOfEurderLines = List.of(
                new EurderLine(itemRepository.getItemByName("Closet"), 2),
                new EurderLine(itemRepository.getItemByName("Table"), 1)
        );
        List<Eurder> listOfEurders = List.of(
                new Eurder(listOfEurderLines, userRepository.getUserByEmail("jozef@hotmail.com"))
        );
    }

    public Eurder createEurder(Eurder eurder) {
        eurders.put(eurder.getId(), eurder);
        return eurder;
    }

    public List<Eurder> getAllEurders() {
        return eurders.values().stream().toList();
    }

    public Eurder getEurderById(String id) throws OrderDoesntExistException {
        return eurders.values().stream().filter(eurder -> eurder.getId().equals(id)).findFirst().orElseThrow(OrderDoesntExistException::new);
    }
}
