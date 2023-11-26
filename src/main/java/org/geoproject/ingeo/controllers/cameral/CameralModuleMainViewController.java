package org.geoproject.ingeo.controllers.cameral;

import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Log4j2
@Component
public class CameralModuleMainViewController implements Initializable {

    protected final ConfigurableApplicationContext applicationContext;

    public CameralModuleMainViewController(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onAllProjectsButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.ALL_PROJECTS_VIEW.getPath(),
                applicationContext, ViewsEnum.ALL_PROJECTS_VIEW.getTitle());
    }

    @FXML
    public void onFieldModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.FIELD_MODULE_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.FIELD_MODULE_MAIN_VIEW.getTitle());
    }

    @FXML
    public void onLaborModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.LABOR_MODULE_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.LABOR_MODULE_MAIN_VIEW.getTitle());
    }

    @FXML
    public void onCameralModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.CAMERAL_MODULE_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.CAMERAL_MODULE_MAIN_VIEW.getTitle());
    }

    @FXML
    public void onCameralModuleMainViewButtonClicked(ActionEvent event) throws IOException {
        log.info("onCameralModuleMainViewButtonClicked clicked...");
    }

    @FXML
    public void onPumpButtonClicked(ActionEvent event) throws IOException {
        log.info("onPumpButtonClicked clicked...");
        JavaFXCommonMethods.changeScene(event, ViewsEnum.EGE_LIST_VIEW.getPath(),
                applicationContext, ViewsEnum.EGE_LIST_VIEW.getTitle());
    }

    @FXML
    public void onStatisticalProcesssingButtonClicked(ActionEvent event) throws IOException {
        log.info("onStatisticalProcesssingButtonClicked clicked...");
    }

}
