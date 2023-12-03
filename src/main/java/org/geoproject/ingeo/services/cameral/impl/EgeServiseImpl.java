package org.geoproject.ingeo.services.cameral.impl;

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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class EgeServiseImpl implements EgeServise {

    private final EgeRepository egeRepository;
    private final EgeMapper egeMapper;
    private final SoilSubkindRepository soilSubkindRepository;
    private final SoilSubkindAdjRepository soilSubkindAdjRepository;

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
    public Ege getBySample(Sample sample) {
        return null;
    }

    @Override
    @Transactional
    public void create(EgeDto dto) {
        throw new NotImplementedException("create метод не реализован");

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
    public void delete(EgeDto object) {

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
    public EgeDto getDto(Ege ege) {

        var egeDto = egeMapper.egeToEgeDto(ege);

        return egeDto;
    }

    @Override
    public DescriptionKgaDto getDescriptionKgaDto(Ege ege) {

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
    public void updateEge(Ege ege, DescriptionKgaDto descriptionKgaDto) {

        System.out.println("+++++++");
        System.out.println(descriptionKgaDto.getSoilClass().getId());
        System.out.println(descriptionKgaDto.getSoilClassKindGroup().getId());

//        var ege = getById(descriptionKgaDto.getEgeId());

        egeMapper.updateEge(ege, descriptionKgaDto);

        egeRepository.save(ege);
    }
}
