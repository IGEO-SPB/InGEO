package org.geoproject.ingeo.services.classificators.impl;

import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.models.classificators.PeatDecayDegree;
import org.geoproject.ingeo.repositories.classificators.PeatDecayDegreeRepository;
import org.geoproject.ingeo.services.classificators.PeatDecayDegreeService;
import org.springframework.stereotype.Service;

@Service
public class PeatDecayDegreeServiceImpl implements PeatDecayDegreeService {
    private final PeatDecayDegreeRepository peatDecayDegreeRepository;

    public PeatDecayDegreeServiceImpl(PeatDecayDegreeRepository peatDecayDegreeRepository) {
        this.peatDecayDegreeRepository = peatDecayDegreeRepository;
    }

    @Override
    public PeatDecayDegree findByP250(int p250) {
        return peatDecayDegreeRepository.findByP250(p250)
                .orElseThrow(() -> new NotFoundException("PeatDecayDegree entity not found"));
    }
}
