package org.geoproject.ingeo.controllers.laborMethods.corrosion;

import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class SoilCorrosionHeaderController implements Initializable {

    protected final ConfigurableApplicationContext applicationContext;
    public void onInsertMeasurementsClicked(ActionEvent actionEvent) throws IOException {
        JavaFXCommonMethods.changeScene(actionEvent, ViewsEnum.SOIL_CORROSION_INPUT, applicationContext);
    }

    public void onInsertFinalClicked(ActionEvent actionEvent) throws IOException {
        JavaFXCommonMethods.changeScene(actionEvent, ViewsEnum.SOIL_CORROSION_RESULT, applicationContext);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
