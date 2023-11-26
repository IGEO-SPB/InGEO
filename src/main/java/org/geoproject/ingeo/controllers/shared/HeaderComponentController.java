package com.geoproject.igeo.controllers.shared;

import com.geoproject.igeo.enums.StageTitleEnum;
import com.geoproject.igeo.enums.ViewsEnum;
import com.geoproject.igeo.utils.JavaFXCommonMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@AllArgsConstructor
public class HeaderComponentController implements Initializable {

    private ConfigurableApplicationContext applicationContext;

    @FXML
    public void onAllProjectsButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.tryChangeScene(event, ViewsEnum.ALL_PROJECTS_VIEW.getPath(),
                applicationContext, StageTitleEnum.PROJECTS.getTitle());
    }

    @FXML
    public void onFieldModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.FIELD_MODULE_MAIN_VIEW.getPath(),
                applicationContext, StageTitleEnum.FIELD_MODULE.getTitle());
    }

    @FXML
    public void onLaborModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.LABOR_MODULE_MAIN_VIEW.getPath(),
                applicationContext, StageTitleEnum.LABOR_MODULE.getTitle());
    }

    @FXML
    public void onCameralModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.CAMERAL_MODULE_MAIN_VIEW.getPath(),
                applicationContext, StageTitleEnum.CAMERAL_MODULE.getTitle());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
