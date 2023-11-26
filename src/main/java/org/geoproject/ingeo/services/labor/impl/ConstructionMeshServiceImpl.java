package org.geoproject.ingeo.services.labor.impl;

import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshDTO;
import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshResultDto;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.ConstructionMeshMapper;
import org.geoproject.ingeo.methods.labor.ConstructionMeshMethod;
import org.geoproject.ingeo.models.labor.ConstructionMesh;
import org.geoproject.ingeo.models.labor.GranCompositionConstructionMesh;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.repositories.labor.ConstructionMeshRepository;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.labor.ConstructionMeshService;
import org.geoproject.ingeo.services.labor.GranCompositionConstructionMeshService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConstructionMeshServiceImpl implements ConstructionMeshService {

    private final ConstructionMeshRepository constructionMeshRepository;
    private final ConstructionMeshMapper mapper;
    private final SampleService sampleService;

    private final GranCompositionConstructionMeshService granCompositionConstructionMeshService;

    @Override
    public ConstructionMesh getBySample(Sample sample) {
        Optional<ConstructionMesh> byId = constructionMeshRepository.findBySampleId(sample.getId());

        if (byId.isEmpty()) {
            ConstructionMesh foundConstructionMesh = new ConstructionMesh();

            mapper.updateConstructionMesh(foundConstructionMesh, sample);

            constructionMeshRepository.save(foundConstructionMesh);

            return foundConstructionMesh;
        }

        return byId.get();
    }

    @Override
    public List<ConstructionMesh> getAllBySample(Sample sample) {
        throw new NotImplemented("getAllBySample method in ConstructionMeshServiceImpl not implemented");
    }

    @Override
    @Transactional
    public void update(ConstructionMesh object) {
        constructionMeshRepository.save(object);
    }

    @Override
    @Transactional
    public void updateFromDTO(ConstructionMeshDTO constructionMeshDTO, Sample sample) {
        ConstructionMesh foundConstructionMesh = constructionMeshRepository.findByLaborNumber(constructionMeshDTO.getLaborNumber())
                .orElseThrow(() -> new RuntimeException("ConstructionMesh entity not found"));

        mapper.updateConstructionMeshFromConstructionMeshDTO(foundConstructionMesh, constructionMeshDTO);

        constructionMeshRepository.save(foundConstructionMesh);
    }

    @Override
    public ConstructionMeshDTO getDto(ConstructionMesh constructionMesh) {
        ConstructionMeshDTO constructionMeshDTO = mapper.constructionMeshToConstructionMeshDTO(constructionMesh);

        Sample sample = sampleService.getByLaborNumber(constructionMesh.getLaborNumber());

        constructionMeshDTO.setIsSand(sample.getIsSand());

        return constructionMeshDTO;
    }

    public ConstructionMeshDTO getConstructionMeshDTOBySample(Sample sample) {
        ConstructionMesh byLaborNumber = constructionMeshRepository.findBySampleId(sample.getId())
                .orElseThrow(() -> new RuntimeException("ConstructionMesh Entity not found"));

        return mapper.constructionMeshToConstructionMeshDTO(byLaborNumber);
    }

    @Override
    public void calculate(Sample sample, ConstructionMeshDTO constructionMeshDTO) {
        GranCompositionConstructionMesh granCompositionConstructionMesh = granCompositionConstructionMeshService.getBySample(sample);
        ConstructionMeshResultDto constructionMeshResultDto = granCompositionConstructionMeshService.getDto(granCompositionConstructionMesh);

        ConstructionMeshMethod.calculateConstructionMesh(constructionMeshDTO, constructionMeshResultDto);

        updateFromDTO(constructionMeshDTO, sample);
        granCompositionConstructionMeshService.updateFromDTO(sample, constructionMeshResultDto);
    }

    @Override
    public ConstructionMesh getBySampleAndNumber(Sample sample, Integer number) {
        throw new NotImplemented("getBySampleAndNumber method in ConstructionMeshServiceImpl not implemented");
    }

    @Override
    public List<ConstructionMesh> getByProject(Project currentProject) {
        throw new NotImplemented("getByProject method not implemented");
    }
}