package com.geoproject.igeo.mapper.qualifier;

import com.geoproject.igeo.dto.ShearDto;
import com.geoproject.igeo.dto.WaterExtractFullDto;
import com.geoproject.igeo.dto.WaterExtractFullResultDto;
import com.geoproject.igeo.dto.WaterExtractPartialDto;
import com.geoproject.igeo.dto.WaterExtractPartialResultDto;
import com.geoproject.igeo.exceptions.ExceptionTypeEnum;
import com.geoproject.igeo.exceptions.NotFoundException;
import com.geoproject.igeo.models.Sample;
import com.geoproject.igeo.repositories.SamplesRepository;
import com.geoproject.igeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Named("ShearMapperQualifier")
@RequiredArgsConstructor
public class ShearMapperQualifier {

    private final SamplesRepository samplesRepository;
    private final CurrentState currentState;

    @Named("getByProjectIdAndLaborNumber")
//    public Sample getByProjectIdAndLaborNumber(ShearDto dto) {
    public Sample getByProjectIdAndLaborNumber(String laborNumber) {
//        System.out.println("dto.getId()");
//        System.out.println(dto.getId());

        System.out.println("currentState.getCurrentProject().getId()");
        System.out.println(currentState.getCurrentProject().getId());

        System.out.println("laborNumber");
        System.out.println(laborNumber);

//        System.out.println("dto.getLaborNumber()");
//        System.out.println(dto.getLaborNumber());
//        return samplesRepository.findByProjectIdAndLaborNumber(currentState.getCurrentProject().getId(), dto.getLaborNumber())

        Sample sample = samplesRepository.findByProjectIdAndLaborNumber(currentState.getCurrentProject(), laborNumber)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SAMPLE_NOT_FOUND_EXCEPTION.getMessage()));

        System.out.println("sample");
        System.out.println(sample);

        return sample;
    }

//    @Named("getBySurveyPointNumberAndLaborNumber")
//    public Sample getBySurveyPointNumberAndLaborNumber(WaterExtractPartialResultDto dto) {
//        return samplesRepository.findBySurveyPointNumberAndLaborNumber(dto.getSurveyPointNumber(), dto.getLaborNumber()).
//                orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SAMPLE_NOT_FOUND_EXCEPTION.getMessage()));
//    }
//
//    @Named("getBySurveyPointNumberAndLaborNumber")
//    public Sample getBySurveyPointNumberAndLaborNumber(WaterExtractFullDto dto) {
//        return samplesRepository.findBySurveyPointNumberAndLaborNumber(dto.getSurveyPointNumber(), dto.getLaborNumber()).
//                orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SAMPLE_NOT_FOUND_EXCEPTION.getMessage()));
//    }
//
//    @Named("getBySurveyPointNumberAndLaborNumber")
//    public Sample getBySurveyPointNumberAndLaborNumber(WaterExtractFullResultDto dto) {
//        return samplesRepository.findBySurveyPointNumberAndLaborNumber(dto.getSurveyPointNumber(), dto.getLaborNumber()).
//                orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SAMPLE_NOT_FOUND_EXCEPTION.getMessage()));
//    }
}
