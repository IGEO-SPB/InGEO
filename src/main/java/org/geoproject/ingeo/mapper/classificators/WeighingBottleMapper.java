package org.geoproject.ingeo.mapper.classificators;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.WeighingBottleDto;
import org.geoproject.ingeo.models.classificators.WeighingBottle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface WeighingBottleMapper {

    WeighingBottleDto weighingBottleToWeighingBottleDto(WeighingBottle weighingBottle);

    List<WeighingBottleDto> weighingBottleToWeighingBottleDto(List<WeighingBottle> weighingBottles);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", defaultValue = "true")
    WeighingBottle weighingBottleDtoToWeighingBottle(WeighingBottleDto weighingBottleDto);

    List<WeighingBottle> weighingBottleDtoToWeighingBottle(List<WeighingBottleDto> weighingBottleDtos);

    @Mapping(target = "id", ignore = true)
    void updateWeighingBottle(@MappingTarget WeighingBottle updatedWeighingBottle, WeighingBottleDto sourceDto);

    default void updateWeighingBottle(List<WeighingBottle> updatedWeighingBottles, List<WeighingBottleDto> sourceDtos) {
        for (var weighingBottle : updatedWeighingBottles) {
            for (var sourceDto : sourceDtos) {
                if (Objects.equals(weighingBottle.getNumber(), sourceDto.getNumber())) {
                    updateWeighingBottle(weighingBottle, sourceDto);
                }
            }
        }
    }
}
