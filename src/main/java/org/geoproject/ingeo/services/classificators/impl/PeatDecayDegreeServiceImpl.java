package com.geoproject.igeo.services.classificators.impl;

import com.geoproject.igeo.exceptions.NotFoundException;
import com.geoproject.igeo.models.classificators.PeatDecayDegree;
import com.geoproject.igeo.repositories.classificators.PeatDecayDegreeRepository;
import com.geoproject.igeo.services.classificators.PeatDecayDegreeService;
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
