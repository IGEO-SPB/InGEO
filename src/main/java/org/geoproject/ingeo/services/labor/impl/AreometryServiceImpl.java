package org.geoproject.ingeo.services.labor.impl;

import org.geoproject.ingeo.dto.methodDtos.AreometryDTO;
import org.geoproject.ingeo.dto.methodDtos.GranCompositionDTO;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.AreometryMapper;
import org.geoproject.ingeo.methods.labor.AreometricMethod;
import org.geoproject.ingeo.models.labor.Areometry;
import org.geoproject.ingeo.models.labor.GranCompositionAreometry;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.repositories.labor.AreometryRepository;
import org.geoproject.ingeo.services.labor.AreometryService;
import org.geoproject.ingeo.services.labor.GranCompositionAreometryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreometryServiceImpl implements AreometryService {

    private final AreometryRepository areometryRepository;
    private final AreometryMapper areometryMapper;

    private final GranCompositionAreometryService granCompositionAreometryService;

    @Override
    public Areometry getBySample(Sample sample) {
        Optional<Areometry> bySample = areometryRepository.findBySample(sample);

        if (!bySample.isPresent()) {
            Areometry newAreometry = new Areometry();

            areometryMapper.updateAreometryFromSample(newAreometry, sample);

            newAreometry.setSample(sample);

            areometryRepository.save(newAreometry);

            return newAreometry;
        }

        return bySample.get();
    }

    @Override
    public List<Areometry> getAllBySample(Sample sample) {
        throw new NotImplemented("getAllBySample method in AreometryServiceImpl not implemented");
    }

    @Override
    @Transactional
    public void update(Areometry object) {
        areometryRepository.save(object);
    }

    @Override
    public void updateFromDTO(AreometryDTO areometryDTO, Sample sample) {
        Areometry bySample = areometryRepository.findBySample(sample)
                .orElseThrow(() -> new NotFoundException("Areometry entity not found"));

        areometryMapper.updateAreometry(bySample, areometryDTO);

        areometryRepository.save(bySample);
    }

    @Override
    public AreometryDTO getDto(Areometry areometry) {
        AreometryDTO areometryDTO = areometryMapper.areometryToAreometryDTO(areometry);

        areometryDTO.setLaborNumber(areometry.getSample().getLaborNumber());
        areometryDTO.setVoidRatio(areometry.getSample().getVoidRatio());

        return areometryDTO;
    }

    @Override
    public void calculate(Sample sample, AreometryDTO areometryDTO) {

        GranCompositionAreometry granCompositionAreometry = granCompositionAreometryService.getBySample(sample);

        GranCompositionDTO granCompositionDTO = granCompositionAreometryService.getDto(granCompositionAreometry);
        AreometricMethod.calculateGranCompositionAreometry(areometryDTO, granCompositionDTO);

        updateFromDTO(areometryDTO, sample);
        granCompositionAreometryService.updateFromDTO(sample, granCompositionDTO);
    }

    @Override
    public Areometry getBySampleAndNumber(Sample sample, Integer number) {
        throw new NotImplemented("getBySampleAndNumber method in AreometryServiceImpl not implemented");
    }

    @Override
    public List<Areometry> getByProject(Project currentProject) {
        throw new NotImplemented("getByProject method not implemented");
    }
}