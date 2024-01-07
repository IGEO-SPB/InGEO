package org.geoproject.ingeo.services.classificators.kga;

import org.geoproject.ingeo.dto.classificators.kga.SoilClassKindGroupDto;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;

import java.util.List;

public interface SoilClassKindGroupService {

    List<SoilClassKindGroupDto> getBySoilClassId(Long soilClassId);
}
