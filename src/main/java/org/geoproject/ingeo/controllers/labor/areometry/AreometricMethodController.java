package org.geoproject.ingeo.controllers.labor.areometry;

import org.geoproject.ingeo.controllers.labor.AbstractLaborMethodController;
import org.geoproject.ingeo.dto.methodDtos.AreometryDTO;
import org.geoproject.ingeo.models.labor.Areometry;
import org.geoproject.ingeo.services.classificators.PotService;
import org.geoproject.ingeo.services.classificators.RingService;
import org.geoproject.ingeo.services.classificators.WeighingBottleService;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.labor.MethodViewService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.enums.StageTitleEnum.GRAN_COMPOSITION_AREOMETRY;
import static org.geoproject.ingeo.enums.StageTitleEnum.WEIGHING_BOTTLES;
import static org.geoproject.ingeo.enums.ViewsEnum.GRAN_COMPOSITION_RESULT_TABLE_VIEW;
import static org.geoproject.ingeo.enums.ViewsEnum.WEIGHING_BOTTLES_VIEW;

@Component
public class AreometricMethodController extends AbstractLaborMethodController<Areometry, AreometryDTO> implements Initializable {

    @FXML
    private TextField particleDensity;
    @FXML
    private TextField totalSubsample;
    @FXML
    private TextField undisturbedSampleWaterContent;
    @FXML
    private TextField subsampleWetSoil;

    @FXML
    private TextField weighingBottleNumber;
    @FXML
    private TextField emptyWeighingBottleMass;
    @FXML
    private TextField weighingBottleWithWetSoilMass;
    @FXML
    private TextField weighingBottleWithDrySoilMass;

    @FXML
    private TextField particleMassOver10mm;
    @FXML
    private TextField particleMassBetween10and5mm;
    @FXML
    private TextField particleMassBetween5and2mm;
    @FXML
    private TextField particleMassBetween2and1mm;
    @FXML
    private TextField particleMassBetween1and05mm;
    @FXML
    private TextField particleMassBetween05and025mm;
    @FXML
    private TextField particleMassBetween025and01mm;
    @FXML
    private TextField totalReadingForParticleSizeBetween005and001;
    @FXML
    private TextField totalReadingForParticleSizeBetween001and0002;
    @FXML
    private TextField totalReadingForParticleSizeBetweenLess0002;
    @FXML
    private TextField firstAmendment;
    @FXML
    private TextField secondAmendment;
    @FXML
    private TextField fourthAmendment;

    public AreometricMethodController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                      MethodViewService<Areometry, AreometryDTO> service, CurrentState currentState,
                                      WeighingBottleService weighingBottleService, PotService potService,
                                      RingService ringService) {
        super(applicationContext, sampleService, service, currentState, weighingBottleService, potService, ringService);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    @Override
    public void setDTO() {
        super.setDTO();

        dto.setStructure(currentState.getSample().getStructure());
    }

    @Override
    public void setTextFieldLists() {
        allTextFieldMap.put(particleDensity, dto.particleDensityProperty());
        allTextFieldMap.put(totalSubsample, dto.totalSubsampleProperty());
        allTextFieldMap.put(undisturbedSampleWaterContent, dto.undisturbedSampleWaterContentProperty());
        allTextFieldMap.put(subsampleWetSoil, dto.subsampleWetSoilProperty());
        allTextFieldMap.put(weighingBottleNumber, dto.weighingBottleNumberProperty());

        allTextFieldMap.put(emptyWeighingBottleMass, dto.emptyWeighingBottleMassProperty());
        allTextFieldMap.put(weighingBottleWithWetSoilMass, dto.weighingBottleWithWetSoilMassProperty());
        allTextFieldMap.put(weighingBottleWithDrySoilMass, dto.weighingBottleWithDrySoilMassProperty());
        allTextFieldMap.put(particleMassOver10mm, dto.particleMassOver10mmProperty());
        allTextFieldMap.put(particleMassBetween10and5mm, dto.particleMassBetween10and5mmProperty());
        allTextFieldMap.put(particleMassBetween5and2mm, dto.particleMassBetween5and2mmProperty());
        allTextFieldMap.put(particleMassBetween2and1mm, dto.particleMassBetween2and1mmProperty());
        allTextFieldMap.put(particleMassBetween1and05mm, dto.particleMassBetween1and05mmProperty());
        allTextFieldMap.put(particleMassBetween05and025mm, dto.particleMassBetween05and025mmProperty());
        allTextFieldMap.put(particleMassBetween025and01mm, dto.particleMassBetween025and01mmProperty());

        allTextFieldMap.put(totalReadingForParticleSizeBetween005and001, dto.totalReadingForParticleSizeBetween005and001Property());
        allTextFieldMap.put(totalReadingForParticleSizeBetween001and0002, dto.totalReadingForParticleSizeBetween001and0002Property());
        allTextFieldMap.put(totalReadingForParticleSizeBetweenLess0002, dto.totalReadingForParticleSizeBetweenLess0002Property());
        allTextFieldMap.put(firstAmendment, dto.firstAmendmentProperty());
        allTextFieldMap.put(secondAmendment, dto.secondAmendmentProperty());
        allTextFieldMap.put(fourthAmendment, dto.fourthAmendmentProperty());

//        nonEditableTextFieldMap.put();
        JavaFXCommonMethods.setTextFieldProperties(allTextFieldMap, nonEditableTextFieldMap,
                weighingBottleService, this::updateEntity);
    }

    //кнопки, чойсбоксы

//    @Override
//    public void onChoiceBoxClicked(ActionEvent event) {
//        super.onChoiceBoxClicked(event);
//    }

    @FXML
    public void onSetWeighingBottleButtonClick(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeSceneToModalWindow(event, WEIGHING_BOTTLES_VIEW.getPath(),
                applicationContext, stage, WEIGHING_BOTTLES.getTitle());
    }

    @FXML
    public void onDensityCopyButtonClick() {
        object.setParticleDensity(currentState.getSample().getAverageDensity());
        dto.setParticleDensity(currentState.getSample().getAverageDensity());

        service.update(object);
    }

    @FXML
    public void onCalculateGranCompositionButtonClick() {
        calculate();
    }

    @FXML
    public void onShowGranCompositionButtonClick(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, GRAN_COMPOSITION_RESULT_TABLE_VIEW.getPath(), applicationContext,
                GRAN_COMPOSITION_AREOMETRY.getTitle());
    }
}