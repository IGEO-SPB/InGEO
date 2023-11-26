package org.geoproject.ingeo;

import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.services.allProjects.ProjectsService;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;

@Log4j2
public class GeoJavaFXApp extends javafx.application.Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            CurrentState state = applicationContext.getBean(CurrentState.class);
            ProjectsService projectsService = applicationContext.getBean(ProjectsService.class);
            SurveyPointsService surveyPointsService = applicationContext.getBean(SurveyPointsService.class);
            SampleService sampleService = applicationContext.getBean(SampleService.class);

            var project = projectsService.getById(1L);
            log.info("project");
            log.info(project.getId());
            var surveyPoint = surveyPointsService.getByProject(project).get(0);
            log.info("surveyPoint");
            log.info(surveyPoint.getId());
            var sample = sampleService.getBySurveyPoint(surveyPoint, Sort.unsorted()).get(0);

            state.setCurrentProject(project);
            state.setSurveyPoint(surveyPoint);
            state.setSample(sample);
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
