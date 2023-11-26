package org.geoproject.ingeo.services.methodViews;

import org.geoproject.ingeo.dto.PrintInfoDto;
import org.geoproject.ingeo.dto.methodDtos.SoilCorrosionInputDto;
import org.geoproject.ingeo.dto.methodDtos.SoilCorrosionResultDto;
import org.geoproject.ingeo.models.Sample;

import java.io.File;
import java.util.List;

public interface SoilCorrosionService {
    public SoilCorrosionInputDto createNewSoilCorrosion(Sample sp, String labNumber);
    public SoilCorrosionInputDto updateSoilCorrosion(Long id, SoilCorrosionInputDto newData);
    public void deleteSoilCorrosion(Long id);
    public SoilCorrosionInputDto calculateResistance(SoilCorrosionInputDto dto);
    public List<SoilCorrosionResultDto> calculateAverageUesgAndPkt(Sample sample, String labNumber);
    public SoilCorrosionResultDto calculateCorrosionAggressionDegree(SoilCorrosionResultDto dto);
    public File printCorrosionAggressionTable(PrintInfoDto printInfoDto, Sample sp);

    public List<SoilCorrosionInputDto> getInputsBySample(Sample sp);
    public List<SoilCorrosionResultDto> getOrCreateResultBySample(Sample sp);
}

