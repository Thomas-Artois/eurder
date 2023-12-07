package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.Eurder;
import com.switchfully.eurder.dto.CreateEurderDto;
import com.switchfully.eurder.dto.EurderDto;
import org.springframework.stereotype.Component;

@Component
public class EurderMapper {
    public Eurder mapCreateEurderDtoToEurder(CreateEurderDto createEurderDto) {
        return new Eurder(createEurderDto.getEurderList(), createEurderDto.getUser());
    }

    public EurderDto mapEurderToEurderDto(Eurder eurder) {
        return new EurderDto(eurder.getId(), eurder.getEurderList(), eurder.getUser());
    }
}
