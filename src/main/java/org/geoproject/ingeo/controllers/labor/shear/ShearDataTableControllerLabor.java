package org.geoproject.ingeo.controllers.laborMethods.shear;

import org.geoproject.ingeo.controllers.laborMethods.AbstractLaborMethodTableController;
import org.geoproject.ingeo.dto.ShearDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.dtoenums.ShearDtoFieldsEnum;
import org.geoproject.ingeo.methods.labor.ShearMethod;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.Shear;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.mainViews.SurveyPointsService;
import org.geoproject.ingeo.services.tableViews.PhysicalPropertiesService;
import org.geoproject.ingeo.services.tableViews.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.geoproject.ingeo.constants.JavaFXConstants.DEPTH_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.IS_EXCLUDED;
import static org.geoproject.ingeo.constants.JavaFXConstants.KD_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.LABOR_NUMBER_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE;
import static org.geoproject.ingeo.constants.ServiceConstants.SINGLE_INDEX_POINT;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;
import static org.geoproject.ingeo.enums.StageTitleEnum.SHEAR_WATER_CONTENT_CUT_RING;

@Log4j2
@Component
public class ShearDataTableControllerLabor extends AbstractLaborMethodTableController<Shear, ShearDto>
        implements Initializable {

    private final PhysicalPropertiesService physicalPropertiesService;

    @FXML
    private TextField shearRingDensityAverageFirstMeasurement;
    @FXML
    private TextField naturalShearAverageWaterContentFirstMeasurement;
    @FXML
    private TextField physicalPropertiesDensityBefore;
    @FXML
    private TextField physicalPropertiesWaterContentBefore;

    @FXML
    private TextField chartLaborNumber;
    @FXML
    private TextField chartTgFi;
    @FXML
    private TextField chartFi;
    @FXML
    private TextField chartC;

    @FXML
    private TableColumn<ShearDto, Boolean> kd;
    @FXML
    private TableColumn<ShearDto, Boolean> isExcluded;


    @FXML
    private LineChart<Number, Number> shearChart;
    @FXML
    private ScatterChart<String, Float> shearScatterChart;

    Map<String, Sample> samplesLaborNumbersSamplesMap;
    ObservableList<String> laborNumberObservableList = FXCollections.observableArrayList();


    public ShearDataTableControllerLabor(ConfigurableApplicationContext applicationContext,
                                         SampleService sampleService, SurveyPointsService surveyPointsService, CurrentState currentState,
                                         TableService<Shear, ShearDto> service, PhysicalPropertiesService physicalPropertiesService) {
        super(applicationContext, sampleService, surveyPointsService, currentState, service);
        this.physicalPropertiesService = physicalPropertiesService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        sortObservableListByShearPointNumber();

        setTableViewRowSelectListener();

        if (!dtos.isEmpty()) {
            setChart(0);

            dtos.forEach(dto -> {
                System.out.println("!!! " + dto.getXSigmaSum());
                System.out.println("!!! " + dto.getYTauSum());
                System.out.println("!!! " + dto.getXyProductionSum());
                System.out.println("!!! " + dto.getXSquaredSum());
                System.out.println("!!! " + dto.getTgFi());
                System.out.println("!!! " + dto.getFi());
                System.out.println("!!! " + dto.getDegrees());
                System.out.println("!!! " + dto.getC());
            });
            setTextFields(dtos.get(ZERO_INDEX));
        }
    }

    @Override
    public void setCellsFormat() {
        setInitValuesForLaborNumberChoiceBox();

        //список для колонок с нестандартным поведением - например, choicebox.
        // Обязательно заполнять!
        var excludeColumnNameList = new ArrayList<>(Arrays.asList(
                LABOR_NUMBER_COLUMN,
                KD_COLUMN,
                IS_EXCLUDED
        ));

//        columnsMap.values().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView, observableList,
//                this::addNewRow, excludeColumnNameList));

        columnsMap.values().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView, observableList,
                null, excludeColumnNameList));

        columnsMap.forEach((columnName, column) -> {
            if (columnName.equals(LABOR_NUMBER_COLUMN)) {
//                setCellValueFactoryForChoiceBox((TableColumn<ShearDto, String>) column);
                setCellValueFactoryForComboBox((TableColumn<ShearDto, String>) column);
            }
            if (columnName.equals(ShearDtoFieldsEnum.KD.getName()) ||
                    columnName.equals(ShearDtoFieldsEnum.IS_EXCLUDED.getName())) {
                setCheckBox((TableColumn<ShearDto, Boolean>) column);
            } else {
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        columnsMap.forEach((columnName, column) ->
                column.setOnEditCommit(event ->
                {
                    var value = event.getNewValue();

                    if (Objects.isNull(event.getNewValue()) || event.getNewValue().equals(StringUtils.EMPTY)) {
                        value = null;
                    }

                    event.getRowValue()
                            .setFieldValue(ShearDtoFieldsEnum.getEnumByName(columnName), value);

                    if (columnName.equals(LABOR_NUMBER_COLUMN)) {

                        var rowValue = event.getRowValue();
                        var laborNumber = event.getNewValue();

                        var sample = sampleService.getByLaborNumber((String) laborNumber);

                        var physicalProperties = physicalPropertiesService.getBySample(sample);

                        rowValue.setPhysicalPropertiesDensityBefore(physicalProperties.getRingDensityAverage());
                        rowValue.setPhysicalPropertiesWaterContentBefore(physicalProperties.getNaturalAverageWaterContent());

                        //для новой строчки эти значения устанавливаются в 0,
                        //чтобы у пользователя не было иллюзии, что средние значения рассчитаны
                        rowValue.setNaturalShearAverageWaterContentFirstMeasurement(777);
                        rowValue.setShearRingDensityAverageFirstMeasurement(777);

                        rowValue.setFieldValue(ShearDtoFieldsEnum.getEnumByName(DEPTH_COLUMN), samplesLaborNumbersSamplesMap.get(laborNumber).getDepthMin());

                        ((TableColumn<ShearDto, String>) columnsMap.get(DEPTH_COLUMN)).setCellValueFactory(data -> {
                            var initialValue = Objects.nonNull(data.getValue().getDepth()) ?
                                    String.valueOf(data.getValue().getDepth()) :
                                    StringUtils.EMPTY;

                            return new SimpleStringProperty(initialValue);
                        });

                        tableView.refresh();
                    }
                }));

        tableView.setItems(observableList);
    }

    @Override
    public void addNewRow() {
        log.warn("Init addNewRow()");


        ShearDto shearDto = new ShearDto();

        shearDto.setShearPointNumber(observableList.size() + SINGLE_INDEX_POINT);
        shearDto.setKd(Boolean.FALSE);
        shearDto.setIsExcluded(Boolean.FALSE);

//        var sample = sampleService.getByLaborNumber(shearDto.getLaborNumber());
//
//        var physicalProperties = physicalPropertiesService.getBySample(sample);
//
//        shearDto.setPhysicalPropertiesDensityBefore(physicalProperties.getRingDensityAverage());
//        shearDto.setPhysicalPropertiesWaterContentBefore(physicalProperties.getNaturalAverageWaterContent());
//
//        //для новой строчки эти значения устанавливаются в 0,
//        //чтобы у пользователя не было иллюзии, что средние значения рассчитаны
//        shearDto.setNaturalShearAverageWaterContentFirstMeasurement(0);
//        shearDto.setShearRingDensityAverageFirstMeasurement(0);

        observableList.add(shearDto);

        dtos.add(shearDto);

        TableColumn<ShearDto, String> waterExtractPartialDtoStringTableColumn = (TableColumn<ShearDto, String>) columnsMap.get(LABOR_NUMBER_COLUMN);

        tableView.refresh();
    }

    public void addNewRow(int selectedIndex) {
        log.warn("Init addNewRow(int selectedIndex)");

        ShearDto dtoForIndex = tableView.getItems().get(selectedIndex);

        ShearDto shearDto = new ShearDto();

        shearDto.setShearPointNumber(dtoForIndex.getShearPointNumber());
        shearDto.setKd(Boolean.FALSE);
        shearDto.setIsExcluded(Boolean.FALSE);

//        var sample = sampleService.getByLaborNumber(shearDto.getLaborNumber());
//
//        var physicalProperties = physicalPropertiesService.getBySample(sample);
//
//        shearDto.setPhysicalPropertiesDensityBefore(physicalProperties.getRingDensityAverage());
//        shearDto.setPhysicalPropertiesWaterContentBefore(physicalProperties.getNaturalAverageWaterContent());
//
//        //для новой строчки эти значения устанавливаются в 0,
//        //чтобы у пользователя не было иллюзии, что средние значения рассчитаны
//        shearDto.setNaturalShearAverageWaterContentFirstMeasurement(0);
//        shearDto.setShearRingDensityAverageFirstMeasurement(0);

        System.out.println("D-T-O-S");
        dtos.forEach(d -> {
            System.out.println("=========");
            System.out.println(d.getLaborNumber());
            System.out.println(d.getShearPointNumber());
            System.out.println("=========");
        });

        for (int i = dtoForIndex.getShearPointNumber() - 1; i < observableList.size(); i++) {
            observableList.get(i).setShearPointNumber(observableList.get(i).getShearPointNumber() + SINGLE_INDEX_POINT);
            dtos.get(i).setShearPointNumber(dtos.get(i).getShearPointNumber());
        }

        System.out.println("D-T-O-S after");
        dtos.forEach(d -> {
            System.out.println("=========");
            System.out.println(d.getLaborNumber());
            System.out.println(d.getShearPointNumber());
            System.out.println("=========");
        });

//        service.updateFromDtos(dtos);

        observableList.add(shearDto);

        sortObservableListByShearPointNumber();

        dtos.add(shearDto);

        System.out.println("D-T-O-S after after");
        dtos.forEach(d -> {
            System.out.println("=========");
            System.out.println(d.getLaborNumber());
            System.out.println(d.getShearPointNumber());
            System.out.println("=========");
        });

        sortDtosByShearPointNumber();

        System.out.println("D-T-O-S after sort");
        dtos.forEach(d -> {
            System.out.println("=========");
            System.out.println(d.getLaborNumber());
            System.out.println(d.getShearPointNumber());
            System.out.println("=========");
        });

        TableColumn<ShearDto, String> waterExtractPartialDtoStringTableColumn = (TableColumn<ShearDto, String>) columnsMap.get(LABOR_NUMBER_COLUMN);

        tableView.refresh();
    }

    private void sortObservableListByShearPointNumber() {
        observableList.sort((o1, o2) -> {
            if (o1.getShearPointNumber() > o2.getShearPointNumber()) {
                return 1;
            } else if (o1.getShearPointNumber() < o2.getShearPointNumber()) {
                return -1;
            }
            return 0;
        });
    }

    private void sortDtosByShearPointNumber() {
        dtos.sort((o1, o2) -> {
            if (o1.getShearPointNumber() > o2.getShearPointNumber()) {
                return 1;
            } else if (o1.getShearPointNumber() < o2.getShearPointNumber()) {
                return -1;
            }
            return 0;
        });
    }

    private void setTextFields(ShearDto shearDto) {
        log.info("Set text fields: " + shearDto.getLaborNumber());

        dtos.forEach(dto -> {
            System.out.println("!!! " + dto.getXSigmaSum());
            System.out.println("!!! " + dto.getYTauSum());
            System.out.println("!!! " + dto.getXyProductionSum());
            System.out.println("!!! " + dto.getXSquaredSum());
            System.out.println("!!! " + dto.getTgFi());
            System.out.println("!!! " + dto.getFi());
            System.out.println("!!! " + dto.getDegrees());
            System.out.println("!!! " + dto.getC());
        });
//            if (Objects.nonNull(shearDto.getLaborNumber())) {

//        shearRingDensityAverageFirstMeasurement.textProperty().bindBidirectional(shearDto.shearRingDensityAverageFirstMeasurementProperty(), JavaFXCommonMethods.getConverter());
//        naturalShearAverageWaterContentFirstMeasurement.textProperty().bindBidirectional(shearDto.naturalShearAverageWaterContentFirstMeasurementProperty(), JavaFXCommonMethods.getConverter());
//        physicalPropertiesDensityBefore.textProperty().bindBidirectional(shearDto.physicalPropertiesDensityBeforeProperty(), JavaFXCommonMethods.getConverter());
//        physicalPropertiesWaterContentBefore.textProperty().bindBidirectional(shearDto.physicalPropertiesWaterContentBeforeProperty(), JavaFXCommonMethods.getConverter());

        shearRingDensityAverageFirstMeasurement.textProperty().setValue(String.valueOf(shearDto.getShearRingDensityAverageFirstMeasurement()));
        naturalShearAverageWaterContentFirstMeasurement.textProperty().setValue(String.valueOf(shearDto.getNaturalShearAverageWaterContentFirstMeasurement()));
        physicalPropertiesDensityBefore.textProperty().setValue(String.valueOf(shearDto.getPhysicalPropertiesDensityBefore()));
        physicalPropertiesWaterContentBefore.textProperty().setValue(String.valueOf(shearDto.getPhysicalPropertiesWaterContentBefore()));

        chartLaborNumber.setText(String.valueOf(shearDto.getLaborNumber()));
        chartTgFi.setText(String.valueOf(shearDto.getTgFi()));
        chartFi.setText(String.valueOf(shearDto.getFi()));
        chartC.setText(String.valueOf(shearDto.getC()));
        //        }
    }

    private void setCellValueFactoryForChoiceBox(TableColumn<ShearDto, String> tableColumn) {
        var laborNumberList = new ArrayList<>(samplesLaborNumbersSamplesMap.keySet());
        laborNumberObservableList.setAll(laborNumberList);

        tableColumn.setCellValueFactory(data -> {
            var emptyValue = WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE;

            var text = Objects.isNull(data.getValue().getLaborNumber()) ?
                    emptyValue :
                    data.getValue().getLaborNumber();

            return new SimpleObjectProperty<>(text);
        });

        tableColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(laborNumberObservableList));
    }

    private void setCellValueFactoryForComboBox(TableColumn<ShearDto, String> tableColumn) {
        var laborNumberList = new ArrayList<>(samplesLaborNumbersSamplesMap.keySet());
        laborNumberObservableList.setAll(laborNumberList);

        tableColumn.setCellValueFactory(data -> {
            var emptyValue = WATER_SAMPLE_PROPERTY_CHOICE_BOX_VALUE;

            var text = Objects.isNull(data.getValue().getLaborNumber()) ?
                    emptyValue :
                    data.getValue().getLaborNumber();

            return new SimpleObjectProperty<>(text);
        });

        tableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(laborNumberObservableList));
    }

    private void setInitValuesForLaborNumberChoiceBox() {
        samplesLaborNumbersSamplesMap =
                sampleService.getByProject(currentState.getCurrentProject()).stream()
                        .collect(Collectors.toMap(Sample::getLaborNumber, Function.identity()));
    }

    private void setCheckBox(TableColumn<ShearDto, Boolean> column) {
        column.setCellFactory(cellValue -> new CheckBoxTableCell<>());

        column.setCellValueFactory(cellData -> {
            BooleanProperty property = new ReadOnlyBooleanWrapper();
            ShearDto shearDto = cellData.getValue();

            if (Objects.equals(column.getId(), ShearDtoFieldsEnum.KD.getName()) && Objects.nonNull(shearDto) &&
                    Objects.nonNull(shearDto.getKd())) {
                property = new ReadOnlyBooleanWrapper(shearDto.getKd());

                property.addListener((observable, oldValue, newValue) -> {
                    shearDto.setKdTransient(new SimpleBooleanProperty(newValue));
                    shearDto.setKd(newValue);
                });

            } else if (Objects.equals(column.getId(), ShearDtoFieldsEnum.IS_EXCLUDED.getName()) && Objects.nonNull(shearDto) &&
                    Objects.nonNull(shearDto.getIsExcluded())) {
                property = new ReadOnlyBooleanWrapper(shearDto.getIsExcluded());

                property.addListener((observable, oldValue, newValue) -> {
                    shearDto.setIsExcludedTransient(new SimpleBooleanProperty(newValue));
                    shearDto.setIsExcluded(newValue);
                });
            }

            return property;
        });
    }

    private void setTableViewRowSelectListener() {

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                setChart(selectedIndex);
                setTextFields(dtos.get(selectedIndex));
            }
        });
    }

    private void setChart(int rowIndex) {
        shearChart.getData().clear();
        System.out.println("rowIndex");
        System.out.println(rowIndex);

        ShearDto currentDto = tableView.getItems().get(rowIndex);

        var shearDtoList = dtos.stream()
                .filter(dto -> Objects.equals(currentDto.getLaborNumber(), dto.getLaborNumber()))
                .collect(Collectors.toList());

        System.out.println("!!!shearDtoList!!! " + shearDtoList.size());

//        var shearDtoList = new ArrayList<ShearDto>();
//
//        ShearDto shearDtoOne = new ShearDto();
////        shearDtoOne.setVerticalLoading(0.025F);
////        shearDtoOne.setShearStrength(0.032F);
//        shearDtoOne.setVerticalLoading(0.025F);
//        shearDtoOne.setShearStrength(0.013F);
//
//        ShearDto shearDtoTwo = new ShearDto();
////        shearDtoTwo.setVerticalLoading(0.075F);
////        shearDtoTwo.setShearStrength(0.042F);
//        shearDtoTwo.setVerticalLoading(0.075F);
//        shearDtoTwo.setShearStrength(0.02F);
//
//        ShearDto shearDtoThree = new ShearDto();
////        shearDtoThree.setVerticalLoading(0.125F);
////        shearDtoThree.setShearStrength(0.052F);
//        shearDtoThree.setVerticalLoading(0.125F);
//        shearDtoThree.setShearStrength(0.03F);

//        shearDtoList.add(shearDtoOne);
//        shearDtoList.add(shearDtoTwo);
//        shearDtoList.add(shearDtoThree);

        ShearMethod.calculateApproximation(shearDtoList);
//        service.updateFromDtos(shearDtoList);

        var tgFi = shearDtoList.get(0).getTgFi();
        var c = shearDtoList.get(0).getC();

        var firstX = 0F;
        var firstY = tgFi * firstX + c;

        float secondX = 0;

        try {
            secondX = shearDtoList.get(shearDtoList.size() - 1).getVerticalLoading() + shearDtoList.get(0).getVerticalLoading();
        } catch (NullPointerException e) {
            log.warn(e.getMessage());
        }

        var secondY = tgFi * secondX + c;

        XYChart.Series<Number, Number> aproxSeries = new XYChart.Series<>();

        aproxSeries.getData().add(new XYChart.Data<>(firstX, firstY));
        aproxSeries.getData().add(new XYChart.Data<>(secondX, secondY));

        ObservableList<XYChart.Series<Number, Number>> seriesObservableList = FXCollections.observableArrayList();

        shearDtoList.forEach(dto -> {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();

            try {
                series.getData().add(new XYChart.Data<>(dto.getVerticalLoading(), dto.getShearStrength()));
            } catch (NullPointerException e) {
                log.warn(e.getMessage());
            }

            seriesObservableList.add(series);
        });

        shearChart.setCreateSymbols(Boolean.FALSE);

        shearChart.getData().add(aproxSeries);
        shearChart.getData().addAll(seriesObservableList);
    }

    //region кнопки

    @FXML
    public void onCalculateButtonClicked() {
        service.updateFromDtos(dtos);
        service.calculate(dtos);
    }

    @FXML
    public void onCopySoilDescriptionButtonClicked() {

        dtos.forEach(dto -> {

            var sample = sampleService.getByLaborNumber(dto.getLaborNumber());

            var physicalProperties = physicalPropertiesService.getBySample(sample);

            var soilDescription =
                    String.format("%s %s %s",
                            setSoilDescriptionPart(physicalProperties.getPlasticitySoilSubType()),
                            setSoilDescriptionPart(physicalProperties.getLiquiditySoilSubType()),
                            setSoilDescriptionPart(physicalProperties.getClaySoilSubType())
                    );

            dto.setSoilDescription(soilDescription);
        });

//        service.updateFromDtos(dtos);

        tableView.refresh();
    }

    private String setSoilDescriptionPart(String description) {
        return Objects.nonNull(description) ? description : StringUtils.EMPTY;
    }

    @FXML
    public void onAddNewPointBeforeSelectedRowButtonClicked(ActionEvent event) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        addNewRow(selectedIndex);
    }

    @FXML
    public void onAddNewPointAtTheEndButtonClicked(ActionEvent event) {
        addNewRow();
    }

    @FXML
    @Override
    public void onSaveButtonClicked(ActionEvent event) {

        System.out.println("DTOs BEFORE:");
        System.out.println(dtos.size());

        super.onSaveButtonClicked(event);

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        setChart(selectedIndex);
        setTextFields(dtos.get(selectedIndex));

        dtos = service.getDTOs(currentState.getSurveyPoint());


        System.out.println("DTOs AFTER:");
        System.out.println(dtos.size());
        ;

//        dtos.clear();
    }

    @FXML
    public void onDeleteButtonClicked(ActionEvent event) {
        super.onDeleteButtonClicked(event);

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != observableList.size() - SINGLE_INDEX_POINT) {
            ShearDto dtoForIndex = tableView.getItems().get(selectedIndex);

            for (int i = dtoForIndex.getShearPointNumber() - 1 - SINGLE_INDEX_POINT; i < observableList.size(); i++) {
                observableList.get(i).setShearPointNumber(observableList.get(i).getShearPointNumber() - SINGLE_INDEX_POINT);
                dtos.get(i).setShearPointNumber(observableList.get(i).getShearPointNumber());
            }
        }
    }

    @FXML
    public void onShearDensityAndWaterContentButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.SHEAR_WATER_CONTENT_CUT_RING_VIEW.getPath(),
                applicationContext, SHEAR_WATER_CONTENT_CUT_RING.getTitle());
    }

    @FXML
    public void onEnterShearDataButtonClicked(ActionEvent event) {
        System.out.println("Уже на экране таблицы ввода данных");
    }

    @FXML
    public void onShearResultTableButtonClicked(ActionEvent event) throws IOException {
        onCalculateButtonClicked();

//        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_PARTIAL_RESULT_VIEW.getPath(),
//                applicationContext, WATER_EXTRACT_PARTIAL_RESULT.getTitle());
    }

    //endregion
}
