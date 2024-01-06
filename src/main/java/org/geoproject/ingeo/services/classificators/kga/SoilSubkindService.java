package org.geoproject.ingeo.services.classificators.kga;

import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindDto;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;

import java.util.List;

public interface SoilSubkindService {

    List<SoilSubkindDto> getBySoilKindGroupTypeId(Long soilKindGroupTypeId);
}
