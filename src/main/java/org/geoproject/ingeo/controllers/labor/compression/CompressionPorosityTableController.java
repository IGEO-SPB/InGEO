package org.geoproject.ingeo.controllers.laborMethods.compression;

import org.geoproject.ingeo.dto.CompressionDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.services.methodViews.CompressionService;
import org.geoproject.ingeo.spreadsheetlib.SpreadsheetHeader;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CompressionPorosityTableController implements Initializable {

    @FXML
    private SpreadsheetView spreadsheet;

    private ConfigurableApplicationContext applicationContext;
    private CompressionService service;
    private CurrentState state;

    private ObservableList<CompressionDto> viewModel;

    private void initHeaders() {
        var h00000 = new SpreadsheetHeader("0.0000");
        var h00125 = new SpreadsheetHeader("0.0125");
        var h00250 = new SpreadsheetHeader("0.0250");
        var h00500 = new SpreadsheetHeader("0.0500");
        var h00750 = new SpreadsheetHeader("0.0750");
        var h01000 = new SpreadsheetHeader("0.1000");
        var h02000 = new SpreadsheetHeader("0.2000");
        var h03000 = new SpreadsheetHeader("0.3000");
        var h04000 = new SpreadsheetHeader("0.4000");
        var h05000 = new SpreadsheetHeader("0.5000");
        var h06000 = new SpreadsheetHeader("0.6000");
        var h07000 = new SpreadsheetHeader("0.7000");
        var h08000 = new SpreadsheetHeader("0.8000");
        var h09000 = new SpreadsheetHeader("0.9000");
        var h10000 = new SpreadsheetHeader("1.0000");

        var headerMpa = new SpreadsheetHeader("Пористость от нагрузки [МПа->мм]", List.of(
                h00000, h00125, h00250, h00500, h00750, h01000, h02000, h03000, h04000, h05000, h06000, h07000, h08000, h09000, h10000
        ));

        var headerLabNumber = new SpreadsheetHeader("Лаб. Номер");
        var headerPorosityCoefficient = new SpreadsheetHeader("Нач. Коэф. Пористости");
        var headerRingNumber = new SpreadsheetHeader("Номер кольца");
        var headerRingHeight = new SpreadsheetHeader("Высота кольца");

        rootHeaders = List.of(headerLabNumber, headerPorosityCoefficient, headerRingNumber, headerRingHeight, headerMpa);
        calcResult = SpreadsheetHeader.convertToSpreadsheetCellMatrix(rootHeaders.toArray(new SpreadsheetHeader[0]));
    }

    public CompressionPorosityTableController(ConfigurableApplicationContext applicationContext, CompressionService service, CurrentState state) {
        this.applicationContext = applicationContext;
        this.service = service;
        this.state = state;

        initHeaders();
    }

    private List<SpreadsheetHeader> rootHeaders;
    private SpreadsheetHeader.CalculationResult calcResult;

    private SpreadsheetCell createDoubleCell(Property<Double> property, int row, int col) {
        var cell0 = SpreadsheetCellType.DOUBLE.createCell(row, col, 1, 1, property.getValue());
        cell0.textProperty().addListener((observableValue, s, t1) -> {
            property.setValue(Double.parseDouble(t1));
        });
        return cell0;
    }

    private ObservableList<SpreadsheetCell> createDataSpreadsheetRow(CompressionDto dto, int row) {
        var colIter = 0;
        var cell0 = SpreadsheetCellType.STRING.createCell(row, colIter++, 1, 1,dto.getLabNumber());
        var cell1 = SpreadsheetCellType.DOUBLE.createCell(row, colIter++, 1, 1,dto.getPorosityCoefficient());
        var cell2 = SpreadsheetCellType.INTEGER.createCell(row, colIter++, 1, 1,dto.getRingNumber());
        var cell3 = SpreadsheetCellType.DOUBLE.createCell(row, colIter++, 1, 1,dto.getRingHeight());
        cell0.setEditable(false);
        cell1.setEditable(false);
        cell2.setEditable(false);
        cell3.setEditable(false);

        var c1 = createDoubleCell(dto.getItem_00000().porosityProperty(), row, colIter++);
        var c2 = createDoubleCell(dto.getItem_00125().porosityProperty(), row, colIter++);
        var c3 = createDoubleCell(dto.getItem_00250().porosityProperty(), row, colIter++);
        var c4 = createDoubleCell(dto.getItem_00500().porosityProperty(), row, colIter++);
        var c5 = createDoubleCell(dto.getItem_00750().porosityProperty(), row, colIter++);
        var c6 = createDoubleCell(dto.getItem_01000().porosityProperty(), row, colIter++);
        var c7 = createDoubleCell(dto.getItem_02000().porosityProperty(), row, colIter++);
        var c8 = createDoubleCell(dto.getItem_03000().porosityProperty(), row, colIter++);
        var c9 = createDoubleCell(dto.getItem_04000().porosityProperty(), row, colIter++);
        var c10 = createDoubleCell(dto.getItem_05000().porosityProperty(), row, colIter++);
        var c11 = createDoubleCell(dto.getItem_06000().porosityProperty(), row, colIter++);
        var c12 = createDoubleCell(dto.getItem_07000().porosityProperty(), row, colIter++);
        var c13 = createDoubleCell(dto.getItem_08000().porosityProperty(), row, colIter++);
        var c14 = createDoubleCell(dto.getItem_09000().porosityProperty(), row, colIter++);
        var c15 = createDoubleCell(dto.getItem_10000().porosityProperty(), row, colIter++);

        var res = FXCollections.observableArrayList(cell0, cell1, cell2, cell3, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15);
        return res;
    }

    private List<ObservableList<SpreadsheetCell>> initializeDataRows(int startPosition) {
        var res = new ArrayList<ObservableList<SpreadsheetCell>>();
        for(int i = 0; i < viewModel.size(); ++i) {
            res.add(createDataSpreadsheetRow(viewModel.get(i), startPosition + i));
        }

        return res;
    }

    private GridBase createGrid() {
        int rowCount = 3; //Will be re-calculated after if incorrect.
        int columnCount = 5;

        GridBase grid = new GridBase(rowCount, columnCount);

        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();

        rows.addAll(calcResult.getItems());
        rows.addAll(initializeDataRows(calcResult.getHeight()));

        grid.setRows(rows);

        return grid;
    }
    private void initializeSpreadsheet() {
        spreadsheet.setGrid(createGrid());
        spreadsheet.resizeRowsToFitContent();
        var columns = spreadsheet.getColumns();
        spreadsheet.setShowColumnHeader(false);
//        spreadsheet.getFixedRows().addAll(0, 1);
    }

    private int selectedCellId;
    private List<SpreadsheetCell> editableCells;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var sp = state.getSurveyPoint();

        viewModel = FXCollections.observableArrayList(service.getCompressionsForProject(state.getCurrentProject()));
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

    public void onDeformationTableClicked(ActionEvent actionEvent) throws IOException {
        JavaFXCommonMethods.changeScene(actionEvent, ViewsEnum.COMPRESSION_DEFORMATION, applicationContext);
    }

    public void onPorosityTableClicked(ActionEvent actionEvent) throws IOException {
        JavaFXCommonMethods.changeScene(actionEvent, ViewsEnum.COMPRESSION_POROSITY, applicationContext);
    }

    public void onInputFormButtonClicked(ActionEvent actionEvent) throws IOException {
        JavaFXCommonMethods.changeScene(actionEvent, ViewsEnum.COMPRESSION_INPUT, applicationContext);
    }
}
