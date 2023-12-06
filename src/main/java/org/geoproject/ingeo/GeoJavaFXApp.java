package org.geoproject.ingeo;

import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.repositories.classificators.ConstructionTypeRepository;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Log4j2
public class GeoJavaFXApp extends javafx.application.Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage primaryStage) throws Exception {
        JavaFXCommonMethods.changeScene(primaryStage, ViewsEnum.LOGIN_VIEW, applicationContext);
        log.info("Showing primary stage...");
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
