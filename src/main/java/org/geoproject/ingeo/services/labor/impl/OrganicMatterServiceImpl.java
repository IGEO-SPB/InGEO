package org.geoproject.ingeo.services.methodViews.impl;

import org.geoproject.ingeo.dto.methodDtos.OrganicMatterDTO;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.OrganicMatterMapper;
import org.geoproject.ingeo.methods.labor.OrganicMatterContentDeterminationMethod;
import org.geoproject.ingeo.models.labor.OrganicMatter;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.classificators.PeatDecayDegree;
import org.geoproject.ingeo.repositories.labor.OrganicMatterRepository;
import org.geoproject.ingeo.services.classificators.PeatDecayDegreeService;
import org.geoproject.ingeo.services.methodViews.OrganicMatterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganicMatterServiceImpl implements OrganicMatterService {

    private final PeatDecayDegreeService peatDecayDegreeService;

    private final OrganicMatterRepository organicMatterRepository;
    private final OrganicMatterMapper organicMatterMapper;

    @Override
    public OrganicMatter getBySample(Sample sample) {
        Optional<OrganicMatter> bySample = organicMatterRepository.findBySample(sample);

        if (!bySample.isPresent()) {
            OrganicMatter newOrganicMatter = new OrganicMatter();

            organicMatterMapper.updateOrganicMatterFromSample(newOrganicMatter, sample);

            organicMatterRepository.save(newOrganicMatter);

            return newOrganicMatter;
        }

        return bySample.get();
    }

    @Override
    public List<OrganicMatter> getAllBySample(Sample sample) {
        throw new NotImplemented("getAllBySample method in OrganicMatterService not implemented");
    }

    @Override
    public void update(OrganicMatter object) {
        throw new NotImplemented("update method in OrganicMatterService not implemented");
    }

    @Override
    public void updateFromDTO(OrganicMatterDTO organicMatterDTO, Sample sample) {
        OrganicMatter byLaborNumber = getBySample(sample);

        organicMatterMapper.updateOrganicMatter(byLaborNumber, organicMatterDTO);

        organicMatterRepository.save(byLaborNumber);
    }

    @Override
    public OrganicMatterDTO getDto(OrganicMatter organicMatter) {
        return organicMatterMapper.organicMatterToOrganicMatterDTO(organicMatter);
    }

    @Override
    public void calculate(Sample sample, OrganicMatterDTO organicMatterDTO) {
        OrganicMatterContentDeterminationMethod.calculateOrganicMatter(organicMatterDTO);
        float p250 = OrganicMatterContentDeterminationMethod.calculateP250(organicMatterDTO);
        try {
            PeatDecayDegree byP250 = peatDecayDegreeService.findByP250((int) p250);
            organicMatterDTO.setDecompositionDegree(byP250.getValue());
        } catch (NotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("Проверка после выброса исключения");
        updateFromDTO(organicMatterDTO, sample);
    }

    @Override
    public OrganicMatter getBySampleAndNumber(Sample sample, Integer number) {
        throw new NotImplemented("getBySampleAndNumber method in OrganicMatterService not implemented");
    }

    @Override
    public List<OrganicMatter> getByProject(Project currentProject) {
        throw new NotImplemented("getByProject method in OrganicMatterService not implemented");
    }
}