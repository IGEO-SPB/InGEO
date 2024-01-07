package org.geoproject.ingeo.services.cameral;

import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.mainViewsDtos.BoreholeLayerDto;
import org.geoproject.ingeo.models.BoreholeLayer;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.MainViewService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BoreHoleLayerMainService extends MainViewService<BoreholeLayer, BoreholeLayerDto> {

    @Override
    List<BoreholeLayer> getAll();

    @Override
    BoreholeLayer getById(Long id);

//    @Override
//    void create(BoreholeLayerDTO object);

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
    void delete(BoreholeLayerDto object);

    @Override
    default void deleteByDto(BoreholeLayerDto dto) {

    }

    @Override
    List<BoreholeLayerDto> getDtos(List<BoreholeLayer> objects);

    @Override
    void updateFromDtos(List<BoreholeLayer> objects, List<BoreholeLayerDto> dtos);

    @Override
    void updateFromDtos(List<BoreholeLayerDto> dtos);

    @Override
    List<BoreholeLayerDto> getDtosByProject(Project project);

    @Override
    List<BoreholeLayerDto> getDtosBySurveyPointId(Long surveyPointId);

    @Override
    BoreholeLayerDto cloneDto(BoreholeLayerDto egeDto);

    @Override
    void enrichEntity(Long updatedEntityId, Long sourceEntityId);

    DescriptionKgaDto getDescriptionKgaDto(Long egeId);

    void updateBoreholeLayer(DescriptionKgaDto descriptionKgaDto);
}