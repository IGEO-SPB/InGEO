package org.geoproject.ingeo.services.common.impl;

import org.geoproject.ingeo.dto.SurveyPointDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.SurveyPointMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.SurveyPointRepository;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyPointsServiceImpl implements SurveyPointsService {

    private final SurveyPointRepository surveyPointRepository;
    private final SurveyPointMapper surveyPointMapper;
    private final CurrentState currentState;

    @Override
    public SurveyPoint getById(Long id) {
        return surveyPointRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SURVEY_POINT_NOT_FOUND_EXCEPTION.getExceptionMessage("SurveyPoint")));
    }

    public List<SurveyPoint> getAll() {
        return surveyPointRepository.findAll();
    }

//    @Transactional
//    public void create(SurveyPointDTO dto) {
//        SurveyPoint newSurveyPoint = surveyPointMapper.surveyPointDtoToSurveyPoint(dto);
//        newSurveyPoint.setProject(currentState.getCurrentProject());
//
//        newSurveyPoint.setNameNumber(newSurveyPoint.getSurveyPointsType().getSurveyTypeShortName() +
//                SPACE_PATTERN + newSurveyPoint.getPointNumber());
//
//        surveyPointsRepository.save(newSurveyPoint);
//    }

    @Override
    @Transactional
    public void create(List<SurveyPoint> surveyPoints) {

        for (SurveyPoint surveyPoint : surveyPoints) {
            surveyPoint.setProject(currentState.getCurrentProject());
            surveyPoint.setNameNumber(surveyPoint.getSurveyPointsType().getSurveyTypeShortName() + " " + surveyPoint.getPointNumber());
        }
        surveyPointRepository.saveAll(surveyPoints);
    }

    @Override
    @Transactional
    public void update(SurveyPoint surveyPoint) {
        surveyPointRepository.save(surveyPoint);
    }

    @Override
    @Transactional
    public void delete(List<SurveyPoint> surveyPoints) {
        surveyPointRepository.deleteAll(surveyPoints);
    }

    @Override
    public List<SurveyPoint> getByProject(Project project) {
        return surveyPointRepository.findByProject(project, Sort.by("id"));
    }

    @Override
    public List<SurveyPoint> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getBySurveyPoint"));
    }

    @Override
    public SurveyPoint findByPointNumberAndProject(String number, Project project) {
        return surveyPointRepository.findByPointNumberAndProject(number, project);
    }

    @Override
    public void delete(SurveyPointDto object) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("delete in SurveyPointService"));
    }

    @Override
    public void deleteByDto(SurveyPointDto dto) {

    }

    @Override
    public List<SurveyPointDto> getDtos(List<SurveyPoint> objects) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getDtos in SurveyPointService"));
    }

    @Override
    public void updateFromDtos(List<SurveyPoint> objects, List<SurveyPointDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("updateFromDtos"));
    }

    @Override
    public void updateFromDtos(List<SurveyPointDto> dtos) {

    }

    @Override
    public List<SurveyPointDto> getDtosByProject(Project project) {
        var surveyPoints = getByProject(project);

        return surveyPointMapper.surveyPointToSurveyPointDto(surveyPoints);
    }

    @Override
    public List<SurveyPointDto> getDtosBySurveyPointId(Long surveyPointId) {
        return null;
    }

    @Override
    public SurveyPointDto cloneDto(SurveyPointDto egeDto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("cloneDto"));
    }

    @Override
    public void enrichEntity(Long updatedEntityId, Long sourceEntityId) {

    }

    @Override
    public SurveyPoint getByPointNumber(String pointNumber, Project project) {
        return surveyPointRepository.findByPointNumberAndProject(pointNumber, project);
    }

    @Override
    public List<SurveyPoint> getAllByProject(Project project) {
        Sort sort = Sort.by("id");

        return surveyPointRepository.findByProject(project, sort);
    }
}
