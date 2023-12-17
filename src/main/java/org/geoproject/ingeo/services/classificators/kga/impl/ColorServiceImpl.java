package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.models.classificators.kga.Color;
import org.geoproject.ingeo.repositories.classificators.kga.ColorRepository;
import org.geoproject.ingeo.services.classificators.kga.ColorService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public List<Color> getAll(String sortField) {
        var sort = Sort.by(Sort.Direction.DESC, sortField);

        return colorRepository.findAll(sort);
    }
}
