package com.geoproject.igeo.utils;

import com.geoproject.igeo.models.Project;
import com.geoproject.igeo.models.Sample;
import com.geoproject.igeo.models.SurveyPoint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
