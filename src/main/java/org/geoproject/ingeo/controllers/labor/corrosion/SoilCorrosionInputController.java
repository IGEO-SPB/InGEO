package org.geoproject.ingeo.controllers.labor.corrosion;

import org.geoproject.ingeo.dto.methodDtos.SoilCorrosionInputDto;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.labor.SoilCorrosionService;
import org.geoproject.ingeo.spreadsheetlib.SpreadsheetHeader;
import org.geoproject.ingeo.utils.CurrentState;
import javafx.beans.property.FloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import lombok.extern.apachecommons.CommonsLog;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Component
@CommonsLog
public class SoilCorrosionInputController implements Initializable {
    @FXML
    public Button buttonCalculateAverageValues;
    @FXML
    public Button buttonCalculateR;

    @FXML
    private SpreadsheetView spreadsheet;

    private SoilCorrosionService service;
    private SampleService sampleService;
    private CurrentState state;

    private List<SpreadsheetHeader> rootHeaders;
    private SpreadsheetHeader.CalculationResult calcResult;

    private void initRootSpreadsheetHeaders() {
        var headerLabNumber = new SpreadsheetHeader("Лаб. №");
        var headerCurrent = new SpreadsheetHeader("Ток I [мА]");
        var headerVoltage = new SpreadsheetHeader("Напряжение U [В]");
        var headerResistance = new SpreadsheetHeader("Сопротивление R [Ом]");
        var headerCathodeCurrent = new SpreadsheetHeader("Катодный ток Ik [мА]");

        var headerEmpty = new SpreadsheetHeader("", List.of(headerLabNumber));
        var headerUesg = new SpreadsheetHeader("Измерение УЭСГ", List.of(headerCurrent, headerVoltage, headerResistance));
        var headerPkt = new SpreadsheetHeader("Измерение ПКТ", List.of(headerCathodeCurrent));

        rootHeaders = List.of(headerEmpty, headerUesg, headerPkt);
        calcResult = SpreadsheetHeader.convertToSpreadsheetCellMatrix(rootHeaders.toArray(new SpreadsheetHeader[0]));
    }
    public SoilCorrosionInputController(SoilCorrosionService soilCorrosionService, CurrentState currentState, SampleService sampleService) {
        this.service = soilCorrosionService;
        this.state = currentState;
        this.sampleService = sampleService;

        initRootSpreadsheetHeaders();
    }

    private ObservableList<SoilCorrosionInputDto> viewModel = FXCollections.observableArrayList();

    private SpreadsheetCell createEmptyCell(int row, int column, int rowSpan, int colSpan) {
        var cell = SpreadsheetCellType.STRING.createCell(row, column, rowSpan, colSpan, "");
        cell.setEditable(false);
        return cell;
    }

    private List<String> getAvailableLaborNumbers() {
        if(state.getSurveyPoint() == null) {
            log.info("survey point is empty");
            return List.of();
        }
        return sampleService
                .getBySurveyPoint(state.getSurveyPoint(), Sort.by(Sort.Direction.ASC, "laborNumber"))
                .stream().map(Sample::getLaborNumber)
                .toList();
    }

    private SpreadsheetCell createLaborNumberChooseCell(int row, int column, int rowSpan, int colSpan) {
        var list = new ArrayList<>(List.of(""));
        list.addAll(getAvailableLaborNumbers());
        if(list.size() == 1) {
            return SpreadsheetCellType.LIST(list).createCell(row, column, rowSpan, colSpan, list.get(0));
        }

        var cell = SpreadsheetCellType.LIST(list).createCell(row, column, rowSpan, colSpan, list.get(0));
        cell.textProperty().addListener((observableValue, s, t1) -> {
            log.info(t1);
            if(!t1.isEmpty()) {
                var newItem = service.createNewSoilCorrosion(state.getSample(), t1);
                viewModel.add(newItem);
                spreadsheet.setGrid(createGrid());
            }
        });
        return cell;
    }

    private SpreadsheetCell createDoubleCell(FloatProperty property, int row, int col) {
        var cell0 = SpreadsheetCellType.DOUBLE.createCell(row, col, 1, 1, property.doubleValue());
        cell0.textProperty().addListener((observableValue, s, t1) -> {
            property.setValue(Double.parseDouble(t1));
        });
        return cell0;
    }

    private ObservableList<SpreadsheetCell> createDataSpreadsheetRow(SoilCorrosionInputDto dto, int row) {
        var cell0 = SpreadsheetCellType.STRING.createCell(row, 0, 1, 1,dto.getLabNumber());
        cell0.setEditable(false);
        var cell1 = createDoubleCell(dto.currentProperty(), row, 1);
        var cell2 = createDoubleCell(dto.voltageProperty(), row, 2);
        var cell3 = createDoubleCell(dto.resistanceProperty(), row, 3);
        var cell4 = createDoubleCell(dto.cathodeCurrentProperty(), row, 4);

        var res = FXCollections.observableArrayList(cell0, cell1, cell2, cell3, cell4);
        return res;
    }

    private List<ObservableList<SpreadsheetCell>> initializeDataRows(int startPosition) {
        var res = new ArrayList<ObservableList<SpreadsheetCell>>();
        for(int i = 0; i < viewModel.size(); ++i) {
            res.add(createDataSpreadsheetRow(viewModel.get(i), startPosition + i));
        }

        return res;
    }

    private ObservableList<SpreadsheetCell> initializeCreatorRow(int startPosition) {
        final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
        list.add(createLaborNumberChooseCell(startPosition, 0, 1, 1));
        list.add(createEmptyCell(startPosition, 1, 1, 1));
        list.add(createEmptyCell(startPosition, 2, 1, 1));
        list.add(createEmptyCell(startPosition, 3, 1, 1));
        list.add(createEmptyCell(startPosition, 4, 1, 1));
        return list;
    }
    private GridBase createGrid() {
        int rowCount = 3; //Will be re-calculated after if incorrect.
        int columnCount = 5;

        GridBase grid = new GridBase(rowCount, columnCount);

        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();

        rows.addAll(calcResult.getItems());
        rows.addAll(initializeDataRows(calcResult.getHeight()));
        rows.add(initializeCreatorRow(calcResult.getHeight() + viewModel.size()));

        grid.setRows(rows);

        return grid;
    }
    private void initializeSpreadsheet() {
        spreadsheet.setGrid(createGrid());
        spreadsheet.resizeRowsToFitContent();
        var columns = spreadsheet.getColumns();
        spreadsheet.setShowColumnHeader(false);
        spreadsheet.getFixedRows().addAll(0, 1);

        var lastHeaderRow = calcResult.getItems().get(calcResult.getItems().size() - 1);
        var widths = List.of(50D + spreadsheet.getRowHeaderWidth(), 70D, 120D, 140D, 140D);
        for(int i = 0; i < columns.size(); ++i) {
            columns.get(i).setPrefWidth(widths.get(i));
        }
        var total = widths.stream().reduce(0D, Double::sum);
        total += 10;
        spreadsheet.setPrefWidth(total);
//        for(var i = 0; i < columns.size(); ++i) {
//            var item = lastHeaderRow.get(i);
//            var label = (Label) item.getGraphic();
//            final var constId = i;
//            label.widthProperty().addListener((obs, oldVal, newVal) -> {
//                var width = newVal.doubleValue();
//                var options = item.getOptionsForEditor();
//                options.forEach(x -> log.info(String.format("option: %s", x.toString())));
//                log.info(String.format("width: %f, text: %s, celltext: %s", width, label.getText(), item.getText()));
//                columns.get(constId).setPrefWidth(width);
//                log.info(String.format("width: %f, text: %s, celltext: %s", width, label.getText(), item.getText()));
//            });
//        }
    }

    private int selectedCellId;
    private List<SpreadsheetCell> editableCells;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var sp = state.getSurveyPoint();

        viewModel.addListener((ListChangeListener<SoilCorrosionInputDto>) change -> {
            spreadsheet.setGrid(createGrid());
        });

        //viewModel = new ArrayList<>(service.getInputsBySurveyPoint(sp));

//        var model1 = new SoilCorrosionInputDto();
//        model1.setCurrent(3.0F);
//        model1.setCathodeCurrent(2.0F);
//        model1.setVoltage(15.0F);
//        model1.setResistance(5.0F);
//        model1.setSample(state.getSample());
//        model1.setLabNumber("1139");
//
//        var model2 = new SoilCorrosionInputDto();
//        model2.setCurrent(6.0F);
//        model2.setCathodeCurrent(4.0F);
//        model2.setVoltage(15.0F);
//        model2.setResistance(2.5F);
//        model2.setSample(state.getSample());
//        model2.setLabNumber("1140");

        viewModel = FXCollections.observableArrayList(service.getInputsBySample(state.getSample()));

        initializeSpreadsheet();

        var editableCell = spreadsheet
                .getItems()
                .stream()
                .flatMap(Collection::stream)
                .filter(SpreadsheetCell::isEditable)
                .findFirst();

        int rowId = editableCell.get().getRow();
        int columnId = editableCell.get().getColumn();
        var spreadsheetColumn = spreadsheet.getColumns().get(columnId);

        spreadsheet.getSelectionModel().focus(rowId, spreadsheetColumn);
        spreadsheet.getSelectionModel().select(rowId, spreadsheetColumn);
    }

    public void onCalculateRClicked(ActionEvent actionEvent) {
        viewModel.forEach(dto -> service.calculateResistance(dto));
        spreadsheet.setGrid(createGrid());
    }

    public void onCalculateAverageValuesClicked(ActionEvent actionEvent) {
        viewModel.forEach(dto -> service.calculateAverageUesgAndPkt(state.getSample(), dto.getLabNumber()));
    }
}
