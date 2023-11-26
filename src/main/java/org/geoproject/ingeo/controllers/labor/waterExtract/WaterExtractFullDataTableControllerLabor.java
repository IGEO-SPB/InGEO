package org.geoproject.ingeo.controllers.laborMethods.waterExtract;

import org.geoproject.ingeo.controllers.laborMethods.AbstractLaborMethodTableController;
import org.geoproject.ingeo.dto.WaterExtractFullDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.dtoenums.WaterExtractFullDtoFieldsEnum;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.WaterExtractFull;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.mainViews.SurveyPointsService;
import org.geoproject.ingeo.services.tableViews.TableService;
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
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_EXTRACT_FULL_RESULT;

@Component
public class WaterExtractFullDataTableControllerLabor extends AbstractLaborMethodTableController<WaterExtractFull, WaterExtractFullDto>
        implements Initializable {

    @FXML
    private TextField clCoef;
    @FXML
    private TableColumn<WaterExtractFullDto, String> isBlocked;

    Map<String, Sample> samplesLaborNumbersSamplesMap;
    List<String> usedLaborNumbers;
    ObservableList<String> laborNumberObservableList = FXCollections.observableArrayList();


    public WaterExtractFullDataTableControllerLabor(ConfigurableApplicationContext applicationContext,
                                                    SampleService sampleService, SurveyPointsService surveyPointsService, CurrentState currentState,
                                                    TableService<WaterExtractFull, WaterExtractFullDto> service) {
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
                setCellValueFactoryForChoiceBox((TableColumn<WaterExtractFullDto, String>) column);
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
                                .setFieldValue(WaterExtractFullDtoFieldsEnum.getEnumByName(columnName), value);

                        if (columnName.equals(LABOR_NUMBER_COLUMN)) {
                            event.getRowValue()
                                    .setFieldValue(WaterExtractFullDtoFieldsEnum.getEnumByName(SURVEY_POINT_NUMBER_COLUMN), samplesLaborNumbersSamplesMap.get(event.getNewValue()).getSurveyPoint().getPointNumber());

                            ((TableColumn<WaterExtractFullDto, String>) columnsMap.get(SURVEY_POINT_NUMBER_COLUMN)).setCellValueFactory(data -> {
                                String surveyPointNumber = data.getValue().getSurveyPointNumber();

                                return new SimpleStringProperty(surveyPointNumber);
                            });

                            event.getRowValue()
                                    .setFieldValue(WaterExtractFullDtoFieldsEnum.getEnumByName(DEPTH_FROM_COLUMN), samplesLaborNumbersSamplesMap.get(event.getNewValue()).getDepthMin());

                            ((TableColumn<WaterExtractFullDto, String>) columnsMap.get(DEPTH_FROM_COLUMN)).setCellValueFactory(data -> {
                                var initialValue = Objects.nonNull(data.getValue().getDepthFrom()) ?
                                        String.valueOf(data.getValue().getDepthFrom()) :
                                        StringUtils.EMPTY;

                                return new SimpleStringProperty(initialValue);
                            });


                            event.getRowValue()
                                    .setFieldValue(WaterExtractFullDtoFieldsEnum.getEnumByName(DEPTH_TO_COLUMN), samplesLaborNumbersSamplesMap.get(event.getNewValue()).getDepthMax());

                            ((TableColumn<WaterExtractFullDto, String>) columnsMap.get(DEPTH_TO_COLUMN)).setCellValueFactory(data -> {
                                var initialValue = Objects.nonNull(data.getValue().getDepthTo()) ?
                                        String.valueOf(data.getValue().getDepthTo()) :
                                        StringUtils.EMPTY;

                                return new SimpleStringProperty(initialValue);
                            });


                            event.getRowValue()
                                    .setFieldValue(WaterExtractFullDtoFieldsEnum.getEnumByName(SAMPLING_DATE_COLUMN), samplesLaborNumbersSamplesMap.get(event.getNewValue()).getSamplingDate());

                            ((TableColumn<WaterExtractFullDto, String>) columnsMap.get(SAMPLING_DATE_COLUMN)).setCellValueFactory(data -> {
                                var initialValue = Objects.nonNull(data.getValue().getSamplingDate()) ?
                                        String.valueOf(data.getValue().getSamplingDate()) :
                                        StringUtils.EMPTY;

                                return new SimpleStringProperty(initialValue);
                            });

                            setCellValueFactoryForChoiceBox((TableColumn<WaterExtractFullDto, String>) column);

                            tableView.refresh();
                        }
                    } else {
                        WaterExtractFullDto tempDto = event.getRowValue();

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
        WaterExtractFullDto waterExtractFullDto = new WaterExtractFullDto();
        waterExtractFullDto.setIsBlocked(Boolean.FALSE);
        observableList.add(waterExtractFullDto);
        dtos.add(waterExtractFullDto);

        TableColumn<WaterExtractFullDto, String> waterExtractFullDtoStringTableColumn = (TableColumn<WaterExtractFullDto, String>) columnsMap.get(LABOR_NUMBER_COLUMN);

        setCellValueFactoryForChoiceBox(waterExtractFullDtoStringTableColumn);

        tableView.refresh();
    }

    private void setClCoefTextField() {
        dtos.forEach(dto -> clCoef.textProperty().bindBidirectional(dto.clCoefProperty(), JavaFXCommonMethods.getConverter()));
    }
//TODO: при удалении сэмпла переносить все связанные с ним сущности в архив
    private void setCellValueFactoryForChoiceBox(TableColumn<WaterExtractFullDto, String> tableColumn) {
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
                .map(WaterExtractFullDto::getLaborNumber)
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
                .map(WaterExtractFullDto::getLaborNumber)
                .collect(Collectors.toList());
    }

    //region кнопки

    public void onClCoefButtonClicked() {
        if (clCoef.getText().matches(TEXTFIELD_NUMERIC_PATTERN)) {
            dtos.forEach(waterExtractFullDto -> waterExtractFullDto.setClCoef(Float.parseFloat(clCoef.getText())));
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

        TableColumn<WaterExtractFullDto, String> waterExtractFullDtoStringTableColumn = (TableColumn<WaterExtractFullDto, String>) columnsMap.get(LABOR_NUMBER_COLUMN);

        setCellValueFactoryForChoiceBox(waterExtractFullDtoStringTableColumn);

        tableView.refresh();
    }


    @FXML
    public void onWaterExtractChooseMethodVolumeButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_CHOOSE_METHOD_VOLUME_VIEW.getPath(),
                applicationContext, WATER_EXTRACT_CHOOSE_METHOD_VOLUME.getTitle());
    }

    @FXML
    public void onWaterExtractFullDataTableButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Уже на экране таблицы ввода данных");
    }

    @FXML
    public void onWaterExtractFullResultTableButtonClicked(ActionEvent event) throws IOException {
        onCalculateButtonClicked();

        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_FULL_RESULT_VIEW.getPath(),
                applicationContext, WATER_EXTRACT_FULL_RESULT.getTitle());
    }

    //endregion
}
