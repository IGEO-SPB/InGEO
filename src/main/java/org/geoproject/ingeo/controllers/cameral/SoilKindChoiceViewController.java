package com.geoproject.igeo.controllers.cameral;

import com.geoproject.igeo.dto.mainViewsDto.EgeDTO;
import com.geoproject.igeo.dto.mainViewsDto.ProjectDto;
import com.geoproject.igeo.models.classificators.kga.SoilClass;
import com.geoproject.igeo.services.classificators.kga.SoilClassService;
import com.geoproject.igeo.utils.CurrentState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.geoproject.igeo.constants.ServiceConstants.ZERO_INDEX;

@Log4j2
@Component
public class SoilKindChoiceViewController implements Initializable {

    protected final ConfigurableApplicationContext applicationContext;
    protected final CurrentState currentState;
    private final SoilClassService soilClassService;

    private EgeDTO egeDto;

    @FXML
    ChoiceBox<SoilClass> soilClassChoiceBox;

    public SoilKindChoiceViewController(ConfigurableApplicationContext applicationContext, CurrentState currentState,
                                        SoilClassService soilClassService) {
        this.applicationContext = applicationContext;
        this.currentState = currentState;
        this.soilClassService = soilClassService;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSoilClassChoiceBox();
        setDto();
    }

    public void setDto() {
        egeDto = new EgeDTO();
    }

    public void setSoilClassChoiceBox() {
        var soilClasses = soilClassService.getAll();

        ObservableList<SoilClass> items = FXCollections.observableArrayList(soilClasses);
        soilClassChoiceBox.getItems().addAll(items);

        soilClassChoiceBox.setConverter(new StringConverter<SoilClass>() {
            @Override
            public String toString(SoilClass object) {
                return object.getScName();
            }

            @Override
            public SoilClass fromString(String string) {
                return null;
            }
        });

        soilClassChoiceBox.setValue(soilClasses.get(ZERO_INDEX));
    }

    @FXML
    public void onSoilClassChoiceBoxClicked(ActionEvent event) {
        soilClassChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue))
            {
                System.out.println("newValue");
                System.out.println(newValue.getId());
                System.out.println(newValue.getScName());


//                setSoilKindChoiceBox();
            }});
    }




    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
//        saveEntity();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCancellButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
