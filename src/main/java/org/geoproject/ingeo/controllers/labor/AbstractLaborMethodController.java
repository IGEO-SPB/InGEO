package org.geoproject.ingeo.controllers.laborMethods;

import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.services.classificators.PotService;
import org.geoproject.ingeo.services.classificators.RingService;
import org.geoproject.ingeo.services.classificators.WeighingBottleService;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.methodViews.MethodViewService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.geoproject.ingeo.constants.ServiceConstants.IS_NOT_SAND;
import static org.geoproject.ingeo.constants.ServiceConstants.IS_SAND;
import static org.geoproject.ingeo.constants.ServiceConstants.LABOR_NUMBER_FIELD;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

@CommonsLog
public abstract class AbstractLaborMethodController<T, Y> {

    protected final ConfigurableApplicationContext applicationContext;
    protected final SampleService sampleService;
    protected final MethodViewService<T, Y> service;
    protected final CurrentState currentState;
    protected final WeighingBottleService weighingBottleService;
    protected final PotService potService;
    protected final RingService ringService;

    Scene scene;
    protected Stage stage;

    @FXML
    protected ChoiceBox<String> sampleChoiceBox;
    @FXML
    CheckBox sandCheckBox;

    protected Map<TextField, Property> allTextFieldMap;
    protected Map<TextField, Property> nonEditableTextFieldMap;

    protected List<Sample> samplesInCurrentSurveyPointList;
    protected List<String> sampleLaborNumberList;

    protected T object;
    protected Y dto;

    protected AbstractLaborMethodController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                            MethodViewService<T, Y> service, CurrentState currentState,
                                            WeighingBottleService weighingBottleService, PotService potService, RingService ringService) {
        this.applicationContext = applicationContext;
        this.sampleService = sampleService;
        this.service = service;
        this.currentState = currentState;
        this.weighingBottleService = weighingBottleService;
        this.potService = potService;
        this.ringService = ringService;
    }

    public void init() {
        initMaps();

        setFirstSample();

        setDTO();

        setTextFieldLists();

        setSampleChoiceBox();

        setSandCheckBox();

        setFieldBinding();
    }

    public void initMaps() {
        allTextFieldMap = new HashMap<>();
        nonEditableTextFieldMap = new HashMap<>();
    }

    protected void setFirstSample() {
        List<Sample> bySurveyPoint = sampleService.getBySurveyPoint(currentState.getSurveyPoint(), Sort.by(LABOR_NUMBER_FIELD));
        Sample sample = bySurveyPoint.get(ZERO_INDEX);

        currentState.setSample(sample);
    }

    public void setDTO() {
        Sample currentSample = currentState.getSample();

        object = service.getBySample(currentSample);
        dto = service.getDto(object);
    }

    public abstract void setTextFieldLists();

    public void setFieldBinding() {
        JavaFXCommonMethods.setFieldBinding(allTextFieldMap);
    }

    public void updateEntity() {
        service.updateFromDTO(dto, currentState.getSample());
    }

    @FXML
    protected void calculate() {
        service.calculate(currentState.getSample(), dto);
    }

    //region Сеттеры чойсбоксов и чекбоксов
    @FXML
    public void setSampleChoiceBox() {
        samplesInCurrentSurveyPointList = sampleService.getBySurveyPoint(currentState.getSurveyPoint(), Sort.by(LABOR_NUMBER_FIELD));
        sampleLaborNumberList = samplesInCurrentSurveyPointList.stream().map(Sample::getLaborNumber).toList();
        ObservableList<String> sampleList = FXCollections.observableArrayList(sampleLaborNumberList);
        sampleChoiceBox.getItems().addAll(sampleList);
        sampleChoiceBox.setValue(currentState.getSample().getLaborNumber());
    }

    @FXML
    public void setSandCheckBox() {
        Sample currentSample = currentState.getSample();

        if (currentSample.getIsSand()) {
            sandCheckBox.setSelected(IS_SAND);
        } else {
            sandCheckBox.setSelected(IS_NOT_SAND);
        }

        sandCheckBox.setOnAction(event -> {
            if (sandCheckBox.isSelected()) {
                currentSample.setIsSand(IS_SAND);
            } else {
                currentSample.setIsSand(IS_NOT_SAND);
            }
            sampleService.update(currentSample);

            updateEntity();
        });
    }
    //endregion

    //region Кнопки
    @FXML
    public void onChoiceBoxClicked(ActionEvent event) {
        sampleChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            Sample byLaborNumber = sampleService.getByLaborNumber(newValue);

            System.out.println("And here current sample: " + byLaborNumber.getLaborNumber() + " | " + newValue);

            currentState.setSample(byLaborNumber);
            setDTO();
            setTextFieldLists();

            setSandCheckBox();
            setFieldBinding();
        });
    }

    //TODO: оптимизировать методы переключения по образцам
    @FXML
    public void onPreviousSampleButtonClick(ActionEvent event) {
        System.out.println("Prev sample button clicked");

        int indexOfCurrentSample = sampleLaborNumberList.indexOf(currentState.getSample().getLaborNumber());

        int prevIndex;

        if (indexOfCurrentSample > 0) {
            prevIndex = indexOfCurrentSample - 1;
        } else {
            prevIndex = samplesInCurrentSurveyPointList.size() - 1;
        }

        Sample nextSample = samplesInCurrentSurveyPointList.get(prevIndex);

        currentState.setSample(nextSample);
        sampleChoiceBox.setValue(currentState.getSample().getLaborNumber());

        setDTO();

        setTextFieldLists();

        setSandCheckBox();
        setFieldBinding();
    }

    @FXML
    public void onNextSampleButtonClick(ActionEvent event) {
        System.out.println("Next sample button clicked");

        int indexOfCurrentSample = sampleLaborNumberList.indexOf(currentState.getSample().getLaborNumber());

        int nextIndex;

        if (indexOfCurrentSample < samplesInCurrentSurveyPointList.size() - 1) {
            nextIndex = indexOfCurrentSample + 1;
        } else {
            nextIndex = 0;
        }

        Sample nextSample = samplesInCurrentSurveyPointList.get(nextIndex);
        currentState.setSample(nextSample);
        sampleChoiceBox.setValue(currentState.getSample().getLaborNumber());

        setDTO();

        setTextFieldLists();

        setSandCheckBox();
        setFieldBinding();
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
    //endregion
}