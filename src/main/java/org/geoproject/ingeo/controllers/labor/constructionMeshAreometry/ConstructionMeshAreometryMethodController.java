package org.geoproject.ingeo.controllers.laborMethods.constructionMeshAreometry;

import org.geoproject.ingeo.controllers.laborMethods.AbstractLaborMethodController;
import org.geoproject.ingeo.dto.ConstructionMeshAreometryDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.laborenums.HCLStatusEnum;
import org.geoproject.ingeo.models.ConstructionMeshAreometry;
import org.geoproject.ingeo.services.classificators.PotService;
import org.geoproject.ingeo.services.classificators.RingService;
import org.geoproject.ingeo.services.classificators.WeighingBottleService;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.methodViews.MethodViewService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.enums.StageTitleEnum.POTS;
import static org.geoproject.ingeo.enums.ViewsEnum.POTS_VIEW;

@Component
public class ConstructionMeshAreometryMethodController extends AbstractLaborMethodController<ConstructionMeshAreometry, ConstructionMeshAreometryDto> implements Initializable {

    @FXML
    ChoiceBox<String> hClChoiceBox;

    @FXML
    private TextField totalSubsample;
    @FXML
    private TextField sieveDropperSubsample;
    @FXML
    private TextField mass_X_more_70;
    @FXML
    private TextField mass_X_70_40;
    @FXML
    private TextField mass_X_40_20;
    @FXML
    private TextField mass_X_20_10;
    @FXML
    private TextField mass_X_10_5;
    @FXML
    private TextField mass_X_5_25;
    @FXML
    private TextField mass_X_25_125;
    @FXML
    private TextField mass_X_125_063;
    @FXML
    private TextField mass_X_063_0315;
    @FXML
    private TextField mass_X_0315_016;
    @FXML
    private TextField mass_X_016_01;
    @FXML
    private TextField particleDensity;
    @FXML
    private TextField drySoilSubsample;
    @FXML
    private TextField reading;
    @FXML
    private TextField correction;

    public ConstructionMeshAreometryMethodController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                                     MethodViewService<ConstructionMeshAreometry, ConstructionMeshAreometryDto> service, CurrentState currentState,
                                                     WeighingBottleService weighingBottleService, PotService potService,
                                                     RingService ringService) {
        super(applicationContext, sampleService, service, currentState, weighingBottleService, potService, ringService);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        setHClChoiceBox();
    }

    //инициализация списков и полей
    @Override
    public void setTextFieldLists() {
        allTextFieldMap.put(totalSubsample, dto.totalSubsampleProperty());
        allTextFieldMap.put(sieveDropperSubsample, dto.sieveDropperSubsampleProperty());
        allTextFieldMap.put(mass_X_more_70, dto.mass_X_more_70Property());
        allTextFieldMap.put(mass_X_70_40, dto.mass_X_70_40Property());
        allTextFieldMap.put(mass_X_40_20, dto.mass_X_40_20Property());
        allTextFieldMap.put(mass_X_20_10, dto.mass_X_20_10Property());
        allTextFieldMap.put(mass_X_10_5, dto.mass_X_10_5Property());
        allTextFieldMap.put(mass_X_5_25, dto.mass_X_5_25Property());
        allTextFieldMap.put(mass_X_25_125, dto.mass_X_25_125Property());
        allTextFieldMap.put(mass_X_125_063, dto.mass_X_125_063Property());
        allTextFieldMap.put(mass_X_063_0315, dto.mass_X_063_0315Property());
        allTextFieldMap.put(mass_X_0315_016, dto.mass_X_0315_016Property());
        allTextFieldMap.put(mass_X_016_01, dto.mass_X_016_01Property());
        allTextFieldMap.put(particleDensity, dto.particleDensityProperty());
        allTextFieldMap.put(drySoilSubsample, dto.drySoilSubsampleProperty());
        allTextFieldMap.put(reading, dto.readingProperty());
        allTextFieldMap.put(correction, dto.correctionProperty());

        JavaFXCommonMethods.setTextFieldProperties(allTextFieldMap, nonEditableTextFieldMap,
                potService, this::updateEntity);
    }

    //кнопки
    @FXML
    public void onSetPotButtonClick(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeSceneToModalWindow(event, POTS_VIEW.getPath(), applicationContext, stage, POTS.getTitle());
    }

    @FXML
    public void onCalculateConstructionMeshButtonClick() {
        calculate();
    }

    @FXML
    public void onShowResultTableButtonClick(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.CONSTRUCTION_MESH_AREOMETRY_RESULT_VIEW.getPath(),
                applicationContext, ViewsEnum.CONSTRUCTION_MESH_AREOMETRY_RESULT_VIEW.getTitle());
    }

    //чекбоксы, чойсбоксы

    @FXML
    public void setHClChoiceBox() {
        ObservableList<String> hClChooseList = FXCollections.observableArrayList(HCLStatusEnum.getStatuses());
        hClChoiceBox.getItems().addAll(hClChooseList);
        hClChoiceBox.setValue(object.getHCl());
    }

    @FXML
    public void onHClChoiceBoxClicked(ActionEvent event) {
        hClChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            dto.setHCl(newValue);
            object.setHCl(newValue);

            service.update(object);
        });
    }
}
