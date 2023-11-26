package org.geoproject.ingeo.mapper.qualifier;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullResultDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.repositories.SamplesRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Named("WaterExtractMapperQualifier")
@RequiredArgsConstructor
public class WaterExtractMapperQualifier {

    private final SamplesRepository samplesRepository;

    @Named("getBySurveyPointNumberAndLaborNumber")
    public Sample getBySurveyPointNumberAndLaborNumber(WaterExtractPartialDto dto) {
        return samplesRepository.findBySurveyPointNumberAndLaborNumber(dto.getSurveyPointNumber(), dto.getLaborNumber()).
                orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SAMPLE_NOT_FOUND_EXCEPTION.getMessage()));
    }

    @Named("getBySurveyPointNumberAndLaborNumber")
    public Sample getBySurveyPointNumberAndLaborNumber(WaterExtractPartialResultDto dto) {
        return samplesRepository.findBySurveyPointNumberAndLaborNumber(dto.getSurveyPointNumber(), dto.getLaborNumber()).
                orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SAMPLE_NOT_FOUND_EXCEPTION.getMessage()));
    }

    @Named("getBySurveyPointNumberAndLaborNumber")
    public Sample getBySurveyPointNumberAndLaborNumber(WaterExtractFullDto dto) {
        return samplesRepository.findBySurveyPointNumberAndLaborNumber(dto.getSurveyPointNumber(), dto.getLaborNumber()).
                orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SAMPLE_NOT_FOUND_EXCEPTION.getMessage()));
    }

    @Named("getBySurveyPointNumberAndLaborNumber")
    public Sample getBySurveyPointNumberAndLaborNumber(WaterExtractFullResultDto dto) {
        return samplesRepository.findBySurveyPointNumberAndLaborNumber(dto.getSurveyPointNumber(), dto.getLaborNumber()).
                orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SAMPLE_NOT_FOUND_EXCEPTION.getMessage()));
    }
}
