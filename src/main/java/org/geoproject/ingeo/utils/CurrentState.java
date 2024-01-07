package org.geoproject.ingeo.utils;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import lombok.Getter;
import lombok.Setter;
import org.geoproject.ingeo.repositories.SurveyPointRepository;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class CurrentState {

    private final SurveyPointRepository surveyPointRepository;

    private Project currentProject;

    private SurveyPoint surveyPoint;

    private Long surveyPointId;

    private Sample sample;

    public Long getSurveyPointId() {
        return surveyPointId;
    }

    public void setSurveyPointId(Long surveyPointId) {
        this.surveyPointId = surveyPointId;
        var byId = surveyPointRepository.findById(surveyPointId)
                .orElseThrow(() -> new NotFoundException("CurrentState, setSurveyPointId(), sting 36"));

        setSurveyPoint(byId);
    }

    @Override
    public String toString() {
        return "CurrentState{" +
                "currentProject=" + currentProject + "\n" +
                ", surveyPoint=" + surveyPoint + "\n" +
                ", sample=" + sample +
                '}';
    }

}
