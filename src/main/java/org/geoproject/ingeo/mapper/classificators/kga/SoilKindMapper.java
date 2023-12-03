package org.geoproject.ingeo.mapper.classificators.kga;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.PotDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilKindDto;
import org.geoproject.ingeo.models.classificators.Pot;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface SoilKindMapper {
//    void updatePotFromPot(@MappingTarget Pot updatedPot, Pot pot);
//
    @Mapping(target = "soilClassName", source = "soilClass.scName")
    SoilKindDto soilKindToSoilKindDto(SoilKind soilKind);

    List<SoilKindDto> soilKindToSoilKindDto(List<SoilKind> soilKind);
//
//    List<PotDto> potToPotDto(List<Pot> pots);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "isActive", defaultValue = "true")
//    Pot potDtoToPot(PotDto pot);
//
//    List<Pot> potDtoToPot(List<PotDto> dtos);
//
//    @Mapping(target = "id", ignore = true)
//    void updatePot(@MappingTarget Pot updatedPot, PotDto sourceDto);
//
//    default void updatePot(List<Pot> updatedPots, List<PotDto> sourceDtos) {
//        for (var pot : updatedPots) {
//            for (var sourceDto : sourceDtos) {
//                if (Objects.equals(pot.getNumber(), sourceDto.getNumber())) {
//                    updatePot(pot, sourceDto);
//                }
//            }
//        }
//    }
}