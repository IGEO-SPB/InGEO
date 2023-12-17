package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.models.classificators.Hatching;

import java.util.List;

public interface HatchingService {

    List<Hatching> getAll();

    List<HatchingDto> getHatchingDtos();
}
