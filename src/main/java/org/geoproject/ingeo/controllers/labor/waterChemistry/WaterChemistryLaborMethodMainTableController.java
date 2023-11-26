package org.geoproject.ingeo.controllers.labor.waterChemistry;

import org.geoproject.ingeo.controllers.labor.AbstractLaborMethodTableController;
import org.geoproject.ingeo.dto.methodDtos.WaterSampleResultDto;
import org.geoproject.ingeo.enums.dtoenums.WaterSampleResultDtoFieldsEnum;
import org.geoproject.ingeo.enums.laborenums.WaterColorEnum;
import org.geoproject.ingeo.enums.laborenums.WaterOdorEnum;
import org.geoproject.ingeo.enums.laborenums.WaterTransparencyEnum;
import org.geoproject.ingeo.exceptions.ConflictException;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.models.labor.WaterSampleResult;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.services.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.JavaFXConstants.WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE;

@Component
public class WaterChemistryLaborMethodMainTableController extends AbstractLaborMethodTableController<WaterSampleResult, WaterSampleResultDto>
        implements Initializable {

    @FXML
    private TableColumn<WaterSampleResultDto, DatePicker> samplingDate;

    public WaterChemistryLaborMethodMainTableController(ConfigurableApplicationContext applicationContext,
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
        var excludeColumnNameList = new ArrayList<>(Arrays.asList(
                WaterSampleResultDtoFieldsEnum.TRANSPARENCY.getName(),
                WaterSampleResultDtoFieldsEnum.COLOR.getName(),
                WaterSampleResultDtoFieldsEnum.ODOR.getName()
        ));

        columnsMap.values().forEach(column ->
                JavaFXCommonMethods.setCellFactory(column, tableView, observableList,
                        this::addNewRow, excludeColumnNameList));

        columnsMap.forEach((columnName, column) -> {
            if (columnName.equals(WaterSampleResultDtoFieldsEnum.TRANSPARENCY.getName()) ||
                    columnName.equals(WaterSampleResultDtoFieldsEnum.COLOR.getName()) ||
                    columnName.equals(WaterSampleResultDtoFieldsEnum.ODOR.getName())
            ) {
                setCellValueFactoryForChoiceBox((TableColumn<WaterSampleResultDto, String>) column);
            } else {
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        columnsMap.forEach((columnName, column) -> {
            if (!columnName.equals(WaterSampleResultDtoFieldsEnum.TRANSPARENCY.getName()) ||
                    !columnName.equals(WaterSampleResultDtoFieldsEnum.COLOR.getName()) ||
                    !columnName.equals(WaterSampleResultDtoFieldsEnum.ODOR.getName())) {
                column.setOnEditCommit(event -> {
                    if (columnName.equals(WaterSampleResultDtoFieldsEnum.LABOR_NUMBER.getName())) {
                        if (Boolean.TRUE.equals(service.checkForExistingLaborNumber((String) event.getOldValue(),
                                (String) event.getNewValue(), currentState.getCurrentProject()))) {
                            event.getRowValue().setFieldValue(WaterSampleResultDtoFieldsEnum.getEnumByName(columnName), StringUtils.EMPTY);

                            throw new ConflictException(ExceptionTypeEnum.WATER_SAMPLE_WITH_SUCH_LABOR_NUMBER_EXISTS_EXCEPTION.getMessage());
                        } else {
                            event.getRowValue()
                                    .setFieldValue(WaterSampleResultDtoFieldsEnum.getEnumByName(columnName), event.getNewValue());
                        }
                    } else {
                        event.getRowValue()
                                .setFieldValue(WaterSampleResultDtoFieldsEnum.getEnumByName(columnName), event.getNewValue());
                    }
                });
            }
        });

        DatePicker picker = new DatePicker(LocalDate.now());
        picker.editableProperty().bind(samplingDate.editableProperty());

        tableView.setItems(observableList);

        addNewRow();
    }

    public void addNewRow() {
        WaterSampleResultDto waterSampleResultDto = new WaterSampleResultDto();
        observableList.add(waterSampleResultDto);
        dtos.add(waterSampleResultDto);
    }

    //region установка choicebox

    private void setCellValueFactoryForChoiceBox(TableColumn<WaterSampleResultDto, String> tableColumn) {
        ObservableList<String> observableList = FXCollections.observableArrayList();

        if (tableColumn.getId().equals(WaterSampleResultDtoFieldsEnum.TRANSPARENCY.getName())) {
            observableList.addAll(Arrays.stream(WaterTransparencyEnum.values())
                    .map(WaterTransparencyEnum::getName)
                    .toList());

            tableColumn.setCellValueFactory(data -> {
                var emptyValue = Objects.isNull(data.getValue().getLaborNumber()) ?
                        StringUtils.EMPTY :
                        WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE;

                var text = Objects.isNull(data.getValue().getTransparency()) ?
                        emptyValue :
                        data.getValue().getTransparency();

                return new SimpleObjectProperty<>(text);
            });
        } else if (tableColumn.getId().equals(WaterSampleResultDtoFieldsEnum.COLOR.getName())) {
            observableList.addAll(Arrays.stream(WaterColorEnum.values())
                    .map(WaterColorEnum::getColor)
                    .toList());

            tableColumn.setCellValueFactory(data -> {
                var emptyValue = Objects.isNull(data.getValue().getLaborNumber()) ?
                        StringUtils.EMPTY :
                        WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE;

                var text = Objects.isNull(data.getValue().getColor()) ?
                        emptyValue :
                        data.getValue().getColor();

                return new SimpleObjectProperty<>(text);
            });
        } else if (tableColumn.getId().equals(WaterSampleResultDtoFieldsEnum.ODOR.getName())) {
            observableList.addAll(Arrays.stream(WaterOdorEnum.values())
                    .map(WaterOdorEnum::getOdor)
                    .toList());

            tableColumn.setCellValueFactory(data -> {
                var emptyValue = Objects.isNull(data.getValue().getLaborNumber()) ?
                        StringUtils.EMPTY :
                        WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE;

                var text = Objects.isNull(data.getValue().getOdor()) ?
                        emptyValue :
                        data.getValue().getOdor();

                return new SimpleObjectProperty<>(text);
            });
        }

        tableColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(observableList));
        tableColumn.setCellFactory(x -> new TableCell<>());

        tableColumn.setOnEditCommit(event -> {
            WaterSampleResultDto waterSampleResultDto = event.getRowValue();
            waterSampleResultDto.setFieldValue(WaterSampleResultDtoFieldsEnum.getEnumByName(tableColumn.getId()), event.getNewValue());
        });
    }

    //endregion

    // region кнопки

    @Override
    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        super.onSaveButtonClicked(event);

        WaterSampleResultDto checkWaterSampleResultDto = new WaterSampleResultDto();
        checkWaterSampleResultDto.setLaborNumber(StringUtils.EMPTY);

        if (!dtos.contains(checkWaterSampleResultDto)) {
            addNewRow();
        }
    }

    //endregion
}
