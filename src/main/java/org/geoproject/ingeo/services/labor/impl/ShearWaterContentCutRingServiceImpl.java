package org.geoproject.ingeo.services.methodViews.impl;

import org.geoproject.ingeo.dto.methodDtos.ShearDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.ShearMapper;
import org.geoproject.ingeo.methods.labor.ShearMethod;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.Shear;
import org.geoproject.ingeo.repositories.labor.ShearRepository;
import org.geoproject.ingeo.services.methodViews.ShearWaterContentCutRingService;
import org.geoproject.ingeo.services.tableViews.GranCompositionAreometryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.geoproject.ingeo.exceptions.ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class ShearWaterContentCutRingServiceImpl implements ShearWaterContentCutRingService {

    private final ShearRepository shearRepository;
    private final ShearMapper shearMapper;

    private final GranCompositionAreometryService granCompositionAreometryService;

    @Override
    public Shear getBySample(Sample sample) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getBySample"));
//        Optional<Areometry> bySample = areometryRepository.findBySample(sample);
//
//        if (!bySample.isPresent()) {
//            Areometry newAreometry = new Areometry();
//
//            areometryMapper.updateAreometryFromSample(newAreometry, sample);
//
//            newAreometry.setSample(sample);
//
//            areometryRepository.save(newAreometry);
//
//            return newAreometry;
//        }
//
//        return bySample.get();
    }

    @Override
    public List<Shear> getAllBySample(Sample sample) {
        return shearRepository.findBySampleOrderByShearPointNumber(sample);
    }

    @Override
    @Transactional
    public void update(Shear object) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("update"));

//        areometryRepository.save(object);
    }

    @Override
    public void updateFromDTO(ShearDto shearDto, Sample sample) {
        var shear = shearRepository.findBySampleAndShearPointNumber(sample, shearDto.getShearPointNumber())
                .orElseThrow(() -> new NotFoundException(ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("Shear")));

        shearMapper.updateShearFromShearDto(shear, shearDto);

        shearRepository.save(shear);
    }

    @Override
    public ShearDto getDto(Shear shear) {
        ShearDto shearDto = shearMapper.shearToShearDto(shear);

        return shearDto;
    }

    @Override
    public void calculate(Sample sample, ShearDto shearDto) {
        var allPointsInShear = shearRepository.findBySampleOrderByShearPointNumber(sample);

        var shearDtoList = shearMapper.shearToShearDto(allPointsInShear);

        ShearMethod.calculate(shearDto, shearDtoList);

        updateFromDTO(shearDto, sample);
        shearMapper.updateShearFromShearDto(allPointsInShear, shearDtoList);

        shearRepository.saveAll(allPointsInShear);
    }

    @Override
    public Shear getBySampleAndNumber(Sample sample, Integer number) {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(sample.getId());

        System.out.println(number);

        var result = shearRepository.findBySampleAndShearPointNumber(sample, number);

        System.out.println("----------");
        System.out.println("Shear:");

        System.out.println(result);

        System.out.println(result.get());
        System.out.println(result.get().getId());

        System.out.println(result.get().getShearPointNumber());

        System.out.println("==========");

//        var result = shearRepository.findBySampleAndShearPointNumber(sample, number)
//                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SHEAR_NOT_FOUND_EXCEPTION.getMessage()));

        return result.get();
    }

    @Override
    public List<Shear> getByProject(Project currentProject) {
        return shearRepository.findByProjectSortByShearPointNumber(currentProject);
    }
}