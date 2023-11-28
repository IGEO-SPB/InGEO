package org.geoproject.ingeo.controllers.cameral;

import javafx.scene.control.TextArea;
import org.apache.commons.lang.StringUtils;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.services.cameral.EgeServise;
import org.geoproject.ingeo.services.classificators.kga.SoilClassKindGroupService;
import org.geoproject.ingeo.services.classificators.kga.SoilClassService;
import org.geoproject.ingeo.services.classificators.kga.SoilKindService;
import org.geoproject.ingeo.utils.CurrentState;
import javafx.collections.FXCollections;
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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

@Log4j2
@Component
public class SoilKindChoiceViewController implements Initializable {

    protected final ConfigurableApplicationContext applicationContext;
    protected final CurrentState currentState;
    private final SoilClassService soilClassService;
    private final SoilKindService soilKindService;
    private final SoilClassKindGroupService soilClassKindGroupService;
    private final EgeServise egeServise;

    private Ege ege;
    private EgeDto egeDto;

    List<SoilClass> soilClasses;
    List<SoilClassKindGroup> soilGroups;

    @FXML
    TextArea descriptionCredoFormular;
    @FXML
    ChoiceBox<SoilClass> soilClassChoiceBox;
    @FXML
    ChoiceBox<SoilClassKindGroup> soilKindGroupChoiceBox;

    public SoilKindChoiceViewController(ConfigurableApplicationContext applicationContext, CurrentState currentState,
                                        SoilClassService soilClassService, SoilKindService soilKindService, SoilClassKindGroupService soilClassKindGroupService, EgeServise egeServise) {
        this.applicationContext = applicationContext;
        this.currentState = currentState;
        this.soilClassService = soilClassService;
        this.soilKindService = soilKindService;
        this.soilClassKindGroupService = soilClassKindGroupService;
        this.egeServise = egeServise;
    }

    public void passEge(Ege ege) {
        this.ege = ege;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDto();
        setDescriptionCredoFormularTextArea();
        setSoilClassChoiceBox();
        setSoilKindGroupChoiceBox();
    }

    public void setDto() {
        egeDto = egeServise.getDto(ege);
    }

    public void setSoilClassChoiceBox() {
        soilClassChoiceBox.setConverter(new StringConverter<SoilClass>() {
            @Override
            public String toString(SoilClass object) {
                return Objects.nonNull(object) ? object.getScName() : StringUtils.EMPTY;
            }

            @Override
            public SoilClass fromString(String string) {
                return null;
            }
        });

        fillSoilClassChoiceBox();
        fillSoilKindGroupChoiceBox(soilClasses.get(ZERO_INDEX));

        soilClassChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue))
            {
                fillSoilKindGroupChoiceBox(newValue);
                soilKindGroupChoiceBox.setValue(soilGroups.get(ZERO_INDEX));

//                egeDto.setSoilClass(newValue);
            }});
    }

    public void setSoilKindGroupChoiceBox() {
        soilKindGroupChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(SoilClassKindGroup object) {
                return Objects.nonNull(object) ? object.getSoilKindGroup() : StringUtils.EMPTY;
            }

            @Override
            public SoilClassKindGroup fromString(String string) {
                return null;
            }
        });

        soilKindGroupChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue))
            {
                setSoilKindTableView(newValue);
            }});
    }

    public void fillSoilClassChoiceBox() {
        soilClassChoiceBox.getItems().clear();

        soilClasses = soilClassService.getAll();
        var items = FXCollections.observableArrayList(soilClasses);
        soilClassChoiceBox.getItems().addAll(items);

        soilClassChoiceBox.setValue(soilClasses.get(ZERO_INDEX));
    }

    public void fillSoilKindGroupChoiceBox(SoilClass currentSoilClass) {
        soilKindGroupChoiceBox.getItems().clear();

        soilGroups = soilClassKindGroupService.getBySoilClass(currentSoilClass);
        var items = FXCollections.observableArrayList(soilGroups);
        soilKindGroupChoiceBox.getItems().addAll(items);

        soilKindGroupChoiceBox.setValue(soilGroups.get(ZERO_INDEX));
    }

    public void setDescriptionCredoFormularTextArea() {
        descriptionCredoFormular.setText(egeDto.getDescriptionCredoFormular());
    }

    @FXML
    public void onSoilClassChoiceBoxClicked(ActionEvent event) {

    }

    @FXML
    public void onSoilKindGroupChoiceBoxClicked(ActionEvent event) {

    }

    private void setSoilKindTableView(SoilClassKindGroup newValue) {
        log.info("setSoilKindTableView method called...");
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
