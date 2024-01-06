package org.geoproject.ingeo.services.classificators.kga;

import org.geoproject.ingeo.dto.classificators.kga.SoilKindGroupTypeDto;

import java.util.List;

public interface SoilKindGroupTypeService {

    List<SoilKindGroupTypeDto> getBySoilKindId(Long soilKindId);
}
