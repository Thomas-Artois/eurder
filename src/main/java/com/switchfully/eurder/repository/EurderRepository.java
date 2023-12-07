package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Eurder;
import com.switchfully.eurder.dto.EurderDto;
import com.switchfully.eurder.exception.OrderDoesntExistException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EurderRepository {
    private Map<String, Eurder> eurders = new HashMap<>();

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
