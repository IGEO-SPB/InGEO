package org.geoproject.ingeo.controllers.laborMethods.waterChemistry;

import org.geoproject.ingeo.controllers.laborMethods.AbstractLaborMethodTableController;
import org.geoproject.ingeo.dto.WaterSampleResultDto;
import org.geoproject.ingeo.enums.dtoenums.WaterSampleResultDtoFieldsEnum;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.models.WaterSampleResult;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.mainViews.SurveyPointsService;
import org.geoproject.ingeo.services.tableViews.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WaterChemistryResultTableControllerLabor extends AbstractLaborMethodTableController<WaterSampleResult, WaterSampleResultDto>
        implements Initializable {

    @FXML
    private TableColumn<WaterSampleResultDto, Boolean> isBlocked;

    public WaterChemistryResultTableControllerLabor(ConfigurableApplicationContext applicationContext,
                                                    SampleService sampleService, SurveyPointsService surveyPointsService, CurrentState currentState,
                                                    TableService<WaterSampleResult, WaterSampleResultDto> service) {
        super(applicationContext, sampleService, surveyPointsService, currentState, service);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    @Override
    public void setCellsFormat() {
        columnsMap.values().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView, observableList,
                null, null));

        columnsMap.forEach((columnName, column) -> {
            if (columnName.equals(WaterSampleResultDtoFieldsEnum.IS_BLOCKED.getName())) {
                isBlocked.setCellFactory(cellValue -> new CheckBoxTableCell<>());
                isBlocked.setCellValueFactory(cellData -> {
                    WaterSampleResultDto waterSampleResultDto = cellData.getValue();
                    BooleanProperty property = new ReadOnlyBooleanWrapper(waterSampleResultDto.getIsBlocked());

                    property.addListener((observable, oldValue, newValue) -> {
                        waterSampleResultDto.setIsBlockedTransient(new SimpleBooleanProperty(newValue));
                        waterSampleResultDto.setIsBlocked(newValue);
                    });

                    return property;
                });
            } else {
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        columnsMap.forEach((columnName, column) ->
                column.setOnEditCommit(event ->
                        event.getRowValue()
                                .setFieldValue(WaterSampleResultDtoFieldsEnum.getEnumByName(columnName), event.getNewValue())
                ));

        tableView.setItems(observableList);
    }

    public void addNewRow() {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("addNewRow"));
    }

    //region кнопки

    public void onAdditionalCalculatingButtonClicked() {
        service.calculate(dtos);
        //TODO: попробовать с refresh()
        setCellsFormat();
    }

    @Override
    @FXML
    public void onWaterChemistryFinalResultViewButtonClicked(ActionEvent event) throws IOException {
        super.onWaterChemistryFinalResultViewButtonClicked(event);
        service.calculate(dtos);
    }
    //endregion
}
