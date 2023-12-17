package org.geoproject.ingeo.services.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;

import java.util.List;

public interface SoilClassKindGroupService {

    List<SoilClassKindGroup> getBySoilClass(SoilClass soilClass);
}
