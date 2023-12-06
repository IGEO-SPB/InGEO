package org.geoproject.ingeo.controllers;

import lombok.extern.log4j.Log4j2;
import org.geoproject.ingeo.Initializer;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.repositories.ProjectsRepository;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
@RequiredArgsConstructor
public class LoginController {
    private final ConfigurableApplicationContext applicationContext;
    private final CurrentState state;
    private final ProjectsRepository projectsRepository;
    private final Initializer initializer;

    @FXML
    protected void onLoginButtonClicked(ActionEvent event) throws IOException {
        var nextView = ViewsEnum.ALL_PROJECTS_VIEW;
        var project = projectsRepository.findFirstBy(Sort.by("id"));
        state.setCurrentProject(project);
        initializer.setCurrentSurveyPoint(project);
        initializer.EnsureConstructionTypesExist();
        initializer.EnsureSurveyPointsTypeExist();
        initializer.setCurrentSample(state.getSurveyPoint());
        log.info("App starting...");

        JavaFXCommonMethods.changeScene(event, nextView, applicationContext);
    }
}