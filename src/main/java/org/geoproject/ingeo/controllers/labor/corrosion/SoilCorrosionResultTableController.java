package org.geoproject.ingeo.controllers.labor.corrosion;

import org.geoproject.ingeo.dto.PrintInfoDto;
import org.geoproject.ingeo.dto.methodDtos.SoilCorrosionResultDto;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.labor.SoilCorrosionService;
import org.geoproject.ingeo.spreadsheetlib.SpreadsheetHeader;
import org.geoproject.ingeo.utils.CurrentState;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
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
import java.util.Objects;
import java.util.ResourceBundle;

@CommonsLog
@Component
public class SoilCorrosionResultTableController implements Initializable {
    public SoilCorrosionService service;
    public SampleService sampleService;
    public CurrentState state;

    @FXML
    public TextField printTablePageNumber;
    @FXML
    public TextField printTableCreatorFullName;
    @FXML
    public TextField printTableAttachmentNumber;

    public ObservableList<SoilCorrosionResultDto> viewModel = FXCollections.observableArrayList();

    @FXML
    public SpreadsheetView spreadsheet;

    public void onPrintClicked(ActionEvent actionEvent) {
        service.printCorrosionAggressionTable(new PrintInfoDto(), state.getSample());
    }

    public void onCalculateClicked(ActionEvent actionEvent) {
        for(var soilCorrosion: viewModel) {
            service.calculateCorrosionAggressionDegree(soilCorrosion);
        }

        spreadsheet.setGrid(createGrid());
    }

    private SpreadsheetHeader.CalculationResult calcResult;

    private void initRootSpreadsheetHeaders() {
        var header0 = new SpreadsheetHeader("Лаб. №");
        var header1 = new SpreadsheetHeader("УЭСГ, [Ом*м]");
        var header2 = new SpreadsheetHeader("Степень корр. агр. по УЭСГ");
        var header3 = new SpreadsheetHeader("ПКТ, [мА/м^2]");
        var header4 = new SpreadsheetHeader("Степень корр. агр. по ПКТ");

        var rootHeaders = List.of(header0, header1, header2, header3, header4);
        calcResult = SpreadsheetHeader.convertToSpreadsheetCellMatrix(rootHeaders.toArray(new SpreadsheetHeader[0]));
    }
    public SoilCorrosionResultTableController(SoilCorrosionService soilCorrosionService, CurrentState currentState, SampleService sampleService) {
        log.info("init");
        log.info(soilCorrosionService.toString());
        log.info(currentState.toString());
        log.info(sampleService.toString());
        //log.info("init");
        this.service = soilCorrosionService;
        this.state = currentState;
        this.sampleService = sampleService;

        initRootSpreadsheetHeaders();
    }


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
                .filter(x -> viewModel.stream().noneMatch(y -> Objects.equals(y.getLabNumber(), x)))
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
                var newItems = service.calculateAverageUesgAndPkt(state.getSample(), t1);
                viewModel.setAll(newItems);
                spreadsheet.setGrid(createGrid());
            }
        });
        return cell;
    }

    private SpreadsheetCell createStringCell(StringProperty property, int row, int col) {
        var cell0 = SpreadsheetCellType.STRING.createCell(row, col, 1, 1, property.getValue());
        cell0.textProperty().addListener((observableValue, s, t1) -> {
            property.setValue(t1);
        });
        return cell0;
    }

    private SpreadsheetCell createDoubleCell(FloatProperty property, int row, int col) {
        var cell0 = SpreadsheetCellType.DOUBLE.createCell(row, col, 1, 1, property.doubleValue());
        cell0.textProperty().addListener((observableValue, s, t1) -> {
            property.setValue(Double.parseDouble(t1));
        });
        return cell0;
    }

    private ObservableList<SpreadsheetCell> createDataSpreadsheetRow(SoilCorrosionResultDto dto, int row) {
        var cell0 = SpreadsheetCellType.STRING.createCell(row, 0, 1, 1,dto.getLabNumber());
        cell0.setEditable(false);
        var cell1 = createDoubleCell(dto.uesgProperty(), row, 1);
        var cell2 = createStringCell(dto.corrAggrDegreeUesgProperty(), row, 2);
        var cell3 = createDoubleCell(dto.pktProperty(), row, 3);
        var cell4 = createStringCell(dto.corrAggrDegreePktProperty(), row, 4);

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
        log.info("grid");
        int rowCount = 3; //Will be re-calculated after if incorrect.
        int columnCount = 5;

        GridBase grid = new GridBase(rowCount, columnCount);

        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();

        var headerItems = calcResult.getItems();
        headerItems.stream().flatMap(Collection::stream).forEach(x -> x.setWrapText(true));

        rows.addAll(headerItems);
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
        spreadsheet.getFixedRows().addAll(0);
//        spreadsheet.addEventHandler(new EventType<KeyEvent>(), keyEvent -> {
//            var backspace = 8;
//            var del = 46;
//            var keyCode = keyEvent.getCode().getCode();
//            if(keyCode == backspace || keyCode == del) {
//                spreadsheet.getSelectionModel().
//            }
//
//        });
        var lastHeaderRow = calcResult.getItems().get(calcResult.getItems().size() - 1);
        var widths = List.of(50D + spreadsheet.getRowHeaderWidth(), 90D, 170D, 100D, 160D);
        for(int i = 0; i < columns.size(); ++i) {
            columns.get(i).setPrefWidth(widths.get(i));
        }
        var total = widths.stream().reduce(0D, Double::sum);
        total += 10;
        spreadsheet.setPrefWidth(total);
    }

    private int selectedCellId;
    private List<SpreadsheetCell> editableCells;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("init");
        viewModel = FXCollections.observableArrayList(service.getOrCreateResultBySample(state.getSample()));

        viewModel.addListener((ListChangeListener<SoilCorrosionResultDto>) change -> {
            spreadsheet.setGrid(createGrid());
        });

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
}
