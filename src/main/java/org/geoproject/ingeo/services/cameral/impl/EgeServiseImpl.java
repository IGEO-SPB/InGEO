package org.geoproject.ingeo.services.cameral.impl;

import org.apache.commons.lang.ObjectUtils;
import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.EgeMapper;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.geoproject.ingeo.repositories.EgeRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindAdjRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindRepository;
import org.geoproject.ingeo.services.cameral.EgeServise;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.geoproject.ingeo.utils.CurrentState;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EgeServiseImpl implements EgeServise {

    private final EgeRepository egeRepository;
    private final EgeMapper egeMapper;
    private final SoilSubkindRepository soilSubkindRepository;
    private final SoilSubkindAdjRepository soilSubkindAdjRepository;

    private final CurrentState currentState;

    @Override
    public List<Ege> getAll() {
        return egeRepository.findAll();
    }

    @Override
    public Ege getById(Long id) {
        return egeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ИГЭ не найден"));
    }

    @Override
    @Transactional
    public void create(List<Ege> egeList) {
        egeRepository.saveAll(egeList);
    }

    @Override
    @Transactional
    public void update(Ege ege) {
        egeRepository.save(ege);
    }

    @Override
    @Transactional
    public void delete(List<Ege> egeList) {
        egeRepository.deleteAll(egeList);
    }

    @Override
    public List<Ege> getByProject(Project project) {
        return egeRepository.findByProject(project);
    }

    @Override
    public List<Ege> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        throw new NotImplemented("getBySurveyPoint method in EgesServise not implemented");
    }

    @Override
    public Ege getByNumberAndProject(String number, Project project) {
        return egeRepository.findByNumberAndProject(number, project);
    }

    @Override
    public void delete(EgeDto egeDto) {

    }

    @Override
    public void deleteByDto(EgeDto dto) {
        if (Objects.nonNull(dto.getId())) {
            egeRepository.deleteById(dto.getId());
        }
    }

    @Override
    public List<EgeDto> getDtos(List<Ege> objects) {
        throw new NotImplementedException("getDtos метод не реализован");
    }

    @Override
    public void updateFromDtos(List<Ege> objects, List<EgeDto> dtos) {
        throw new NotImplemented("updateFromDtos not implemented");
    }

    @Override
    public void updateFromDtos(List<EgeDto> dtos) {
        var dtosToUpdate = dtos.stream()
                        .filter(egeDto -> Objects.nonNull(egeDto.getId()))
                                .collect(Collectors.toList());

        if (!dtosToUpdate.isEmpty()) {
            List<Ege> savedEges = getByProject(currentState.getCurrentProject());

            egeMapper.updateEgeFromEgeDto(savedEges, dtosToUpdate);

            egeRepository.saveAll(savedEges);
        }

        var dtosToSave = dtos.stream()
                .filter(egeDto -> Objects.isNull(egeDto.getId()))
                .collect(Collectors.toList());

        if (!dtosToSave.isEmpty()) {
            var newEges = egeMapper.egeDtoToEge(dtosToSave);
            newEges.forEach(ege -> ege.setProject(currentState.getCurrentProject()));
            egeRepository.saveAll(newEges);
        }
    }

    @Override
    public EgeDto getDto(Ege ege) {

        var egeDto = egeMapper.egeToEgeDto(ege);

        return egeDto;
    }

    @Override
    public DescriptionKgaDto getDescriptionKgaDto(Long egeId) {
        var ege = getById(egeId);

        var descriptionKgaDto = egeMapper.egeToDescriptionKgaDto(ege);


        var soilSubkindMap = descriptionKgaDto.getSoilSubkindMap();
        var soilSubkindAdjMap = descriptionKgaDto.getSoilSubkindAdjMap();

        Class<? extends Ege> clazz = ege.getClass();

        fillSoilSubkindMap(soilSubkindMap, clazz, ege);
        fillSoilSubkindAdjMap(soilSubkindAdjMap, clazz, ege);

        return descriptionKgaDto;
    }

    private void fillSoilSubkindMap(Map<String, SoilSubkind> soilSubkindMap, Class<?> clazz, Ege ege) {
        soilSubkindMap.forEach((key, value) -> {

            try {
                var field = clazz.getDeclaredField(key);
                field.setAccessible(true);

                Long fieldValue = (Long) field.get(ege);
//                long fieldValue = field.getLong(clazz);

                var newValue = getSoilSubkind(fieldValue);

                soilSubkindMap.put(key, newValue);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void fillSoilSubkindAdjMap(Map<String, SoilSubkindAdj> soilSubkindAdjMap, Class<?> clazz,  Ege ege) {
        soilSubkindAdjMap.forEach((key, value) -> {

            try {
                var field = clazz.getDeclaredField(key);
                field.setAccessible(true);

                var fieldValue = (Long) field.get(ege);
                var newValue = getSoilSubkindAdj(fieldValue);

                soilSubkindAdjMap.put(key, newValue);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private SoilSubkind getSoilSubkind(Long id) {

        if (Objects.isNull(id)) {
            return null;
        }
//                    .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage(SOIL_SUBKIND_ENTITY_NAME)));
        return soilSubkindRepository.findById(id).orElse(null);
    }

    private SoilSubkindAdj getSoilSubkindAdj(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return soilSubkindAdjRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateEge(DescriptionKgaDto descriptionKgaDto) {
        var ege = getById(descriptionKgaDto.getEgeId());

        egeMapper.updateEge(ege, descriptionKgaDto);

        egeRepository.save(ege);
    }

    @Override
    public List<EgeDto> getDtosByProject(Project project) {
        var currentProjectEges = getByProject(project);

        return egeMapper.egeToEgeDto(currentProjectEges);
    }
}
