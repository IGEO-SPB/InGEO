package org.geoproject.ingeo.controllers.shared;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.AllArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class HeaderComponentController implements Initializable {

    private final ConfigurableApplicationContext applicationContext;
    private final CurrentState state;

    @FXML
    public Button allProjectsButton;
    @FXML
    public SplitMenuButton fieldModuleButton;
    @FXML
    public SplitMenuButton laborModuleButton;
    @FXML
    public SplitMenuButton cameralModuleButton;

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
        var prop = state.getCurrentProjectProperty();
        prop.addListener((observableValue, project, t1) -> {
            updateButtonDisabledStatus(t1);
        });
        updateButtonDisabledStatus(state.getCurrentProject());
    }

    private void updateButtonDisabledStatus(Project project) {
        boolean buttonsState = project == null;
        fieldModuleButton.setDisable(buttonsState);
        cameralModuleButton.setDisable(buttonsState);
        laborModuleButton.setDisable(buttonsState);
    }
}
