package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.kga.ColorDto;
import org.geoproject.ingeo.mapper.classificators.kga.ColorMapper;
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
    private final ColorMapper colorMapper;

    @Override
    public List<ColorDto> getAll(String sortField) {
        var sort = Sort.by(Sort.Direction.DESC, sortField);

        var colors = colorRepository.findAll(sort);

        return colorMapper.colorToColorDto(colors);
    }

    @Override
    public List<ColorDto> getColorDtos() {
        var sort = Sort.by(Sort.Direction.DESC, "cltName");

        var colors = colorRepository.findAll(sort);

        return colorMapper.colorToColorDto(colors);
    }
}
