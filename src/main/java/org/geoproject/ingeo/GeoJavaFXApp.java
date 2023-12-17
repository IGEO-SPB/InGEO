package org.geoproject.ingeo;

import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.services.allProjects.ProjectsService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Log4j2
public class GeoJavaFXApp extends javafx.application.Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            CurrentState state = applicationContext.getBean(CurrentState.class);
            ProjectsService projectsService = applicationContext.getBean(ProjectsService.class);

            Initializer initializer = applicationContext.getBean(Initializer.class);

            try {
                var project = projectsService.getAll().get(0);
                state.setCurrentProject(project);
                log.info("project");
                log.info(project.getId());

                initializer.setCurrentSurveyPoint(project);
                initializer.setCurrentSample(state.getSurveyPoint());
            } catch (NotFoundException e) {
                log.error(e.getMessage());
                state.setCurrentProject(null);
            }

        } catch(NoSuchBeanDefinitionException ignored){

            log.error("failed to set current state...");
        }
        JavaFXCommonMethods.changeScene(primaryStage, ViewsEnum.LOGIN_VIEW.getPath(), applicationContext, ViewsEnum.LOGIN_VIEW.getTitle());

        log.info("App starting...");

        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(InGeoApplication.class).run();
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
        Platform.exit();
    }
}
