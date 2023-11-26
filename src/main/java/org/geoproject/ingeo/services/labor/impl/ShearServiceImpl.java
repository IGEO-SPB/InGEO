package org.geoproject.ingeo.services.labor.impl;

import org.geoproject.ingeo.dto.methodDtos.ShearDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.ShearMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.Shear;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.labor.ShearRepository;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.labor.PhysicalPropertiesService;
import org.geoproject.ingeo.services.labor.ShearService;
import org.geoproject.ingeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_ARCHIVE;

@Service
@RequiredArgsConstructor
public class ShearServiceImpl implements ShearService {

    private final CurrentState currentState;
    private final ShearMapper shearMapper;

    private final ShearRepository shearRepository;

    private final PhysicalPropertiesService physicalPropertiesService;
    private final SampleService sampleService;

    @Override
    public List getDTOs(SurveyPoint surveyPoint) {
        List<Shear> entitiesByProject = shearRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        List<ShearDto> shearDtos = shearMapper.shearToShearDto(entitiesByProject);

        shearDtos.forEach(dto -> {
            var sample = sampleService.getByLaborNumber(dto.getLaborNumber());

            var physicalProperties = physicalPropertiesService.getBySample(sample);

            dto.setPhysicalPropertiesDensityBefore(physicalProperties.getRingDensityAverage());
            dto.setPhysicalPropertiesWaterContentBefore(physicalProperties.getNaturalAverageWaterContent());
        });

        return shearDtos;
    }

    @Override
    public ShearDto getDto(Shear entity) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getDto"));
    }

    @Override
    @Transactional
    public void updateFromDtos(List<ShearDto> dtos) {
        List<Shear> updatedObjects = shearRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        var oldDtos = dtos.stream()
                .filter(dto -> Objects.nonNull(dto.getId()))
                .toList();

        System.out.println("oldDtos");
        System.out.println("size: " + oldDtos.size());
        oldDtos.forEach(d -> {
            System.out.println(d.getId());
            System.out.println(d.getArchive());
            System.out.println(d.getLaborNumber());
            System.out.println("--------");
        });


        shearMapper.updateShearFromShearDto(updatedObjects, oldDtos);

        var newDtos = dtos.stream()
                .filter(dto -> Objects.isNull(dto.getId()))
                .toList();

        System.out.println("newDtos");
        System.out.println("size: " + newDtos.size());
        newDtos.forEach(d -> {
            System.out.println(d.getId());
            System.out.println(d.getArchive());
            System.out.println(d.getLaborNumber());
            System.out.println("--------");
        });

        List<Shear> newObjects = shearMapper.shearDtoToShear(newDtos);

        System.out.println("CHECK 1");

        shearRepository.saveAll(updatedObjects);
        System.out.println("CHECK 2");

        shearRepository.saveAll(newObjects);
        System.out.println("CHECK 3");

    }

    @Override
    public void updateFromDTO(Sample sample, ShearDto sourceDto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("updateFromDTO"));
    }

    @Override
    public List<Shear> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getEntitiesBySurveyPoint"));
    }

    @Override
    public void create(List<ShearDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("create"));
    }

    @Override
    @Transactional
    public void delete(ShearDto dto) {

        if (Objects.nonNull(dto.getId())) {
            Optional<Shear> deletedShear = shearRepository.findById(dto.getId());

            deletedShear.ifPresent(entity -> {
                entity.setIsArchive(ENTITY_IS_ARCHIVE);

                shearRepository.save(entity);
            });
        }
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("checkForExistingLaborNumber"));
    }

    @Override
    public void calculate(List<ShearDto> sourceDtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("calculate"));

//        var emptyDtos = sourceDtos.stream()
//                .filter(sourceDto -> Objects.isNull(sourceDto.getLaborNumber()) ||
//                                Objects.equals(StringUtils.EMPTY, sourceDto.getLaborNumber()))
//                                        .collect(Collectors.toList());
//
//        sourceDtos.removeAll(emptyDtos);
//
//        List<WaterExtractPartialResult> waterExtractPartialResultList = waterExtractPartialResultRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());
//
//        List<WaterExtractPartialResultDto> resultDtos = waterExtractMapper.waterExtractPartialResultToWaterExtractPartialResultDto(waterExtractPartialResultList);
//
//        sourceDtos.forEach(sourceDto -> {
//            Optional<WaterExtractPartialResultDto> resultDto = resultDtos.stream()
////                    .filter(dto -> Boolean.FALSE.equals(dto.getIsBlocked()) && Objects.equals(dto.getLaborNumber(), sourceDto.getLaborNumber()))
//                    .filter(dto -> Objects.equals(dto.getLaborNumber(), sourceDto.getLaborNumber()))
//                    .findFirst();
//
//            resultDto.ifPresent(waterExtractPartialResultDto -> WaterExtractPartialMethod.calculateWaterExtractPartial(sourceDto, waterExtractPartialResultDto));
//        });
//
//        waterExtractMapper.updateWaterExtractPartialResultFromWaterExtractPartialResultDto(waterExtractPartialResultList, resultDtos);
//
//        waterExtractPartialResultRepository.saveAll(waterExtractPartialResultList);
    }

}
