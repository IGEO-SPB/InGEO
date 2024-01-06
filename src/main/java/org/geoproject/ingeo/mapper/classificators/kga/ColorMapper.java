package org.geoproject.ingeo.mapper.classificators.kga;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.kga.ColorDto;
import org.geoproject.ingeo.models.classificators.kga.Color;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfiguration.class)
public interface ColorMapper {

    ColorDto colorToColorDto(Color color);

    List<ColorDto> colorToColorDto(List<Color> colorList);
}
