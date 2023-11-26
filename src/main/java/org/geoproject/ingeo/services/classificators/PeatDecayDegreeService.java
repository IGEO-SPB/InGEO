package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.models.classificators.PeatDecayDegree;

public interface PeatDecayDegreeService {
    PeatDecayDegree findByP250(int p250);

}
