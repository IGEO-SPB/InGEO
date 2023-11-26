package com.geoproject.igeo.mapper.qualifier;

import com.geoproject.igeo.dto.WaterExtractFullDto;
import com.geoproject.igeo.dto.WaterExtractFullResultDto;
import com.geoproject.igeo.dto.WaterExtractPartialDto;
import com.geoproject.igeo.dto.WaterExtractPartialResultDto;
import com.geoproject.igeo.exceptions.ExceptionTypeEnum;
import com.geoproject.igeo.exceptions.NotFoundException;
import com.geoproject.igeo.models.Sample;
import com.geoproject.igeo.repositories.SamplesRepository;
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
