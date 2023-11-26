package com.geoproject.igeo.services.classificators.impl;

import com.geoproject.igeo.exceptions.NotFoundException;
import com.geoproject.igeo.models.classificators.ConstructionType;
import com.geoproject.igeo.repositories.classificators.ConstructionTypeRepository;
import com.geoproject.igeo.services.classificators.ConstructionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstructionTypeServiceImpl implements ConstructionTypeService {

    private final ConstructionTypeRepository constructionTypeRepository;

    @Override
    public ConstructionType getByType(String type) {
        return constructionTypeRepository.findByType(type).
                orElseThrow(() -> new NotFoundException("Экземпляр ConstructionType не найден"));
    }

    @Override
    public void create(List<ConstructionType> types) {
        constructionTypeRepository.saveAll(types);
    }

    @Override
    public List<ConstructionType> getAll() {
        List<ConstructionType> constructionTypes = constructionTypeRepository.findAll();

        if (constructionTypes.isEmpty()) {
            throw new NotFoundException("Типы строительства не найдены. Проверьте данные в базе");
        }

        return constructionTypes;
    }
}
