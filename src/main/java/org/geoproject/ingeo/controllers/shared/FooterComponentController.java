package org.geoproject.ingeo.controllers.shared;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.utils.CurrentState;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class FooterComponentController implements Initializable {

    private final CurrentState state;

    @FXML
    private Label projectCipherInFooter;

    @FXML
    private Label projectNameInFooter;

    private void updateFooter(Project project) {
        String projectNameInFooterText = project == null ? "Проект не выбран" : project.getProjectName();
        String projectCipherInFooterText = project == null ? "Проект не выбран" : project.getContractNumber();
        projectNameInFooter.setText(projectNameInFooterText);
        projectCipherInFooter.setText(projectCipherInFooterText);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        state.getCurrentProjectProperty().addListener((observableValue, project, t1) -> {
            updateFooter(t1);
        });
        updateFooter(state.getCurrentProject());
    }
}
