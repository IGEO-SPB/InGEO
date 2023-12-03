package org.geoproject.ingeo.services.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;

import java.util.List;

public interface SoilKindGroupTypeService {

    List<SoilKindGroupType> getBySoilKind(SoilKind soilKind);
}