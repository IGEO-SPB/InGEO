package org.geoproject.ingeo.services.methodViews.impl;

import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshAreometryDto;
import org.geoproject.ingeo.mapper.laborMethods.ConstructionMeshAreometryMapper;
import org.geoproject.ingeo.methods.labor.ConstructionMeshAreometryMethod;
import org.geoproject.ingeo.models.labor.ConstructionMeshAreometry;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.repositories.labor.ConstructionMeshAreometryRepository;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.methodViews.ConstructionMeshAreometryService;
import org.geoproject.ingeo.services.tableViews.GranCompositionConstructionMeshAreometryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConstructionMeshAreometryServiceImpl implements ConstructionMeshAreometryService {

    private final ConstructionMeshAreometryRepository repository;
    private final ConstructionMeshAreometryMapper mapper;
    private final SampleService sampleService;

    private final GranCompositionConstructionMeshAreometryService granCompositionService;

    @Override
    public ConstructionMeshAreometry getBySample(Sample sample) {
        Optional<ConstructionMeshAreometry> byId = repository.findBySampleId(sample.getId());

        if (byId.isEmpty()) {
            ConstructionMeshAreometry foundConstructionMeshAreometry = new ConstructionMeshAreometry();

            mapper.updateConstructionMeshAreometry(foundConstructionMeshAreometry, sample);

            repository.save(foundConstructionMeshAreometry);

            return foundConstructionMeshAreometry;
        }

        return byId.get();
    }

    @Override
    public List<ConstructionMeshAreometry> getAllBySample(Sample sample) {
        return null;
    }

    @Override
    @Transactional
    public void update(ConstructionMeshAreometry object) {
        repository.save(object);
    }

    @Override
    @Transactional
    public void updateFromDTO(ConstructionMeshAreometryDto dto, Sample sample) {
        var foundModel = repository.findByLaborNumber(dto.getLaborNumber())
                .orElseThrow(() -> new RuntimeException("ConstructionMeshAreometry entity not found"));

        mapper.updateModelFromDTO(foundModel, dto);

        repository.save(foundModel);
    }

    @Override
    public ConstructionMeshAreometryDto getDto(ConstructionMeshAreometry model) {
        var dto = mapper.modelToDTO(model);
        Sample sample = sampleService.getByLaborNumber(model.getLaborNumber());
        dto.setIsSand(sample.getIsSand());

        return dto;
    }

    public ConstructionMeshAreometryDto getConstructionMeshAreometryDTOBySample(Sample sample) {
        var model = repository.findBySampleId(sample.getId())
                .orElseThrow(() -> new RuntimeException("ConstructionMeshAreometry Entity not found"));

        return mapper.modelToDTO(model);
    }

    @Override
    public void calculate(Sample sample, ConstructionMeshAreometryDto dto) {
        //throw new NotImplementedException("Calculation not implemented yet");
         var granComposition = granCompositionService.getBySample(sample); // ConstructionMeshAreometryResultDto constructionMeshResultDto = granCompositionConstructionMeshService.getDto(granCompositionConstructionMesh);
         var resultDto = granCompositionService.getDto(granComposition);

         ConstructionMeshAreometryMethod.calculate(dto, resultDto);

         updateFromDTO(dto, sample);
         granCompositionService.updateFromDTO(sample, resultDto);
    }

    @Override
    public ConstructionMeshAreometry getBySampleAndNumber(Sample sample, Integer number) {
        return null;
    }

    @Override
    public List<ConstructionMeshAreometry> getByProject(Project currentProject) {
        return null;
    }
}