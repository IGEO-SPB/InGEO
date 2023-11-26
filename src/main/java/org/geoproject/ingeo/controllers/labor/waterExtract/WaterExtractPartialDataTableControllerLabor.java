package org.geoproject.ingeo.controllers.labor.waterExtract;

import org.geoproject.ingeo.controllers.labor.AbstractLaborMethodTableController;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.dtoenums.WaterExtractPartialDtoFieldsEnum;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.WaterExtractPartial;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.services.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.geoproject.ingeo.constants.JavaFXConstants.DEPTH_FROM_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.DEPTH_TO_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.LABOR_NUMBER_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.NO;
import static org.geoproject.ingeo.constants.JavaFXConstants.NO_SAMPLES;
import static org.geoproject.ingeo.constants.JavaFXConstants.SAMPLING_DATE_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.SURVEY_POINT_NUMBER_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.TEXTFIELD_NUMERIC_PATTERN;
import static org.geoproject.ingeo.constants.JavaFXConstants.WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE;
import static org.geoproject.ingeo.constants.JavaFXConstants.YES;
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_EXTRACT_CHOOSE_METHOD_VOLUME;
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_EXTRACT_PARTIAL_RESULT;

@Component
public class WaterExtractPartialDataTableControllerLabor extends AbstractLaborMethodTableController<WaterExtractPartial, WaterExtractPartialDto>
        implements Initializable {

    @FXML
    private TextField clCoef;
    @FXML
    private TableColumn<WaterExtractPartialDto, String> isBlocked;

    Map<String, Sample> samplesLaborNumbersSamplesMap;
    List<String> usedLaborNumbers;
    ObservableList<String> laborNumberObservableList = FXCollections.observableArrayList();


    public WaterExtractPartialDataTableControllerLabor(ConfigurableApplicationContext applicationContext,
                                                       SampleService sampleService, SurveyPointsService surveyPointsService, CurrentState currentState,
                                                       TableService<WaterExtractPartial, WaterExtractPartialDto> service) {
        super(applicationContext, sampleService, surveyPointsService, currentState, service);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        setClCoefTextField();
    }

    @Override
    public void setCellsFormat() {
        setInitValuesForLaborNumberChoiceBox();

        //список для колонок с нестандартным поведением - например, choicebox.
        // Обязательно заполнять!
        var excludeColumnNameList = new ArrayList<>(Arrays.asList(
                LABOR_NUMBER_COLUMN
        ));

        columnsMap.values().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView, observableList,
                this::addNewRow, excludeColumnNameList));

        columnsMap.forEach((columnName, column) -> {
            if (columnName.equals(LABOR_NUMBER_COLUMN)) {
                setCellValueFactoryForChoiceBox((TableColumn<WaterExtractPartialDto, String>) column);
            } else {
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        columnsMap.forEach((columnName, column) ->
                column.setOnEditCommit(event ->
                {
                    if (Boolean.FALSE.equals(event.getRowValue().getIsBlocked())) {
                        var value = event.getNewValue();

                        if (Objects.isNull(event.getNewValue()) || event.getNewValue().equals(StringUtils.EMPTY)) {
                            value = null;
                        }

                        event.getRowValue()
                                .setFieldValue(WaterExtractPartialDtoFieldsEnum.getEnumByName(columnName), value);

                        if (columnName.equals(LABOR_NUMBER_COLUMN)) {


                            event.getRowValue()
                                    .setFieldValue(WaterExtractPartialDtoFieldsEnum.getEnumByName(SURVEY_POINT_NUMBER_COLUMN), samplesLaborNumbersSamplesMap.get(event.getNewValue()).getSurveyPoint().getPointNumber());

                            ((TableColumn<WaterExtractPartialDto, String>) columnsMap.get(SURVEY_POINT_NUMBER_COLUMN)).setCellValueFactory(data -> {
                                String surveyPointNumber = data.getValue().getSurveyPointNumber();

                                return new SimpleStringProperty(surveyPointNumber);
                            });


                            event.getRowValue()
                                    .setFieldValue(WaterExtractPartialDtoFieldsEnum.getEnumByName(DEPTH_FROM_COLUMN), samplesLaborNumbersSamplesMap.get(event.getNewValue()).getDepthMin());

                            ((TableColumn<WaterExtractPartialDto, String>) columnsMap.get(DEPTH_FROM_COLUMN)).setCellValueFactory(data -> {
                                var initialValue = Objects.nonNull(data.getValue().getDepthFrom()) ?
                                        String.valueOf(data.getValue().getDepthFrom()) :
                                        StringUtils.EMPTY;

                                return new SimpleStringProperty(initialValue);
                            });


                            event.getRowValue()
                                    .setFieldValue(WaterExtractPartialDtoFieldsEnum.getEnumByName(DEPTH_TO_COLUMN), samplesLaborNumbersSamplesMap.get(event.getNewValue()).getDepthMax());

                            ((TableColumn<WaterExtractPartialDto, String>) columnsMap.get(DEPTH_TO_COLUMN)).setCellValueFactory(data -> {
                                var initialValue = Objects.nonNull(data.getValue().getDepthTo()) ?
                                        String.valueOf(data.getValue().getDepthTo()) :
                                        StringUtils.EMPTY;

                                return new SimpleStringProperty(initialValue);
                            });


                            event.getRowValue()
                                    .setFieldValue(WaterExtractPartialDtoFieldsEnum.getEnumByName(SAMPLING_DATE_COLUMN), samplesLaborNumbersSamplesMap.get(event.getNewValue()).getSamplingDate());

                            ((TableColumn<WaterExtractPartialDto, String>) columnsMap.get(SAMPLING_DATE_COLUMN)).setCellValueFactory(data -> {
                                var initialValue = Objects.nonNull(data.getValue().getSamplingDate()) ?
                                        String.valueOf(data.getValue().getSamplingDate()) :
                                        StringUtils.EMPTY;

                                return new SimpleStringProperty(initialValue);
                            });

                            setCellValueFactoryForChoiceBox((TableColumn<WaterExtractPartialDto, String>) column);

                            tableView.refresh();
                        }
                    } else {
                        WaterExtractPartialDto tempDto = event.getRowValue();

                        event.getTableView().getItems().set(event.getTablePosition().getRow(), tempDto);
                    }
                }));

        isBlocked.setCellValueFactory(data ->
        {
            boolean value = data.getValue().getIsBlocked();
            String stringValue = value ? YES : NO;
            return new SimpleStringProperty(stringValue);
        });

        tableView.setItems(observableList);

        addNewRow();
    }

    public void addNewRow() {
        WaterExtractPartialDto waterExtractPartialDto = new WaterExtractPartialDto();
        waterExtractPartialDto.setIsBlocked(Boolean.FALSE);
        observableList.add(waterExtractPartialDto);
        dtos.add(waterExtractPartialDto);

        TableColumn<WaterExtractPartialDto, String> waterExtractPartialDtoStringTableColumn = (TableColumn<WaterExtractPartialDto, String>) columnsMap.get(LABOR_NUMBER_COLUMN);

        setCellValueFactoryForChoiceBox(waterExtractPartialDtoStringTableColumn);

        tableView.refresh();
    }

    private void setClCoefTextField() {
        dtos.forEach(dto -> {
            if (Objects.nonNull(dto.getLaborNumber())) {
                clCoef.textProperty().bindBidirectional(dto.clCoefProperty(), JavaFXCommonMethods.getConverter());
            }
        });
    }

    //TODO: при удалении сэмпла переносить все связанные с ним сущности в архив
    private void setCellValueFactoryForChoiceBox(TableColumn<WaterExtractPartialDto, String> tableColumn) {
        List<String> laborNumberList = getLaborNumberList();
        laborNumberObservableList.clear();
        laborNumberObservableList.addAll(laborNumberList);

        tableColumn.setCellValueFactory(data -> {
            var emptyValue = laborNumberObservableList.isEmpty() ?
                    NO_SAMPLES :
                    WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE;

            var text = Objects.isNull(data.getValue().getLaborNumber()) ?
                    emptyValue :
                    data.getValue().getLaborNumber();

            return new SimpleObjectProperty<>(text);
        });

        tableColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(laborNumberObservableList));
    }

    private List<String> getLaborNumberList() {
        usedLaborNumbers = dtos.stream()
                .map(WaterExtractPartialDto::getLaborNumber)
                .collect(Collectors.toList());

        var laborNumberList = new ArrayList<>(samplesLaborNumbersSamplesMap.keySet());
        laborNumberList.removeAll(usedLaborNumbers);

        return laborNumberList;
    }

    private void setInitValuesForLaborNumberChoiceBox() {
        samplesLaborNumbersSamplesMap =
                sampleService.getByProject(currentState.getCurrentProject()).stream()
                        .collect(Collectors.toMap(Sample::getLaborNumber, Function.identity()));

        usedLaborNumbers = dtos.stream()
                .map(WaterExtractPartialDto::getLaborNumber)
                .collect(Collectors.toList());
    }

    //region кнопки

    public void onClCoefButtonClicked() {
        if (clCoef.getText().matches(TEXTFIELD_NUMERIC_PATTERN)) {
            dtos.forEach(waterExtractPartialDto -> waterExtractPartialDto.setClCoef(Float.parseFloat(clCoef.getText())));
            service.updateFromDtos(dtos);
        }
    }

    @FXML
    public void onCalculateButtonClicked() {
        service.updateFromDtos(dtos);
        service.calculate(dtos);
    }

    @FXML
    @Override
    public void onDeleteButtonClicked(ActionEvent event) {
        super.onDeleteButtonClicked(event);

        TableColumn<WaterExtractPartialDto, String> waterExtractPartialDtoStringTableColumn = (TableColumn<WaterExtractPartialDto, String>) columnsMap.get(LABOR_NUMBER_COLUMN);

        setCellValueFactoryForChoiceBox(waterExtractPartialDtoStringTableColumn);

        tableView.refresh();
    }


    @FXML
    public void onWaterExtractChooseMethodVolumeButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_CHOOSE_METHOD_VOLUME_VIEW.getPath(),
                applicationContext, WATER_EXTRACT_CHOOSE_METHOD_VOLUME.getTitle());
    }

    @FXML
    public void onWaterExtractPartialDataTableButtonClicked(ActionEvent event) {
        System.out.println("Уже на экране таблицы ввода данных");
    }

    @FXML
    public void onWaterExtractPartialResultTableButtonClicked(ActionEvent event) throws IOException {
        onCalculateButtonClicked();

        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_PARTIAL_RESULT_VIEW.getPath(),
                applicationContext, WATER_EXTRACT_PARTIAL_RESULT.getTitle());
    }

    //endregion
}
