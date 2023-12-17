package org.geoproject.ingeo.services.common.impl;

import org.geoproject.ingeo.dto.SurveyPointDTO;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.SurveyPointMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.SurveyPointsRepository;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.geoproject.ingeo.constants.ServiceConstants.SPACE_PATTERN;

@Service
@RequiredArgsConstructor
public class SurveyPointsServiceImpl implements SurveyPointsService {

    private final SurveyPointsRepository surveyPointsRepository;
    private final SurveyPointMapper surveyPointMapper;
    private final CurrentState currentState;

    @Override
    public SurveyPoint getById(Long id) {
        return surveyPointsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SURVEY_POINT_NOT_FOUND_EXCEPTION.getExceptionMessage("SurveyPoint")));
    }

    public List<SurveyPoint> getAll() {
        return surveyPointsRepository.findAll();
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
        surveyPointsRepository.saveAll(surveyPoints);
    }

    @Override
    @Transactional
    public void update(SurveyPoint surveyPoint) {
        surveyPointsRepository.save(surveyPoint);
    }

    @Override
    @Transactional
    public void delete(List<SurveyPoint> surveyPoints) {
        surveyPointsRepository.deleteAll(surveyPoints);
    }

    @Override
    public List<SurveyPoint> getByProject(Project project) {
        return surveyPointsRepository.findByProject(project, Sort.by("id"));
    }

    @Override
    public List<SurveyPoint> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getBySurveyPoint"));
    }

    @Override
    public SurveyPoint findByPointNumberAndProject(String number, Project project) {
        return surveyPointsRepository.findByPointNumberAndProject(number, project);
    }

    @Override
    public void delete(SurveyPointDTO object) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("delete in SurveyPointService"));
    }

    @Override
    public void deleteByDto(SurveyPointDTO dto) {

    }

    @Override
    public List<SurveyPointDTO> getDtos(List<SurveyPoint> objects) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getDtos in SurveyPointService"));
    }

    @Override
    public void updateFromDtos(List<SurveyPoint> objects, List<SurveyPointDTO> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("updateFromDtos"));
    }

    @Override
    public void updateFromDtos(List<SurveyPointDTO> dtos) {

    }

    @Override
    public List<SurveyPointDTO> getDtosByProject(Project project) {
        return null;
    }

    @Override
    public SurveyPointDTO cloneDto(SurveyPointDTO egeDto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("cloneDto"));
    }

    @Override
    public SurveyPoint getByPointNumber(String pointNumber, Project project) {
        return surveyPointsRepository.findByPointNumberAndProject(pointNumber, project);
    }

    @Override
    public List<SurveyPoint> getAllByProject(Project project) {
        Sort sort = Sort.by("id");

        return surveyPointsRepository.findByProject(project, sort);
    }
}
