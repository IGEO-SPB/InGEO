package org.geoproject.ingeo.controllers.labor.waterChemistry;

import org.geoproject.ingeo.controllers.labor.AbstractLaborMethodTableController;
import org.geoproject.ingeo.dto.methodDtos.WaterSampleDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.dtoenums.WaterSampleDtoFieldsEnum;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.models.labor.WaterSample;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.services.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.JavaFXConstants.TEXTFIELD_NUMERIC_PATTERN;
import static org.geoproject.ingeo.enums.StageTitleEnum.DILUTION_FACTORS;

@Component
public class WaterChemistryLaborMethodTableController extends AbstractLaborMethodTableController<WaterSample, WaterSampleDto>
        implements Initializable {

    @FXML
    private TextField clCoef;

    @FXML
    private TableColumn<WaterSampleDto, String> blockedFromWaterSampleResult;

    public WaterChemistryLaborMethodTableController(ConfigurableApplicationContext applicationContext,
                                                    SampleService sampleService, SurveyPointsService surveyPointsService, CurrentState currentState,
                                                    TableService<WaterSample, WaterSampleDto> service) {
        super(applicationContext, sampleService, surveyPointsService, currentState, service);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        setClCoefTextField();
    }

    @Override
    public void setCellsFormat() {
        columnsMap.values().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView, observableList,
                null, null));

        columnsMap.forEach((columnName, column) -> column.setCellValueFactory(new PropertyValueFactory<>(columnName)));

        columnsMap.forEach((columnName, column) ->
                column.setOnEditCommit(event ->
                {
                    if (Boolean.FALSE.equals(event.getRowValue().getBlockedFromWaterSampleResult())) {
                        event.getRowValue()
                                .setFieldValue(WaterSampleDtoFieldsEnum.getEnumByName(columnName), event.getNewValue());
                    } else {
                        WaterSampleDto tempDto = event.getRowValue();

                        event.getTableView().getItems().set(event.getTablePosition().getRow(), tempDto);
                    }
                }));

        blockedFromWaterSampleResult.setCellValueFactory(data ->

        {
            boolean value = data.getValue().getBlockedFromWaterSampleResult();
            String stringValue = value ? "Да" : "Нет";
            return new SimpleStringProperty(stringValue);
        });

        tableView.setItems(observableList);
    }

    public void addNewRow() {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("addNewRow"));
    }

    private void setClCoefTextField() {
        dtos.forEach(dto -> clCoef.textProperty().bindBidirectional(dto.clCoefProperty(), JavaFXCommonMethods.getConverter()));
    }

    //region кнопки

    public void onClCoefButtonClicked() {
        if (clCoef.getText().matches(TEXTFIELD_NUMERIC_PATTERN)) {
            dtos.forEach(waterSampleDto -> waterSampleDto.setClCoef(Float.parseFloat(clCoef.getText())));
            service.updateFromDtos(dtos);
        }
    }

    public void onCalculateButtonClicked() {
        service.updateFromDtos(dtos);
        service.calculate(dtos);
    }

    @FXML
    public void onSetDilutionFactorsButtonClicked(ActionEvent event) throws IOException {
        DilutionFactorsModalWindowController childController = (DilutionFactorsModalWindowController) applicationContext.getBean("dilutionFactorsModalWindowController");

        childController.passWaterSampleDtos(dtos);
        childController.passObjects(objects);

        JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.DILUTION_FACTORS_VIEW.getPath(),
                applicationContext, stage, DILUTION_FACTORS.getTitle());
    }

    //endregion
}
