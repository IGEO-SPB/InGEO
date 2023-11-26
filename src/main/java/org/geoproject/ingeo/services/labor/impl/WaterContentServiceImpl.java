package org.geoproject.ingeo.services.labor.impl;

import org.geoproject.ingeo.dto.methodDtos.WaterContentDTO;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.WaterContentMapper;
import org.geoproject.ingeo.methods.labor.WaterContentMethod;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.WaterContent;
import org.geoproject.ingeo.repositories.labor.WaterContentRepository;
import org.geoproject.ingeo.services.labor.WaterContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WaterContentServiceImpl implements WaterContentService {

    private final WaterContentRepository waterContentRepository;

    private final WaterContentMapper waterContentMapper;

    @Override
    @Transactional
    public WaterContent getBySample(Sample sample) {
        Optional<WaterContent> bySample = waterContentRepository.findBySample(sample);

        if (!bySample.isPresent()) {
            WaterContent newWaterContent = new WaterContent();

            waterContentMapper.updateWaterContentFromSample(newWaterContent, sample);

            waterContentRepository.save(newWaterContent);

            return newWaterContent;
        }

        return bySample.get();
    }

    @Override
    public List<WaterContent> getAllBySample(Sample sample) {
        throw new NotImplemented("getAllBySample method in WaterContentServiceImpl not implemented");
    }

    @Override
    @Transactional
    public void update(WaterContent object) {
        throw new NotImplemented("update method in WaterContentService not implemented");
    }

    @Override
    @Transactional
    public void updateFromDTO(WaterContentDTO waterContentDTO, Sample sample) {
        WaterContent foundWaterContent = waterContentRepository.findBySample(sample)
                .orElseThrow(() -> new NotFoundException("WaterContent entity not found"));

        waterContentMapper.updateWaterContent(foundWaterContent, waterContentDTO);
        waterContentRepository.save(foundWaterContent);
    }

    @Override
    public WaterContentDTO getDto(WaterContent waterContent) {
        return waterContentMapper.waterContentToWaterContentDTO(waterContent);
    }

    @Override
    @Transactional
    public void calculate(Sample sample, WaterContentDTO waterContentDto) {
        WaterContentMethod.calculatePlasticityIndex(waterContentDto);
        updateFromDTO(waterContentDto, sample);
    }

    @Override
    public WaterContent getBySampleAndNumber(Sample sample, Integer number) {
        throw new NotImplemented("getBySampleAndNumber method in WaterContentServiceImpl not implemented");
    }

    @Override
    public List<WaterContent> getByProject(Project currentProject) {
        throw new NotImplemented("getByProject method not implemented");
    }
}