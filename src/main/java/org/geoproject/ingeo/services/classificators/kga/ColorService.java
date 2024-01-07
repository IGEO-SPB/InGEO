package org.geoproject.ingeo.services.classificators.kga;

import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.dto.classificators.kga.ColorDto;
import org.geoproject.ingeo.models.classificators.kga.Color;

import java.util.List;

public interface ColorService {

    List<ColorDto> getAll(String sortField);

    List<ColorDto> getColorDtos();
}
