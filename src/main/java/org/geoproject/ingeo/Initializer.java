package org.geoproject.ingeo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.utils.CurrentState;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

@Component
@RequiredArgsConstructor
@Log4j2
public class Initializer {

    private final SampleService sampleService;
    private final SurveyPointsService surveyPointsService;
    private final CurrentState currentState;

    public void setCurrentSurveyPoint(Project project) {
        var currentProjectSurveyPoints = surveyPointsService.getByProject(project);

        if (!currentProjectSurveyPoints.isEmpty()) {
            var surveyPoint = currentProjectSurveyPoints.get(ZERO_INDEX);

            log.info("Идентификатор текущей точки исследования: " + surveyPoint.getId());

            currentState.setSurveyPoint(surveyPoint);
        } else {
            currentState.setSurveyPoint(null);

            log.info("В базе данных нет сохраненных точек исследования");
        }
    }

    public void setCurrentSample(SurveyPoint surveyPoint) {
        var currentSurveyPointSamples = sampleService.getBySurveyPoint(surveyPoint, Sort.unsorted());

        if (!currentSurveyPointSamples.isEmpty()) {
            var sample = currentSurveyPointSamples.get(ZERO_INDEX);

            log.info("Идентификатор текущего образца: " + sample.getId());

            currentState.setSample(sample);
        } else {
            currentState.setSample(null);

            log.info("В базе данных нет сохраненных образцов");
        }
    }
}
