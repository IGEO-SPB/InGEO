package org.geoproject.ingeo.services.classificators.kga;

import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;

import java.util.List;

public interface SoilSubkindAdjService {

    List<SoilSubkindAdj> getAll();
}
