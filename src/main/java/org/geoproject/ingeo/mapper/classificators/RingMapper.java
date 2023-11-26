package org.geoproject.ingeo.mapper.classificators;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.RingDto;
import org.geoproject.ingeo.models.classificators.Ring;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface RingMapper {
    RingDto ringToRingDto(Ring ring);

    List<RingDto> ringToRingDto(List<Ring> rings);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", defaultValue = "true")
    @Mapping(target = "d1", expression = "java(0)")
    @Mapping(target = "d2", expression = "java(0)")
    Ring ringDtoToRing(RingDto ringDto);

    List<Ring> ringDtoToRing(List<RingDto> dtos);

    @Mapping(target = "id", ignore = true)
    void updateRing(@MappingTarget Ring updatedRing, RingDto sourceDto);

    default void updateRing(List<Ring> updatedRings, List<RingDto> sourceDtos) {
        for (var ring : updatedRings) {
            for (var sourceDto : sourceDtos) {
                if (Objects.equals(ring.getNumber(), sourceDto.getNumber())) {
                    updateRing(ring, sourceDto);
                }
            }
        }
    }
}