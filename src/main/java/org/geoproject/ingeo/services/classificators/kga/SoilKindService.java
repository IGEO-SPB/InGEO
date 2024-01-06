package org.geoproject.ingeo.services.classificators.kga;

import org.geoproject.ingeo.dto.classificators.kga.SoilKindDto;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;

import java.util.List;

public interface SoilKindService {

    List<SoilKind> getBySoilClass(SoilClass soilClass);

    List<SoilKindDto> getDtos(Long currentSoilKindGroupId);

    SoilKind getById(Long id);
}
