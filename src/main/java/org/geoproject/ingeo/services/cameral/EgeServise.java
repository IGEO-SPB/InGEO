package org.geoproject.ingeo.services.cameral;

import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.MainViewService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EgeServise extends MainViewService<Ege, EgeDto> {
    @Override
    List<Ege> getAll();

    @Override
    Ege getById(Long id);

    @Override
    void create(List<Ege> objectList);

    @Override
    void update(Ege object);

    @Override
    void delete(List<Ege> object);

    @Override
    List<Ege> getByProject(Project project);

    @Override
    List<Ege> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber);

    Ege getByNumberAndProject(String number, Project project);

    @Override
    void delete(EgeDto object);

    @Override
    default void deleteByDto(EgeDto dto) {

    }

    @Override
    List<EgeDto> getDtos(List<Ege> objects);

    @Override
    void updateFromDtos(List<Ege> objects, List<EgeDto> dtos);

    @Override
    default void updateFromDtos(List<EgeDto> dtos) {

    }

    EgeDto getDto(Ege ege);

    DescriptionKgaDto getDescriptionKgaDto(Long egeId);

    void updateEge(DescriptionKgaDto descriptionKgaDto);

    @Override
    List<EgeDto> getDtosByProject(Project project);
}
