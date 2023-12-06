package org.geoproject.ingeo.utils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import lombok.Getter;
import lombok.Setter;
import org.geoproject.ingeo.repositories.ProjectsRepository;
import org.geoproject.ingeo.repositories.SamplesRepository;
import org.geoproject.ingeo.repositories.SurveyPointsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class CurrentState {
    @Getter
    @Setter
    private SurveyPoint surveyPoint;
    @Getter
    @Setter
    private Sample sample;
    private final ObjectProperty<Project> currentProject = new SimpleObjectProperty<>();

    @Override
    public String toString() {
        return "CurrentState{" +
                "currentProject=" + currentProject + "\n" +
                ", surveyPoint=" + surveyPoint + "\n" +
                ", sample=" + sample +
                '}';
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject.set(currentProject);
    }
    public Project getCurrentProject() {
        return this.currentProject.get();
    }
    public ObjectProperty<Project> getCurrentProjectProperty() {
        return currentProject;
    }
}
