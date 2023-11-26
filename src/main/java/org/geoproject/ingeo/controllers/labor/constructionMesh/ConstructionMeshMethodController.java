package org.geoproject.ingeo.controllers.laborMethods.constructionMesh;

import org.geoproject.ingeo.controllers.laborMethods.AbstractLaborMethodController;
import org.geoproject.ingeo.dto.ConstructionMeshDTO;
import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.laborenums.HCLStatusEnum;
import org.geoproject.ingeo.models.ConstructionMesh;
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
public class ConstructionMeshMethodController extends AbstractLaborMethodController<ConstructionMesh, ConstructionMeshDTO> implements Initializable {

    @FXML
    ChoiceBox<String> hClChoiceBox;

    @FXML
    private TextField totalSubsample;
    @FXML
    private TextField sieveDropperSubsample;
    @FXML
    private TextField potNumber;
    @FXML
    private TextField potMass;
    @FXML
    private TextField potMassWithSediment;
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
    private TextField mass_X_25_2;
    @FXML
    private TextField mass_X_2_125;
    @FXML
    private TextField mass_X_125_1;
    @FXML
    private TextField mass_X_1_063;
    @FXML
    private TextField mass_X_063_050;
    @FXML
    private TextField mass_X_050_0315;
    @FXML
    private TextField mass_X_0315_025;
    @FXML
    private TextField mass_X_025_016;
    @FXML
    private TextField mass_X_016_01;

    public ConstructionMeshMethodController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                            MethodViewService<ConstructionMesh, ConstructionMeshDTO> service, CurrentState currentState,
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
        allTextFieldMap.put(potNumber, dto.potNumberProperty());
        allTextFieldMap.put(potMass, dto.potMassProperty());
        allTextFieldMap.put(potMassWithSediment, dto.potMassWithSedimentProperty());
        allTextFieldMap.put(mass_X_more_70, dto.mass_X_more_70Property());
        allTextFieldMap.put(mass_X_70_40, dto.mass_X_70_40Property());
        allTextFieldMap.put(mass_X_40_20, dto.mass_X_40_20Property());
        allTextFieldMap.put(mass_X_20_10, dto.mass_X_20_10Property());
        allTextFieldMap.put(mass_X_10_5, dto.mass_X_10_5Property());
        allTextFieldMap.put(mass_X_5_25, dto.mass_X_5_25Property());
        allTextFieldMap.put(mass_X_25_2, dto.mass_X_25_2Property());
        allTextFieldMap.put(mass_X_2_125, dto.mass_X_2_125Property());
        allTextFieldMap.put(mass_X_125_1, dto.mass_X_125_1Property());
        allTextFieldMap.put(mass_X_1_063, dto.mass_X_1_063Property());
        allTextFieldMap.put(mass_X_063_050, dto.mass_X_063_050Property());
        allTextFieldMap.put(mass_X_050_0315, dto.mass_X_050_0315Property());
        allTextFieldMap.put(mass_X_0315_025, dto.mass_X_0315_025Property());
        allTextFieldMap.put(mass_X_025_016, dto.mass_X_025_016Property());
        allTextFieldMap.put(mass_X_016_01, dto.mass_X_016_01Property());

        allTextFieldMap.put(potMass, dto.potMassProperty());

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
        JavaFXCommonMethods.changeScene(event, ViewsEnum.CONSTRUCTION_MESH_SUMMARY_VIEW.getPath(),
                applicationContext, StageTitleEnum.CONSTRUCTION_MESH.getTitle());
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