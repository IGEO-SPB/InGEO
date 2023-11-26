package org.geoproject.ingeo.services.tableViews;

import org.geoproject.ingeo.dto.methodDtos.ShearDto;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.Shear;
import org.geoproject.ingeo.models.SurveyPoint;

import java.util.List;

public interface ShearService extends TableService<Shear, ShearDto> {

    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    ShearDto getDto(Shear entity);

    @Override
    void updateFromDtos(List<ShearDto> dtos);

    @Override
    void updateFromDTO(Sample sample, ShearDto sourceDto);

    @Override
    List<Shear> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<ShearDto> dtos);

    @Override
    void delete(ShearDto dto);

    @Override
    Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project);

    @Override
    void calculate(List<ShearDto> dtos);
}
