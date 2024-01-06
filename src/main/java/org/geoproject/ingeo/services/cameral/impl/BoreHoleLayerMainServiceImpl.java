package org.geoproject.ingeo.services.cameral.impl;

import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindAdjDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindDto;
import org.geoproject.ingeo.dto.mainViewsDtos.BoreholeLayerDto;
import org.geoproject.ingeo.exceptions.ConflictException;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.BoreholeLayerMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilSubkindAdjMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilSubkindMapper;
import org.geoproject.ingeo.models.BoreholeLayer;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.geoproject.ingeo.repositories.BoreHoleLayerMainRepository;
import org.geoproject.ingeo.repositories.classificators.kga.ColorRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindAdjRepository;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindRepository;
import org.geoproject.ingeo.services.cameral.BoreHoleLayerMainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
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
public class BoreHoleLayerMainServiceImpl implements BoreHoleLayerMainService {

    private final CurrentState currentState;
    private final BoreHoleLayerMainRepository boreHoleLayerMainRepository;
    private final SoilSubkindRepository soilSubkindRepository;
    private final SoilSubkindAdjRepository soilSubkindAdjRepository;
    private final ColorRepository colorRepository;

    private final BoreholeLayerMapper boreholeLayerMapper;
    private final SoilSubkindMapper soilSubkindMapper;
    private final SoilSubkindAdjMapper soilSubkindAdjMapper;

    private final EgeServise egeServise;

    @Override
    public List<BoreholeLayer> getAll() {
        return boreHoleLayerMainRepository.findAll();
    }

    @Override
    public BoreholeLayer getById(Long id) {
        if (Objects.isNull(id)) {
            throw new ConflictException(ExceptionTypeEnum.EGE_NOT_SAVED_EXCEPTION.getMessage());
        }

        return boreHoleLayerMainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Слой не найден"));
    }

//    @Override
//    @Transactional
//    public void create(BoreholeLayerDTO dto) {
//        BoreholeLayer boreholeLayer = boreholeLayerMapper.boreholeLayerDtoToBoreholeLayer(dto);
//
//        boreHoleLayerMainRepository.save(boreholeLayer);
//    }

    @Override
    @Transactional
    public void create(List<BoreholeLayer> boreholeLayerList) {
        boreHoleLayerMainRepository.saveAll(boreholeLayerList);
    }

    @Override
    @Transactional
    public void update(BoreholeLayer boreholeLayer) {
        boreHoleLayerMainRepository.save(boreholeLayer);
    }

    @Override
    @Transactional
    public void delete(List<BoreholeLayer> boreholeLayerList) {
        boreHoleLayerMainRepository.deleteAll(boreholeLayerList);
    }

    @Override
    public List<BoreholeLayer> getByProject(Project project) {
        return boreHoleLayerMainRepository.findBySurveyPointProjectAndIsArchiveFalse(project);
    }

    @Override
    public List<BoreholeLayer> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        return boreHoleLayerMainRepository.findBySurveyPointIdAndIsArchiveFalse(surveyPoint.getId());
    }

    @Override
    public void delete(BoreholeLayerDto object) {
        throw new NotImplementedException("delete метод не реализован");
    }

    @Override
    public void deleteByDto(BoreholeLayerDto dto) {
        if (Objects.nonNull(dto.getId())) {
            var deletedBoreholeLayer = boreHoleLayerMainRepository.findById(dto.getId());
            deletedBoreholeLayer.ifPresent(boreholeLayer -> {
                boreholeLayer.setIsArchive(Boolean.TRUE);
                boreHoleLayerMainRepository.save(boreholeLayer);
            });
        }
    }

    @Override
    public List<BoreholeLayerDto> getDtos(List<BoreholeLayer> objects) {
        throw new NotImplementedException("getDtos метод не реализован");
    }

    @Override
    public void updateFromDtos(List<BoreholeLayer> objects, List<BoreholeLayerDto> dtos) {
        throw new NotImplemented("updateFromDtos not implemented");
    }

    @Override
    public void updateFromDtos(List<BoreholeLayerDto> dtos) {
        var dtosToUpdate = dtos.stream()
                .filter(boreholeLayerDto -> Objects.nonNull(boreholeLayerDto.getId()))
                .collect(Collectors.toList());

        if (!dtosToUpdate.isEmpty()) {
            var savedBoreholeLayers = getByProject(currentState.getCurrentProject());


            boreholeLayerMapper.updateBoreholeLayerFromBoreholeLayerDto(savedBoreholeLayers, dtosToUpdate);

            savedBoreholeLayers.forEach(this::setHatchingParameters);

//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//
//            savedBoreholeLayers.forEach(boreholeLayer -> {
//                System.out.println(boreholeLayer.getId());
//                System.out.println(boreholeLayer.getEge().getEgeNumber());
//                System.out.println(boreholeLayer.getColor().getId());
//                System.out.println(boreholeLayer.getColor().getCltName());
//                System.out.println(boreholeLayer.getColor().getCreator());
//            });

            boreHoleLayerMainRepository.saveAll(savedBoreholeLayers);
        }

        var dtosToSave = dtos.stream()
                .filter(boreholeLayerDto -> Objects.isNull(boreholeLayerDto.getId()))
                .collect(Collectors.toList());

        if (!dtosToSave.isEmpty()) {
            var newBoreholeLayers = boreholeLayerMapper.boreholeLayerDtoToBoreholeLayer(dtosToSave);
            newBoreholeLayers.forEach(boreholeLayer -> boreholeLayer.setSurveyPoint(currentState.getSurveyPoint()));

            newBoreholeLayers.forEach(this::setHatchingParameters);

            boreHoleLayerMainRepository.saveAll(newBoreholeLayers);

            newBoreholeLayers.forEach(boreholeLayer -> {
                enrichEntity(boreholeLayer.getId(), boreholeLayer.getEge().getId());
            });
        }
    }

    private void setHatchingParameters(BoreholeLayer boreholeLayer) {

        if (Objects.nonNull(boreholeLayer.getHatching())) {
            boreholeLayer.setCredoColor(boreholeLayer.getHatching().getCredoColor());
            boreholeLayer.setHatchingCredo(boreholeLayer.getHatching().getHatchingCredo());
        }
    }

    @Override
    public List<BoreholeLayerDto> getDtosByProject(Project project) {
        var boreholeLayers = getByProject(project);

        return boreholeLayerMapper.boreholeLayerToBoreholeLayerDto(boreholeLayers);
    }

    @Override
    public List<BoreholeLayerDto> getDtosBySurveyPointId(Long surveyPointId) {
        var boreholeLayers = boreHoleLayerMainRepository.findBySurveyPointIdAndIsArchiveFalse(surveyPointId);

        return boreholeLayerMapper.boreholeLayerToBoreholeLayerDto(boreholeLayers);
    }

    @Override
    public BoreholeLayerDto cloneDto(BoreholeLayerDto egeDto) {
        var cloneDto = boreholeLayerMapper.cloneEgeDto(egeDto);

        return cloneDto;
    }

    @Override
    @Transactional
    public void enrichEntity(Long updatedEntityId, Long sourceEntityId) {
        var boreholeLayer = boreHoleLayerMainRepository.findById(updatedEntityId)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("BoreholeLayer")));

        var ege = egeServise.getById(sourceEntityId);

        boreholeLayer.setSoilClass(ege.getSoilClass());
        boreholeLayer.setSoilKind(ege.getSoilKind());
        boreholeLayer.setSoilClassKindGroup(ege.getSoilClassKindGroup());

        boreholeLayer.setSSA1(ege.getSSA1());
        boreholeLayer.setSSA2(ege.getSSA2());
        boreholeLayer.setSSA3(ege.getSSA3());
        boreholeLayer.setSSA4(ege.getSSA4());
        boreholeLayer.setSSA5(ege.getSSA5());
        boreholeLayer.setSSA6(ege.getSSA6());
        boreholeLayer.setSSA7(ege.getSSA7());
        boreholeLayer.setSSA8(ege.getSSA8());
        boreholeLayer.setSSA9(ege.getSSA9());
        boreholeLayer.setSSA10(ege.getSSA10());
        boreholeLayer.setSSA11(ege.getSSA11());
        boreholeLayer.setSSA12(ege.getSSA12());


        boreholeLayer.setSS1(ege.getSS1());
        boreholeLayer.setSS2(ege.getSS2());
        boreholeLayer.setSS3(ege.getSS3());
        boreholeLayer.setSS4(ege.getSS4());
        boreholeLayer.setSS5(ege.getSS5());
        boreholeLayer.setSS6(ege.getSS6());
        boreholeLayer.setSS7(ege.getSS7());
        boreholeLayer.setSS8(ege.getSS8());
        boreholeLayer.setSS9(ege.getSS9());
        boreholeLayer.setSS10(ege.getSS10());
        boreholeLayer.setSS11(ege.getSS11());
        boreholeLayer.setSS12(ege.getSS12());
        boreholeLayer.setSS13(ege.getSS13());
        boreholeLayer.setSS14(ege.getSS14());
        boreholeLayer.setSS15(ege.getSS15());
        boreholeLayer.setSS16(ege.getSS16());
        boreholeLayer.setSS17(ege.getSS17());
        boreholeLayer.setSS18(ege.getSS18());
        boreholeLayer.setSS19(ege.getSS19());
        boreholeLayer.setSS20(ege.getSS20());

        boreholeLayer.setColor(ege.getColor());
        boreholeLayer.setWaterDepth(ege.getWaterDepth());

        boreHoleLayerMainRepository.save(boreholeLayer);
    }

    @Override
    public DescriptionKgaDto getDescriptionKgaDto(Long borholeLayerId) {
        var boreholeLayer = getById(borholeLayerId);

        var descriptionKgaDto = boreholeLayerMapper.boreholeLayerToDescriptionKgaDto(boreholeLayer);

        var soilSubkindMap = descriptionKgaDto.getSoilSubkindMap();
        var soilSubkindAdjMap = descriptionKgaDto.getSoilSubkindAdjMap();

        Class<? extends BoreholeLayer> clazz = boreholeLayer.getClass();

        fillSoilSubkindMap(soilSubkindMap, clazz, boreholeLayer);
        fillSoilSubkindAdjMap(soilSubkindAdjMap, clazz, boreholeLayer);

        return descriptionKgaDto;
    }

    private void fillSoilSubkindMap(Map<String, SoilSubkindDto> soilSubkindMap, Class<?> clazz, BoreholeLayer boreholeLayer) {
        soilSubkindMap.forEach((key, value) -> {

            try {
                var field = clazz.getDeclaredField(key);
                field.setAccessible(true);

                Long fieldValue = (Long) field.get(boreholeLayer);

                var newValue = getSoilSubkindDto(fieldValue);

                soilSubkindMap.put(key, newValue);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void fillSoilSubkindAdjMap(Map<String, SoilSubkindAdjDto> soilSubkindAdjMap, Class<?> clazz, BoreholeLayer boreholeLayer) {
        soilSubkindAdjMap.forEach((key, value) -> {

            try {
                var field = clazz.getDeclaredField(key);
                field.setAccessible(true);

                var fieldValue = (Long) field.get(boreholeLayer);
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

    @Override
    public void updateBoreholeLayer(DescriptionKgaDto descriptionKgaDto) {
        var boreholeLayer = getById(descriptionKgaDto.getBoreholeLayerId());

        boreholeLayerMapper.updateBoreholeLayer(boreholeLayer, descriptionKgaDto);
//        boreholeLayer.setShortName(descriptionKgaDto.getShortName());
        var color = colorRepository.findById(descriptionKgaDto.getColorDto().getId());
        color.ifPresent(boreholeLayer::setColor);

        boreHoleLayerMainRepository.save(boreholeLayer);
    }
}