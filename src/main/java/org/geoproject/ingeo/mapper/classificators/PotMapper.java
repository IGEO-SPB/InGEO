package com.geoproject.igeo.mapper.classificators;

import com.geoproject.igeo.config.MapStructConfiguration;
import com.geoproject.igeo.dto.classificators.PotDto;
import com.geoproject.igeo.models.classificators.Pot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface PotMapper {
    void updatePotFromPot(@MappingTarget Pot updatedPot, Pot pot);

    PotDto potToPotDto(Pot pot);

    List<PotDto> potToPotDto(List<Pot> pots);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", defaultValue = "true")
    Pot potDtoToPot(PotDto pot);

    List<Pot> potDtoToPot(List<PotDto> dtos);

    @Mapping(target = "id", ignore = true)
    void updatePot(@MappingTarget Pot updatedPot, PotDto sourceDto);

    default void updatePot(List<Pot> updatedPots, List<PotDto> sourceDtos) {
        for (var pot : updatedPots) {
            for (var sourceDto : sourceDtos) {
                if (Objects.equals(pot.getNumber(), sourceDto.getNumber())) {
                    updatePot(pot, sourceDto);
                }
            }
        }
    }
}