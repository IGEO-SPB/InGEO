package org.geoproject.ingeo.services.mainViews;

import org.geoproject.ingeo.dto.mainViewsDtos.BoreholeLayerDTO;
import org.geoproject.ingeo.models.BoreholeLayer;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BoreHoleLayerMainService extends MainViewService<BoreholeLayer, BoreholeLayerDTO> {

    @Override
    List<BoreholeLayer> getAll();

    @Override
    BoreholeLayer getById(Long id);

    @Override
    void create(BoreholeLayerDTO object);

    @Override
    void create(List<BoreholeLayer> object);

    @Override
    void update(BoreholeLayer object);

    @Override
    void delete(List<BoreholeLayer> object);

    @Override
    List<BoreholeLayer> getByProject(Project project);

    @Override
    List<BoreholeLayer> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber);

    @Override
    BoreholeLayer getBySample(Sample sample);

    @Override
    void delete(BoreholeLayerDTO object);

    @Override
    List<BoreholeLayerDTO> getDtos(List<BoreholeLayer> objects);

    @Override
    void updateFromDtos(List<BoreholeLayer> objects, List<BoreholeLayerDTO> dtos);
}