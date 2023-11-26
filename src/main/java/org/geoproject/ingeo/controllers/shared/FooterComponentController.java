package org.geoproject.ingeo.controllers.shared;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class FooterComponentController implements Initializable {

    @FXML
    private Label projectCipherInFooter;

    @FXML
    private Label projectNameInFooter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
