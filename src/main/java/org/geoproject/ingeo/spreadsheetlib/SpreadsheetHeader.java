package com.geoproject.igeo.spreadsheetlib;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class SpreadsheetHeader {
    @Getter
    private String text;
    @Getter
    private List<SpreadsheetHeader> children;
    @Getter
    private int preferredHeight = 1;
    @Getter
    private int actualHeight;
    @Getter
    private int actualWidth = 1;

    public SpreadsheetHeader(String text)
    {
        this.text = text;
        this.children = List.of();
    }
    public SpreadsheetHeader(String text, List<SpreadsheetHeader> children)
    {
        this.text = text;
        this.children = children;
    }
    public SpreadsheetHeader(String text, List<SpreadsheetHeader> children, int height)
    {
        this.text = text;
        this.children = children;
        this.preferredHeight = Math.max(1, height);
    }


    private int calculateWidth() {
        if(children.isEmpty()) actualWidth = 1;
        else actualWidth = children.stream()
                .map(SpreadsheetHeader::getActualWidth)
                .reduce(0, Integer::sum);

        return actualWidth;
    }

    private int calculateMaxHeight() {
        if(children.isEmpty()) actualHeight = preferredHeight;
        else actualHeight = preferredHeight + children.stream()
                .map(SpreadsheetHeader::calculateMaxHeight)
                .reduce(0, Integer::max);

        return actualHeight;
    }

    private static void buildSpreadsheetCells(List<ObservableList<SpreadsheetCell>> matrix, int rowStart, int colStart, List<SpreadsheetHeader> headers) {
        int colIter = colStart;

        for(var header : headers) {
            final int colSpan = header.getActualWidth();
            final int rowSpan = header.getPreferredHeight();

            for(var row = rowStart; row - rowStart < rowSpan; ++row) {
                for(var col = colIter; col - colIter < colSpan; ++col) {
                    var cell = new SpreadsheetCellBase(row, colIter, rowSpan, colSpan);
                    var textItem = new Label(header.getText());
                    textItem.setTextAlignment(TextAlignment.CENTER);
                    textItem.setStyle("-fx-font-weight: bold");
                    textItem.setMaxWidth(Double.MAX_VALUE);
                    textItem.setMaxWidth(Double.MAX_VALUE);
                    var width = textItem.getWidth();
                    cell.setGraphic(textItem);
                    cell.setEditable(false);
                    //var cell = SpreadsheetCellType.STRING.createCell(row, colIter, rowSpan, colSpan, header.getText());
                    //cell.setEditable(false);
                    //cell.setWrapText(true);
                    cell.setStyle("-fx-background-color: #aaaaaa");
                    //cell.setStyle("-fx-text-align: center");
                    matrix.get(row).set(col, cell);
                }
            }
            buildSpreadsheetCells(matrix, rowStart + rowSpan, colIter, header.getChildren());
            colIter += colSpan;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class CalculationResult {
        private List<ObservableList<SpreadsheetCell>> items;
        private int width;
        private int height;
    }

    public static CalculationResult convertToSpreadsheetCellMatrix(SpreadsheetHeader... children) {

        var maxHeight = 0;
        var totalWidth = 0;

        for(var header : children) {
            totalWidth += header.calculateWidth();
            maxHeight = Math.max(maxHeight, header.calculateMaxHeight());
        }

        List<ObservableList<SpreadsheetCell>> matrix = new ArrayList<>();
        for(int i = 0; i < maxHeight; ++i) {
            final var row = i; // for passing variable into the lambda
            var cells = IntStream.range(0, totalWidth)
                    .mapToObj(col -> SpreadsheetCellType.STRING.createCell(row, col, 1, 1, ""))
                    .toList();
            matrix.add(FXCollections.observableArrayList(cells));
        }

        buildSpreadsheetCells(matrix, 0, 0, List.of(children));

        return new CalculationResult(matrix, totalWidth, matrix.size());
    }

    public static void adjustGridToLastHeaders(Grid grid) {

    }

}