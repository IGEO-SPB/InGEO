package org.geoproject.ingeo.controllers.labor.waterExtract;

import org.geoproject.ingeo.controllers.labor.AbstractLaborMethodTableController;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialResultDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.dtoenums.WaterExtractPartialResultDtoFieldsEnum;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.models.labor.WaterExtractPartialResult;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.services.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.JavaFXConstants.FLOAT_ROUND_VIEW_PATTERN;
import static org.geoproject.ingeo.constants.LaborMethodConstants.TRACES;
import static org.geoproject.ingeo.constants.LaborMethodConstants.WATER_EXTRACT_PARTIAL_RESULT_FE_COLUMN;
import static org.geoproject.ingeo.constants.LaborMethodConstants.WATER_EXTRACT_PARTIAL_RESULT_NO3_COLUMN;
import static org.geoproject.ingeo.constants.ServiceConstants.SCIENTIFIC_NOTATION_PATTERN;
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_EXTRACT_CHOOSE_METHOD_VOLUME;
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_EXTRACT_PARTIAL_DATA;

@Component
public class WaterExtractPartialResultTableControllerLabor extends AbstractLaborMethodTableController<WaterExtractPartialResult, WaterExtractPartialResultDto>
        implements Initializable {

    @FXML
    private TableColumn<WaterExtractPartialResultDto, Boolean> isBlocked;


    public WaterExtractPartialResultTableControllerLabor(ConfigurableApplicationContext applicationContext,
                                                         SampleService sampleService, SurveyPointsService surveyPointsService, CurrentState currentState,
                                                         TableService<WaterExtractPartialResult, WaterExtractPartialResultDto> service) {
        super(applicationContext, sampleService, surveyPointsService, currentState, service);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    @Override
    public void setCellsFormat() {
        columnsMap.values().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView,
                observableList, null, null));

        var correctionZeroColumns = new ArrayList<>(Arrays.asList(WATER_EXTRACT_PARTIAL_RESULT_NO3_COLUMN, WATER_EXTRACT_PARTIAL_RESULT_FE_COLUMN));

        columnsMap.forEach((columnName, column) -> {

            if (correctionZeroColumns.contains(columnName)) {
                getZeroCorrection(columnName, (TableColumn<WaterExtractPartialResultDto, String>) column);
            } else if (columnName.equals(WaterExtractPartialResultDtoFieldsEnum.IS_BLOCKED.getName())) {
                setIsBlockedCheckBox();
            } else {
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        columnsMap.forEach((columnName, column) ->
                column.setOnEditCommit(event -> {
                    var value = getValueForDtoSetting(event);

                    var dtoFieldEnum = WaterExtractPartialResultDtoFieldsEnum.getEnumByName(columnName);

                    event.getRowValue().setFieldValue(dtoFieldEnum, value);
                }));

        tableView.setItems(observableList);
    }

    @Override
    public void addNewRow() {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("addNewRow"));
    }

    private void getZeroCorrection(String columnName, TableColumn<WaterExtractPartialResultDto, String> column) {
        column.setCellValueFactory(data -> {
            Float value;

            if (columnName.matches(WATER_EXTRACT_PARTIAL_RESULT_NO3_COLUMN)) {
                value = data.getValue().getNO3Txt();
            } else {
                value = data.getValue().getFeTxt();
            }

            String descriptionText;

            if (Objects.isNull(value)) {
                descriptionText = StringUtils.EMPTY;
            } else if (value == 0F) {
                descriptionText = TRACES;
            } else {
                descriptionText = setDescriptionText(value);
            }

            return new SimpleStringProperty(descriptionText);
        });
    }

    private String setDescriptionText(Float value) {
        boolean isScientificNotation = value.toString().matches(SCIENTIFIC_NOTATION_PATTERN);

        if (isScientificNotation) {
            return value.toString();
        } else {
            return String.format(FLOAT_ROUND_VIEW_PATTERN, value);
        }
    }

    private void setIsBlockedCheckBox() {
        isBlocked.setCellFactory(cellValue -> new CheckBoxTableCell<>());
        isBlocked.setCellValueFactory(cellData -> {
            WaterExtractPartialResultDto waterExtractPartialResultDto = cellData.getValue();
            BooleanProperty property = new ReadOnlyBooleanWrapper(waterExtractPartialResultDto.getIsBlocked());

            property.addListener((observable, oldValue, newValue) -> {
                waterExtractPartialResultDto.setIsBlockedTransient(new SimpleBooleanProperty(newValue));
                waterExtractPartialResultDto.setIsBlocked(newValue);
            });

            return property;
        });
    }

    private static Object getValueForDtoSetting(TableColumn.CellEditEvent<WaterExtractPartialResultDto, ?> event) {
        var value = event.getNewValue();

        if (event.getNewValue().equals(StringUtils.EMPTY)) {
            value = null;
        }
        return value;
    }

    //region кнопки


    @FXML
    public void onWaterExtractChooseMethodVolumeButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_CHOOSE_METHOD_VOLUME_VIEW.getPath(),
                applicationContext, WATER_EXTRACT_CHOOSE_METHOD_VOLUME.getTitle());
    }

    @FXML
    public void onWaterExtractPartialDataTableButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_PARTIAL_DATA_VIEW.getPath(),
                applicationContext, WATER_EXTRACT_PARTIAL_DATA.getTitle());
    }

    @FXML
    public void onWaterExtractPartialResultTableButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Уже на экране результирующей таблицы");
    }

    //endregion
}
