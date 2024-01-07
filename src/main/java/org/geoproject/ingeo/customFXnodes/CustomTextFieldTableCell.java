package org.geoproject.ingeo.customFXnodes;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.FloatStringConverter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.geoproject.ingeo.controllers.functionalInterfaces.NewRowAddable;
import org.geoproject.ingeo.dto.classificators.AbstractClassificator;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialDto;
import org.geoproject.ingeo.dto.methodDtos.WaterSampleResultDto;
import org.geoproject.ingeo.exceptions.UnpredictableException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.geoproject.ingeo.constants.JavaFXConstants.SINGLE_COLUMN_INDEX;
import static org.geoproject.ingeo.constants.JavaFXConstants.SINGLE_ROW_COUNT;
import static org.geoproject.ingeo.constants.ServiceConstants.SCIENTIFIC_NOTATION_PATTERN;

@Log4j2
public class CustomTextFieldTableCell<K, R, Y> extends TableCell<K, R> {
    private TextField textField;

    private TableColumn<K, R> column;
    private TableView<Y> tableView;
    private ObservableList<Y> observableList;
    private NewRowAddable newRowAddable;
    private List<String> excludeColumnNameList;

    public CustomTextFieldTableCell(TableColumn<K, R> tableColumn, TableView<Y> tableView,
                                    ObservableList<Y> observableList, NewRowAddable newRowAddable,
                                    List<String> excludeColumnNameList) {
        this.column = tableColumn;
        this.tableView = tableView;
        this.observableList = observableList;
        this.newRowAddable = newRowAddable;
        this.excludeColumnNameList = excludeColumnNameList;


        tableView.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (tableView.getScene().getFocusOwner().getId() != null &&
                    !tableView.getScene().getFocusOwner().getId().equals("tableView")) {

                tableView.getFocusModel().focus(tableView.getSelectionModel().getSelectedIndex());
                tableView.getSelectionModel().select(tableView.getSelectionModel().getSelectedIndex());

            }

            if (!newVal) {
                tableView.getSelectionModel().select(tableView.getSelectionModel().getSelectedIndex());

            } else {
                if (tableView.getSelectionModel().getSelectedIndex() >= tableView.getItems().size() - 1) {
                    tableView.getSelectionModel().select(tableView.getSelectionModel().getSelectedIndex());

                }

            }
        });
    }

    /**
     * Метод вызывается в момент двойного клика по ячейке, когда курсор начинает мигать в поле TextField
     * Можно вызвать этот метод, если требуется перевести состояние ячейки из non-editing в editing.
     * <p>
     * Описание родительского метода:
     * Starts an edit to the value of the cell. Call this function to transition from a non-editing state
     * into an editing state, if the cell is editable.
     * If this cell is already in an editing state, it will stay in it.
     */
    @Override
    public void startEdit() {
        log.info("Начинается редактирование ячейки");
        super.startEdit();
        if (this.textField == null) {
            this.createTextField();
        }

        this.textField.setText(textField.getText() == null ? "" : this.getString());

        super.setGraphic(this.textField);
        super.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        /**
         * Вызывается в момент перехода из ячейки (focus out)
         */
        Platform.runLater(() -> {
            this.textField.requestFocus();
            this.textField.selectAll();
        });
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        super.setText(this.getString());
        super.setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    protected void updateItem(R item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
//                    super.setText(StringUtils.EMPTY);
            super.setText(null);
            super.setGraphic(null);
        } else {
            String text;
            boolean isScientificNotation = this.getString().matches(SCIENTIFIC_NOTATION_PATTERN);

            if (isScientificNotation) {
                log.info("Значение в научной нотации");

                double doubleValue = Double.parseDouble(this.getString());

                DecimalFormat decimalFormat = new DecimalFormat("0.##################");

                text = decimalFormat.format(doubleValue);

                text = text.replace(',', '.');

            } else {
                log.info("Value is not in scientific notation");

                text = this.getString();
            }

            if (!column.getId().toLowerCase().matches(".*number.*")) {
                try {
                    BigDecimal num = new BigDecimal(Float.parseFloat(text));

//                            String numWithNoExponents = num.toPlainString();

                    super.setText(String.format("%.2f", num));
                } catch (Exception e) {
                    log.warn(e.getMessage());
                    super.setText(text);
                }
            } else {
                super.setText(text);
            }

            super.setGraphic(this.textField);
            super.setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
    }

    private String getString() {
        return super.getItem() == null ? StringUtils.EMPTY : String.valueOf(super.getItem());
    }

    private void createTextField() {
        this.textField = new TextField(getString());

        this.textField.setMinWidth(super.getWidth() - super.getGraphicTextGap() * 2);

        this.textField.setOnKeyPressed(this::handleKeyEvent);

        this.textField.focusedProperty().addListener((observable, oldValue, isFocused) -> this.handleFocusChange(isFocused));
    }

    private void handleKeyEvent(KeyEvent keyEvent) {
        int rowIndex = tableView.getSelectionModel().getSelectedIndex();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            log.info("ENTER pressed");

            ChangeListener<? super Boolean> listener = (observable, oldValue, isFocused) -> {
                if (isFocused) {
                    commitEditOrConvert();

                    Platform.runLater(textField::selectAll);
                } else if (!isFocused && textField != null) {
                    commitEditOrConvert();
                }
            };

            commitEditOrConvert();

            //TODO: в условие необходимо добавлять типы дто, которые мапятся на таблицы,
            // в тех случаях, когда по Enter требуется добавлять строку:

//            if (!observableList.isEmpty()) {
//                if ((observableList.get(0) instanceof AbstractClassificator ||
//                        observableList.get(0) instanceof WaterSampleResultDto ||
//                        observableList.get(0) instanceof WaterExtractPartialDto ||
//                        observableList.get(0) instanceof WaterExtractFullDto ||
//                        observableList.get(0) instanceof EgeDto) &&
//                        observableList.size() == rowIndex + SINGLE_ROW_COUNT) {
//                    if (Objects.nonNull(newRowAddable)) {
//                        newRowAddable.add();
//                    }
//                }
//            }

            if (!tableView.getItems().isEmpty()) {
                if ((tableView.getItems().get(0) instanceof AbstractClassificator ||
                        tableView.getItems().get(0) instanceof WaterSampleResultDto ||
                        tableView.getItems().get(0) instanceof WaterExtractPartialDto ||
                        tableView.getItems().get(0) instanceof WaterExtractFullDto ||
                        tableView.getItems().get(0) instanceof EgeDto) &&
                        tableView.getItems().size() == rowIndex + SINGLE_ROW_COUNT) {
                    if (Objects.nonNull(newRowAddable)) {
                        newRowAddable.add();
                    }
                }
            }

            if (rowIndex < tableView.getItems().size() - 1) {
                TableColumn<Y, ?> nextColumn = getEditableColumns().get(0);
                selectAndEditCell(tableView, rowIndex + SINGLE_COLUMN_INDEX, nextColumn);
            } else {
                //Если редактируем последнюю строку, то вместо перехода на следующую выделяется вся текущая строка целиком
            }
        } else if (keyEvent.getCode() == KeyCode.TAB) {
            log.info("Нажата клавиша TAB");

            /**
             * !!!Желательно не называть колонки "number" кроме классификаторов
             * Если название содержит "number" надо добавить его в исключения
             */
            if (column.getId().toLowerCase().contains("number") &&
                    !column.getId().equalsIgnoreCase("egenumber") &&
                    !column.getId().equalsIgnoreCase("labornumber") &&
                    !column.getId().equalsIgnoreCase("surveyPointNumber")) {
                List<String> numbers = observableList.stream()
                        .map(container -> ((AbstractClassificator) container).getNumber())
                        .toList();

                if (checkIfNumberExistInDataBase(textField.getText(), numbers)) {
                    this.cancelEdit();
                }
            }

            commitEditOrConvert();

            TableColumn<Y, ?> nextColumn = getNextColumn(!keyEvent.isShiftDown());

            selectAndEditCell(tableView, rowIndex, nextColumn);

        } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
            this.cancelEdit();
        }
    }

    private void selectAndEditCell(TableView<Y> tableView, int rowIndex, TableColumn<Y, ?> nextColumn) {
        tableView.layout();
        tableView.layout();
        tableView.getSelectionModel().select(rowIndex, nextColumn);
        tableView.edit(rowIndex, nextColumn);
        tableView.scrollToColumn(nextColumn);
    }

//            public void setDatePickerForDates() {
//                Map<DatePicker, String> datePickerMap = new HashMap<>();
//
//                datePickerMap.put(assignmentDate, assignmentDate.getId());
//                datePickerMap.put(startDate, startDate.getId());
//                datePickerMap.put(endDate, endDate.getId());
//
//                datePickerMap.forEach((datePickerField, fieldName) ->
//                        datePickerField.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//                            dto.setFieldValue(ProjectDTOFieldsEnum.getEnumByName(fieldName), newValue);
//                        }));
//            }

    private void commitEditOrConvert() {

        System.out.println("commitEditOrConvert - 1");

        if (textField.getText().equals(StringUtils.EMPTY)) {
            System.out.println("commitEditOrConvert - 2");

            commitEdit(null);
        }

        try {
            System.out.println("commitEditOrConvert - 3");
            System.out.println(textField.getText());
//                    setEditing(Boolean.TRUE);
//                    startEdit();

            R cellData = column.getCellData(getIndex());

            if (cellData instanceof Float) {
                throw new ClassCastException();
            }

            R text = (R) textField.getText();

            commitEdit(text);
        } catch (ClassCastException e) {
            try {
                System.out.println("commitEditOrConvert - 4");

                commitEdit((R) new FloatStringConverter().fromString(textField.getText()));
            } catch (NumberFormatException exception) {
                try {
                    System.out.println("commitEditOrConvert - 5");

                    commitEdit((R) Boolean.valueOf(textField.getText()));
                } catch (ClassCastException e1) {

                    if (textField.getText().matches(".*[-\\.].*[-\\.].*")) {
                        log.info("This is date!!!");
                        try {
                            System.out.println("commitEditOrConvert - 6");

                            String[] split = textField.getText().split("-");
                            commitEdit((R) LocalDate.of(Integer.parseInt(split[0]),
                                    Integer.parseInt(split[1]),
                                    Integer.parseInt(split[2])));
                        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e2) {
                            System.out.println("commitEditOrConvert - 7");

                            String[] split = textField.getText().split("\\.");
                            commitEdit((R) LocalDate.of(Integer.parseInt(split[2]),
                                    Integer.parseInt(split[1]),
                                    Integer.parseInt(split[0])));
                        }
                    } else if (textField.getText().matches(".*[,\\.].*")) {
                        System.out.println("commitEditOrConvert - 8");

                        var text = textField.getText().replace(',', '.');

                        commitEdit((R) new FloatStringConverter().fromString(text));
                    } else {
                        System.out.println("commitEditOrConvert - 9");

                        throw new UnpredictableException("CustomTextFieldTableCell - private commitEditOrConvert() - string 394");
                    }
                }
            }
        }
    }

    @Override
    public void commitEdit(R item) {

        if (isEditing()) {
            super.commitEdit(item);
        } else {
            final TableView table = getTableView();
            if (table != null) {
                TablePosition position = new TablePosition(getTableView(), getTableRow().getIndex(), getTableColumn());
                TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(table, position, javafx.scene.control.TableColumn.editCommitEvent(), item);

                Event.fireEvent(getTableColumn(), editEvent);
            }

            updateItem(item, false);

//            if (table != null) {
//            if (table == null) {
//                table.edit(-1, null);
//            }

        }
    }

    /**
     * Метод добавлен для сохранения данных, введенных в ячейку TableView в этой же ячейке,
     * при клике мышкой вне редактируемой ячейки.
     *
     * @param isFocused признак наличия фокуса на ячейке TableView
     */
    private void handleFocusChange(Boolean isFocused) {
        if (isFocused) {
            Platform.runLater(textField::selectAll);
        } else if (!isFocused && textField != null) {
            commitEditOrConvert();
        }
    }

    private TableColumn<Y, ?> getNextColumn(Boolean forward) {
        List<TableColumn<Y, ?>> columns = getEditableColumns();

        if (columns.size() < 2) {
            return null;
        }
        int currentIndex = columns.indexOf(this.getTableColumn());

        int nextIndex = forward ? currentIndex + SINGLE_COLUMN_INDEX : currentIndex - SINGLE_COLUMN_INDEX;

        if (forward && nextIndex > columns.size() - 1) {
            nextIndex = 0;
        } else if (!forward && currentIndex < 0) {
            nextIndex = columns.size() - SINGLE_COLUMN_INDEX;
        }

        return columns.get(nextIndex);
    }

    private List<TableColumn<Y, ?>> getEditableColumns() {
        List<TableColumn<Y, ?>> columns = new ArrayList<>();
        for (var column : tableView.getColumns()) {
            columns.addAll(getEditableLeaves((TableColumn<Y, ?>) column));
        }

        return columns;
    }

    private List<TableColumn<Y, ?>> getEditableLeaves(TableColumn<Y, ?> tableColumn) {
        List<TableColumn<Y, ?>> columns = new ArrayList<>();

        if (tableColumn.getColumns().isEmpty()) {

            if (tableColumn.isEditable() &&
                    (excludeColumnNameList == null || !excludeColumnNameList.contains(tableColumn.getId()))) {
                columns.add(tableColumn);
            }
            return columns;
        } else {
            for (TableColumn<Y, ?> column : tableColumn.getColumns()) {
                columns.addAll(getEditableLeaves(column));
            }

            return columns;
        }
    }

    private static boolean checkIfNumberExistInDataBase(String number,
                                                        List<String> numbers) {
        return numbers.contains(number);
    }
}
