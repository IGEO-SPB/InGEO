package org.geoproject.ingeo.utils;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class CurrentState {

    @Getter
    @Setter
    private Project currentProject;

    @Getter
    @Setter
    private SurveyPoint surveyPoint;

    @Getter
    @Setter
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
