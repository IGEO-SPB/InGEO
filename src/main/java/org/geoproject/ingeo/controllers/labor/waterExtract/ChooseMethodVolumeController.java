package org.geoproject.ingeo.controllers.laborMethods.waterExtract;

import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ChooseMethodVolumeController {

    private final ConfigurableApplicationContext applicationContext;

    @FXML
    protected void onPartialWaterExtractButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_PARTIAL_DATA_VIEW.getPath(),
                applicationContext, StageTitleEnum.WATER_EXTRACT_PARTIAL_DATA.getTitle());
    }

    @FXML
    protected void onFullWaterExtractButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_FULL_DATA_VIEW.getPath(),
                applicationContext, StageTitleEnum.WATER_EXTRACT_FULL_DATA.getTitle());
    }

    @FXML
    public void onAllProjectsButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.ALL_PROJECTS_VIEW.getPath(),
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
}