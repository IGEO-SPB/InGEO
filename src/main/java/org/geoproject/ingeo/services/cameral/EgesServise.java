package org.geoproject.ingeo.services.cameral;

import org.geoproject.ingeo.dto.mainViewsDtos.EgeDTO;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.MainViewService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EgesServise extends MainViewService<Ege, EgeDTO> {
    @Override
    List<Ege> getAll();

    @Override
    Ege getById(Long id);

    @Override
    Ege getBySample(Sample sample);

    @Override
    void create(EgeDTO object);

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
    void delete(EgeDTO object);

    @Override
    List<EgeDTO> getDtos(List<Ege> objects);

    @Override
    void updateFromDtos(List<Ege> objects, List<EgeDTO> dtos);
}
