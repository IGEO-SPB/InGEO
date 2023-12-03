package org.geoproject.ingeo.utils;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CurrentState {

    private Project currentProject;

    private SurveyPoint surveyPoint;

    private Sample sample;

    @Override
    public String toString() {
        return "CurrentState{" +
                "currentProject=" + currentProject + "\n" +
                ", surveyPoint=" + surveyPoint + "\n" +
                ", sample=" + sample +
                '}';
    }

}
