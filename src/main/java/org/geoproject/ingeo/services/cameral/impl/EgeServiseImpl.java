package org.geoproject.ingeo.services.cameral.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindAdjDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.exceptions.ConflictException;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.EgeMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilSubkindAdjMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilSubkindMapper;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.EgeRepository;
import org.geoproject.ingeo.repositories.classificators.kga.ColorRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilClassKindGroupRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilClassRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilKindRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindAdjRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindRepository;
import org.geoproject.ingeo.services.cameral.EgeServise;
import org.geoproject.ingeo.utils.CurrentState;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EgeServiseImpl implements EgeServise {

    private final EgeRepository egeRepository;
    private final SoilSubkindRepository soilSubkindRepository;
    private final SoilSubkindAdjRepository soilSubkindAdjRepository;
    private final SoilClassRepository soilClassRepository;
    private final SoilClassKindGroupRepository soilClassKindGroupRepository;
    private final SoilKindRepository soilKindRepository;
    private final ColorRepository colorRepository;

    private final EgeMapper egeMapper;
    private final SoilSubkindAdjMapper soilSubkindAdjMapper;
    private final SoilSubkindMapper soilSubkindMapper;

    private final CurrentState currentState;

    @Override
    public List<Ege> getAll() {
        return egeRepository.findAll();
    }

    @Override
    public Ege getById(Long id) {

        if (Objects.isNull(id)) {
            throw new ConflictException(ExceptionTypeEnum.EGE_NOT_SAVED_EXCEPTION.getMessage());
        }

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
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getBySurveyPoint"));
    }

    @Override
    public Ege getByNumberAndProject(String number, Project project) {
        return egeRepository.findByEgeNumberAndProject(number, project);
    }

    @Override
    public void delete(EgeDto egeDto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("delete"));
    }

    @Override
    public void deleteByDto(EgeDto dto) {
        if (Objects.nonNull(dto.getId())) {
            var deletedEge = egeRepository.findById(dto.getId());
            deletedEge.ifPresent(ege -> {
                ege.setIsArchive(Boolean.TRUE);
                egeRepository.save(ege);
            });
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
            var savedEges = getByProject(currentState.getCurrentProject());


            egeMapper.updateEgeFromEgeDto(savedEges, dtosToUpdate);

            savedEges.forEach(this::setHatchingParameters);

            egeRepository.saveAll(savedEges);
        }

        var dtosToSave = dtos.stream()
                .filter(egeDto -> Objects.isNull(egeDto.getId()))
                .collect(Collectors.toList());

        if (!dtosToSave.isEmpty()) {
            var newEges = egeMapper.egeDtoToEge(dtosToSave);
            newEges.forEach(ege -> ege.setProject(currentState.getCurrentProject()));

            newEges.forEach(this::setHatchingParameters);

            egeRepository.saveAll(newEges);
        }
    }

    @Override
    public EgeDto getDto(Ege ege) {

        return egeMapper.egeToEgeDto(ege);
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

    private void fillSoilSubkindMap(Map<String, SoilSubkindDto> soilSubkindMap, Class<?> clazz, Ege ege) {
        soilSubkindMap.forEach((key, value) -> {

            try {
                var field = clazz.getDeclaredField(key);
                field.setAccessible(true);

                Long fieldValue = (Long) field.get(ege);

                var newValue = getSoilSubkindDto(fieldValue);

                soilSubkindMap.put(key, newValue);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void fillSoilSubkindAdjMap(Map<String, SoilSubkindAdjDto> soilSubkindAdjMap, Class<?> clazz, Ege ege) {
        soilSubkindAdjMap.forEach((key, value) -> {

            try {
                var field = clazz.getDeclaredField(key);
                field.setAccessible(true);

                var fieldValue = (Long) field.get(ege);
                var newValue = getSoilSubkindAdjDto(fieldValue);

                soilSubkindAdjMap.put(key, newValue);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private SoilSubkindDto getSoilSubkindDto(Long id) {

        if (Objects.isNull(id)) {
            return null;
        }
        var soilSubkind = soilSubkindRepository.findById(id).orElse(null);

        if (Objects.isNull(soilSubkind)) {
            return null;
        }

        return soilSubkindMapper.soilSubkindToSoilSubkindDto(soilSubkind);
    }

    private SoilSubkindAdjDto getSoilSubkindAdjDto(Long id) {

        if (Objects.isNull(id)) {
            return null;
        }

        var soilSubkindAdj = soilSubkindAdjRepository.findById(id).orElse(null);

        if (Objects.isNull(soilSubkindAdj)) {
            return null;
        }

        return soilSubkindAdjMapper.soilSubkindAdjToSoilSubkindAdjDto(soilSubkindAdj);
    }

    private void setHatchingParameters(Ege ege) {

        if (Objects.nonNull(ege.getHatching())) {
            ege.setCredoColor(ege.getHatching().getCredoColor());
            ege.setHatchingCredo(ege.getHatching().getHatchingCredo());
        }
    }

    @Override
    @Transactional
    public void updateEge(DescriptionKgaDto descriptionKgaDto) {
        var ege = getById(descriptionKgaDto.getEgeId());

        egeMapper.updateEge(ege, descriptionKgaDto);
        ege.setShortName(descriptionKgaDto.getShortName());

        var soilClass = soilClassRepository.findById(descriptionKgaDto.getSoilClassDto().getId());
        soilClass.ifPresent(ege::setSoilClass);

        var soilClassKindGroup = soilClassKindGroupRepository.findById(descriptionKgaDto.getSoilClassKindGroupDto().getId());
        soilClassKindGroup.ifPresent(ege::setSoilClassKindGroup);

        var soilKind = soilKindRepository.findById(descriptionKgaDto.getSoilKindDto().getId());
        soilKind.ifPresent(ege::setSoilKind);

        var color = colorRepository.findById(descriptionKgaDto.getColorDto().getId());
        color.ifPresent(ege::setColor);

        egeRepository.save(ege);
    }

    @Override
    public List<EgeDto> getDtosByProject(Project project) {
        var currentProjectEges = getByProject(project);

        return egeMapper.egeToEgeDto(currentProjectEges);
    }

    @Override
    public List<EgeDto> getDtosBySurveyPointId(Long surveyPointId) {
        return null;
    }

    @Override
    public EgeDto cloneDto(EgeDto egeDto) {
        var cloneDto = egeMapper.cloneEgeDto(egeDto);

        return cloneDto;
    }

    @Override
    public void enrichEntity(Long updatedEntityId, Long sourceEntityId) {

    }
}
