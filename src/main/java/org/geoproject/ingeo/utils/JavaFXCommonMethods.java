package org.geoproject.ingeo.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.TablePosition;
import org.geoproject.ingeo.controllers.functionalInterfaces.EntitiesUpdateable;
import org.geoproject.ingeo.controllers.functionalInterfaces.NewRowAddable;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialDto;
import org.geoproject.ingeo.dto.methodDtos.WaterSampleResultDto;
import org.geoproject.ingeo.dto.classificators.AbstractClassificator;
import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.exceptions.UnpredictableException;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.classificators.Pot;
import org.geoproject.ingeo.models.classificators.Ring;
import org.geoproject.ingeo.models.classificators.WeighingBottle;
import org.geoproject.ingeo.models.classificators.kga.Color;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.models.classificators.kga.SoilGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.geoproject.ingeo.services.classificators.ClassificatorService;
import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.LongFunction;

import static org.geoproject.ingeo.constants.JavaFXConstants.FLOAT_SPLIT_PATTERN;
import static org.geoproject.ingeo.constants.JavaFXConstants.IS_FLOAT_VALUE_PATTERN;
import static org.geoproject.ingeo.constants.JavaFXConstants.SINGLE_COLUMN_INDEX;
import static org.geoproject.ingeo.constants.JavaFXConstants.SINGLE_ROW_COUNT;
import static org.geoproject.ingeo.constants.JavaFXConstants.TEXTFIELD_NUMERIC_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.ALERT_WINDOW_TITLE;
import static org.geoproject.ingeo.constants.ServiceConstants.SCIENTIFIC_NOTATION_PATTERN;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JavaFXCommonMethods {

    /**
     * @param event              What triggered this action
     * @param scenePath          Path to the scene
     * @param applicationContext
     * @param title              Title for the scene
     * @throws IOException
     */
    @Getter
    private static ViewsEnum currentView;

    public static void changeScene(ActionEvent event, String scenePath, ApplicationContext applicationContext,
                                   String title) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        changeScene(stage, scenePath, applicationContext, title);
    }

    public static void tryChangeScene(ActionEvent event, String scenePath, ApplicationContext applicationContext,
                                   String title) {
        try {
            changeScene(event, scenePath, applicationContext, title);
        } catch (IOException ignored) {
            log.info("could not change scene");
        }
    }

    public static void changeScene(Stage stage, String scenePath, ApplicationContext applicationContext,
                                   String title) throws IOException {
        URL url = JavaFXCommonMethods.class.getClassLoader().getResource("org.geoproject.ingeo/" + scenePath);
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        fxmlLoader.setControllerFactory(applicationContext::getBean);

        double width = 800;
        double height = 500;
        if (stage.getScene() != null) {
            width = stage.getScene().widthProperty().get();
            height = stage.getScene().heightProperty().get();
        }
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    public static void changeScene(ActionEvent event, ViewsEnum view, ApplicationContext applicationContext) throws IOException {
        changeScene(event, view.getPath(), applicationContext, view.getTitle());
        currentView = view;
    }

    public static void changeScene(Stage stage, ViewsEnum path, ApplicationContext applicationContext,
                                   StageTitleEnum title) throws IOException {
        changeScene(stage, path.getPath(), applicationContext, title.getTitle());
    }

    public static void changeSceneToModalWindow(ActionEvent event, String scenePath, ApplicationContext applicationContext,
                                                Stage stage, String title) throws IOException {
        System.out.println("Change scene to " + title);

        Stage dialog = new Stage();
        dialog.setTitle(title);

        URL url = JavaFXCommonMethods.class.getClassLoader().getResource("org.geoproject.ingeo/" + scenePath);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setControllerFactory(applicationContext::getBean);

        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        dialog.setScene(scene);

        dialog.initOwner(stage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }

    /**
     * Устанавливает элементы футера на представлении
     */
    public static void setFooterElements(CurrentState currentState, Label projectNameInFooter, Label projectCipherInFooter) {
        Project currentProject = currentState.getCurrentProject();
        String projectNameInFooterText = currentProject == null ? "Проект не выбран" : currentProject.getProjectName();
        String projectCipherInFooterText = currentProject == null ? "Проект не выбран" : currentProject.getContractNumber();
        projectNameInFooter.setText(projectNameInFooterText);
        projectCipherInFooter.setText(projectCipherInFooterText);
    }

    public static StringConverter<Number> getConverter() {
        return new StringConverter<>() {

            @Override
            public Float fromString(String value) {
                if (value == null) {
                    return null;
                }

                value = value.trim();

                if (value.length() < 1) {
                    return null;
                }

                if (value.contains(",")) {
                    return Float.valueOf(value.replace(',', '.'));
                }

                return Float.valueOf(value);
            }

            @Override
            public String toString(Number value) {
                if (value == null) {
                    return StringUtils.EMPTY;
                }
                if (Float.toString(value.floatValue()).matches(IS_FLOAT_VALUE_PATTERN)) {

                    String[] array = Float.toString(value.floatValue()).split(FLOAT_SPLIT_PATTERN);

                    if (array.length > 0) {
                        return array[0];
                    }
                }

                return Float.toString(value.floatValue());
            }
        };
    }

    public static <K, R, Y> void setCellFactory(TableColumn<K, R> tableColumn, TableView<Y> tableView,
                                                ObservableList<Y> observableList, NewRowAddable newRowAddable,
                                                ArrayList<String> excludeColumnNameList) {
        tableColumn.setCellFactory(column -> new TableCell<K, R>() {
            private TextField textField;

            @Override
            public void startEdit() {
                super.startEdit();
                if (this.textField == null) {
                    this.createTextField();
                }

                this.textField.setText(textField.getText() == null ? "" : this.getString());

                super.setGraphic(this.textField);
                super.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

                System.out.println("SET RUNNABLE:");
                Platform.runLater(() -> {
                    System.out.println("INSIDE RUNNABLE:");

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
                    super.setText(StringUtils.EMPTY);
                    super.setGraphic(null);
                } else {
                    String text;
                    boolean isScientificNotation = this.getString().matches(SCIENTIFIC_NOTATION_PATTERN);

                    if (isScientificNotation) {
                        log.info("Value is in scientific notation");

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

//                this.textField.focusedProperty().addListener((observable, oldValue, isFocused) -> this.handleFocusChange(isFocused));

                textField.focusedProperty().addListener((arg0, arg1, arg2) -> {
                    if (!arg2) {
                        setText(textField.getText());
                        commitEditOrConvert();
                    }
                });
            }

            private void handleKeyEvent(KeyEvent keyEvent) {
                int rowIndex = tableView.getSelectionModel().getSelectedIndex();
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    log.info("ENTER pressed");

                    commitEditOrConvert();

                    //TODO: в условие необходимо добавлять типы дто, которые мапятся на таблицы,
                    // в тех случаях, когда по Enter требуется добавлять строку:

                    if ((observableList.get(0) instanceof AbstractClassificator ||
                            observableList.get(0) instanceof WaterSampleResultDto ||
                            observableList.get(0) instanceof WaterExtractPartialDto ||
                            observableList.get(0) instanceof WaterExtractFullDto ||
                            observableList.get(0) instanceof EgeDto
                    ) &&
                            observableList.size() == rowIndex + SINGLE_ROW_COUNT) {
                        if (Objects.nonNull(newRowAddable)) {
                            newRowAddable.add();
                        }
                    }

                    TableColumn<Y, ?> nextColumn = getEditableColumns().get(0);
                    selectAndEditCell(tableView, rowIndex + SINGLE_COLUMN_INDEX, nextColumn);

                } else if (keyEvent.getCode() == KeyCode.TAB) {
                    log.info("TAB pressed");
                    //!!!Желательно не называть колонки "number" кроме классификаторов
                    //Если название содержит "number" надо добавить его в исключения
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

                System.out.println("1");

                if (textField.getText().equals(StringUtils.EMPTY)) {
                    System.out.println("2");

                    commitEdit(null);
                }

                try {
                    System.out.println("3");
                    System.out.println(textField.getText());
//                    setEditing(Boolean.TRUE);
//                    startEdit();
                    commitEdit((R) textField.getText());
                } catch (ClassCastException e) {
                    try {
                        System.out.println("4");

                        commitEdit((R) new FloatStringConverter().fromString(textField.getText()));
                    } catch (NumberFormatException exception) {
                        try {
                            System.out.println("5");

                            commitEdit((R) Boolean.valueOf(textField.getText()));
                        } catch (ClassCastException e1) {

                            if (textField.getText().matches(".*[-\\.].*[-\\.].*")) {
                                log.info("This is date!!!");
                                try {
                                    System.out.println("6");

                                    String[] split = textField.getText().split("-");
                                    commitEdit((R) LocalDate.of(Integer.parseInt(split[0]),
                                            Integer.parseInt(split[1]),
                                            Integer.parseInt(split[2])));
                                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e2) {
                                    System.out.println("7");

                                    String[] split = textField.getText().split("\\.");
                                    commitEdit((R) LocalDate.of(Integer.parseInt(split[2]),
                                            Integer.parseInt(split[1]),
                                            Integer.parseInt(split[0])));
                                }
                            }
                            else if (textField.getText().matches(".*[,\\.].*")) {
                                System.out.println("8");

                                var text = textField.getText().replace(',', '.');

                                commitEdit((R) new FloatStringConverter().fromString(text));
                            }
                            else {
                                System.out.println("9");

                                throw new UnpredictableException("JavaFXCommonMethods - private commitEditOrConvert() - string 388");
                            }
//                            commitEdit((R) textField.getText());
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
                        TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(table, position, TableColumn.editCommitEvent(), item);

                        Event.fireEvent(getTableColumn(), editEvent);
                    }
                    updateItem(item, false);

                    if (table != null) {
                        table.edit(-1, null);
                    }

                }
            }

//            private void handleFocusChange(Boolean isFocused) {
//                if (isFocused) {
//                    Platform.runLater(textField::selectAll);
//                } else if (!isFocused && textField != null) {
//                    commitEditOrConvert();
//                }
//            }

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
        });
    }

    private static boolean checkIfNumberExistInDataBase(String number,
                                                        List<String> numbers) {
        return numbers.contains(number);
    }

    public static <Y> List<TableColumn<Y, ?>> getAllLeaves(TableColumn<Y, ?> root) {
        List<TableColumn<Y, ?>> columns = new ArrayList<>();
        if (root.getColumns().isEmpty()) {
            columns.add(root);
            return columns;
        } else {
            for (TableColumn<Y, ?> column : root.getColumns()) {
                columns.addAll(getAllLeaves(column));
            }

            return columns;
        }
    }

    public static void setFieldBinding(Map<TextField, Property> editableTextFieldMap) {

        editableTextFieldMap.forEach((textField, property) -> {
            if (property instanceof FloatProperty) {
                textField.textProperty().bindBidirectional(property, JavaFXCommonMethods.getConverter());
            } else {
                textField.textProperty().bindBidirectional(property);
                textField.commitValue();
            }
        });
    }

    public static void setControlFieldBinding(Map<Control, Property> editableTextFieldMap) {

        editableTextFieldMap.forEach((field, property) -> {
            if (property instanceof FloatProperty) {
//                field.textProperty().bindBidirectional(property, JavaFXCommonMethods.getConverter());
                ((TextField) field).textProperty().bindBidirectional(property, JavaFXCommonMethods.getConverter());
            } else {
                if (field instanceof ChoiceBox<?>) {
                    ((ChoiceBox) field).valueProperty().bindBidirectional(property);
                } else if (field instanceof DatePicker datePicker) {
                    datePicker.valueProperty().bindBidirectional(property);
                } else {
                    ((TextField) field).textProperty().bindBidirectional(property);
                    ((TextField) field).commitValue();
                }
            }
        });
    }

    public static <T, Y> void addListenerOnTextField(TextField textField, ClassificatorService<T, Y> classificatorService,
                                                     EntitiesUpdateable entitiesUpdateable) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            log.warn("oldValue: " + oldValue);
            log.warn("newValue: " + newValue);

            if (textField.getId().contains("Number")) {

                if (newValue == null || newValue.length() == 0) {
                    log.warn("Номер контейнера не заполнен");
                } else {
                    if (textField.getId().toLowerCase().contains("pot")) {
                        addListenerOnPotNumberField(textField, classificatorService, newValue);
                    } else if (textField.getId().toLowerCase().contains("weighingbottle")) {

                        addListenerOnWeighingBottleNumberField(textField, classificatorService, newValue);
                    } else if (textField.getId().toLowerCase().contains("ring")) {

                        addListenerOnRingNumberField(textField, classificatorService, newValue);
                    }
                }
            }
//            else if ((textField.getId().contains("Empty") && textField.getId().contains("Mass")) ||
//                    textField.getId().contains("Volume")) {
//
//                Scene currentScene = textField.getScene();
//
//                if (textField.getId().contains("Weighing")) {
//
//                    String textFieldId = textField.getId();
//
//                    String[] splittedIdArray = textFieldId.split("[Ee]mptyWeighingBottleMass");
//
//                    String numberTextFieldId;
//
//                    if (splittedIdArray.length == 2) {
//                        numberTextFieldId = "#" + splittedIdArray[0] + "WeighingBottleNumber" + splittedIdArray[1];
//                    } else {
//                        numberTextFieldId = "#" + "weighingBottleNumber";
//                    }
//
//                    if (Objects.nonNull(currentScene) && !currentScene.getFocusOwner().getId().contains("Number")) {
//                        log.info("Focus owner ID: " + currentScene.getFocusOwner().getId());
//
//                        TextField numberTextField = (TextField) currentScene.lookup(numberTextFieldId);
//
//
//                        Platform.runLater(() -> {
////                            textField.clear();
////                        numberTextField.setText(StringUtils.EMPTY);
//                        numberTextField.clear();
//                        });
//                    }
//
//                } else if (textField.getId().contains("pot")) {
//                    if (Objects.nonNull(currentScene) && !currentScene.getFocusOwner().getId().contains("Number")) {
//                        TextField massTextField = (TextField) currentScene.lookup("#potNumber");
//
//                        massTextField.setText(StringUtils.EMPTY);
//                    }
//                } else if (textField.getId().contains("Ring")) {
//                    log.info("CONTAINS RING!");
//                    if (textField.getId().contains("Mass")) {
//                        log.info("MASS");
//                        String ringMassTextFieldId = textField.getId();
//                        String[] splittedIdArray = ringMassTextFieldId.split("EmptyMass");
//
//                        System.out.println("splittedIdArray: " + Arrays.toString(splittedIdArray));
//
//                        String suffix;
//
//                        try {
//                            suffix = splittedIdArray[1];
//                        } catch (Exception e) {
//                            log.warn(e.getMessage());
//                            suffix = StringUtils.EMPTY;
//                        }
//
//                        String numberTextFieldId = "#" + splittedIdArray[0] + "Number" + suffix;
//
//                        log.info("numberTextFieldId: " + numberTextFieldId);
//
//                        if (Objects.nonNull(currentScene) && !currentScene.getFocusOwner().getId().contains("Number")) {
//                            TextField numberTextField = (TextField) currentScene.lookup(numberTextFieldId);
//
//                            if (Objects.isNull(numberTextField)) {
//                                splittedIdArray = ringMassTextFieldId.split("EmptyRingMass");
//
//                                numberTextFieldId = "#" + splittedIdArray[0] + "RingNumber" + splittedIdArray[1];
//                                numberTextField = (TextField) currentScene.lookup(numberTextFieldId);
//                            }
//
//                            log.info("numberTextField: " + numberTextField);
//
//                            numberTextField.setText(StringUtils.EMPTY);
//                        }
//
//                    } else if (textField.getId().contains("Volume")) {
//                        log.info("VOLUME");
//
//                        String ringVolumeTextFieldId = textField.getId();
//                        String[] splittedIdArray = ringVolumeTextFieldId.split("Volume");
//
//                        String numberTextFieldId = "#" + splittedIdArray[0] + "Number" + splittedIdArray[1];
//
//                        if (Objects.nonNull(currentScene) && !currentScene.getFocusOwner().getId().contains("Number")) {
//                            TextField numberTextField = (TextField) currentScene.lookup(numberTextFieldId);
//
//                            numberTextField.setText(StringUtils.EMPTY);
//                        }
//                    }
//
//                }
//            }
            else {
                if (newValue == null || newValue.length() == 0) {
                    log.warn("Введена пустая строка. Введите число");
                    textField.setText("");
                } else if (!newValue.matches(TEXTFIELD_NUMERIC_PATTERN)) {
                    log.warn("Not numeric!");
                } else if (textField.isFocused()) {
                    textField.setText(newValue);
                }
            }

            if ((textField.getId().contains("Empty") && textField.getId().contains("Mass")) ||
                    textField.getId().contains("Volume")) {

                Scene currentScene = textField.getScene();

                if (textField.getId().contains("Weighing")) {

                    String textFieldId = textField.getId();

                    String[] splittedIdArray = textFieldId.split("[Ee]mptyWeighingBottleMass");

                    String numberTextFieldId;

                    if (splittedIdArray.length == 2) {
                        numberTextFieldId = "#" + splittedIdArray[0] + "WeighingBottleNumber" + splittedIdArray[1];
                    } else {
                        numberTextFieldId = "#" + "weighingBottleNumber";
                    }

                    if (Objects.nonNull(currentScene) && !currentScene.getFocusOwner().getId().contains("Number")) {
                        log.info("Focus owner ID: " + currentScene.getFocusOwner().getId());

                        TextField numberTextField = (TextField) currentScene.lookup(numberTextFieldId);

                        System.out.println("SET RUNNABLE:");
                        Platform.runLater(() -> {
                        System.out.println("INSIDE RUNNABLE:");
//                            textField.clear();
//                        numberTextField.setText(StringUtils.EMPTY);
                            numberTextField.clear();
                        });
                    }

                } else if (textField.getId().contains("pot")) {
                    if (Objects.nonNull(currentScene) && !currentScene.getFocusOwner().getId().contains("Number")) {
                        TextField massTextField = (TextField) currentScene.lookup("#potNumber");

                        massTextField.setText(StringUtils.EMPTY);
                    }
                } else if (textField.getId().contains("Ring")) {
                    log.info("CONTAINS RING!");
                    if (textField.getId().contains("Mass")) {
                        log.info("MASS");
                        String ringMassTextFieldId = textField.getId();
                        String[] splittedIdArray = ringMassTextFieldId.split("EmptyMass");

                        System.out.println("splittedIdArray: " + Arrays.toString(splittedIdArray));

                        String suffix;

                        try {
                            suffix = splittedIdArray[1];
                        } catch (Exception e) {
                            log.warn(e.getMessage());
                            suffix = StringUtils.EMPTY;
                        }

                        String numberTextFieldId = "#" + splittedIdArray[0] + "Number" + suffix;

                        log.info("numberTextFieldId: " + numberTextFieldId);

                        if (
                                Objects.nonNull(currentScene) &&
                                Objects.nonNull(currentScene.getFocusOwner().getId()) &&
                                !currentScene.getFocusOwner().getId().contains("Number")) {
                            TextField numberTextField = (TextField) currentScene.lookup(numberTextFieldId);

                            if (Objects.isNull(numberTextField)) {
                                splittedIdArray = ringMassTextFieldId.split("EmptyRingMass");

                                numberTextFieldId = "#" + splittedIdArray[0] + "RingNumber" + splittedIdArray[1];
                                numberTextField = (TextField) currentScene.lookup(numberTextFieldId);
                            }

                            log.info("numberTextField: " + numberTextField);

                            numberTextField.setText(StringUtils.EMPTY);
                        }

                    } else if (textField.getId().contains("Volume")) {
                        log.info("VOLUME");

                        String ringVolumeTextFieldId = textField.getId();
                        String[] splittedIdArray = ringVolumeTextFieldId.split("Volume");

                        String numberTextFieldId = "#" + splittedIdArray[0] + "Number" + splittedIdArray[1];

                        if (Objects.nonNull(currentScene) && !currentScene.getFocusOwner().getId().contains("Number")) {
                            TextField numberTextField = (TextField) currentScene.lookup(numberTextFieldId);

                            numberTextField.setText(StringUtils.EMPTY);
                        }
                    }

                }
            }

            if (Objects.nonNull(entitiesUpdateable) && oldValue != null && !oldValue.isEmpty() && !oldValue.equals(newValue)) {
                entitiesUpdateable.update();
            }
        });
    }

    private static <T, Y> void addListenerOnRingNumberField(TextField textField, ClassificatorService<T, Y> classificatorService, String newValue) {
        Ring ring = (Ring) classificatorService.getByNumber(newValue);

        Scene currentScene = textField.getScene();

        if (Objects.nonNull(currentScene)) {
            setRingMassAndVolumeTextFieldValue(textField, ring, currentScene);
        }
    }

    private static <T, Y> void addListenerOnWeighingBottleNumberField(TextField textField, ClassificatorService<T, Y> classificatorService, String newValue) {
        WeighingBottle weighingBottle = (WeighingBottle) classificatorService.getByNumber(newValue);

        Scene currentScene = textField.getScene();

        if (Objects.nonNull(currentScene)) {
            setWeighingBottleMassTextFieldValue(textField, weighingBottle, currentScene);
        }
    }

    private static <T, Y> void addListenerOnPotNumberField(TextField textField, ClassificatorService<T, Y> classificatorService, String newValue) {
        Pot pot = (Pot) classificatorService.getByNumber(newValue);

        Scene currentScene = textField.getScene();

        if (Objects.nonNull(currentScene)) {
            setPotMassTextFieldValue(textField, pot, currentScene);
        }
    }

    private static void setPotMassTextFieldValue(TextField textField, Pot pot, Scene currentScene) {
        TextField massTextField = (TextField) currentScene.lookup("#potMass");

        massTextField.setText(String.valueOf(pot.getWeight()));
    }

    private static void setRingMassAndVolumeTextFieldValue(TextField textField, Ring ring, Scene currentScene) {
        String ringNumberTextFieldId = textField.getId();
        String[] splittedIdArray = ringNumberTextFieldId.split("Number");

        String massTextFieldId = "#" + splittedIdArray[0] + "EmptyMass" + splittedIdArray[1];
        String volumeTextFieldId = "#" + splittedIdArray[0] + "Volume" + splittedIdArray[1];

        TextField massTextField = (TextField) currentScene.lookup(massTextFieldId);

        if (Objects.isNull(massTextField)) {
            splittedIdArray = ringNumberTextFieldId.split("RingNumber");

            massTextFieldId = "#" + splittedIdArray[0] + "EmptyRingMass" + splittedIdArray[1];
            massTextField = (TextField) currentScene.lookup(massTextFieldId);
        }

        TextField volumeTextField = (TextField) currentScene.lookup(volumeTextFieldId);

        massTextField.setText(String.valueOf(ring.getWeight()));
        volumeTextField.setText(String.valueOf(ring.getVolume()));
    }


    private static void setWeighingBottleMassTextFieldValue(TextField textField, WeighingBottle weighingBottleByNumber, Scene currentScene) {
        String textFieldId = textField.getId();
        String[] splittedIdArray = textFieldId.split("WeighingBottleNumber");

        String massTextFieldId;

        if (splittedIdArray.length == 2) {
            massTextFieldId = "#" + splittedIdArray[0] + "EmptyWeighingBottleMass" + splittedIdArray[1];
        } else {
            massTextFieldId = "#" + "emptyWeighingBottleMass";
        }

        TextField massTextField = (TextField) currentScene.lookup(massTextFieldId);

        massTextField.setText(String.valueOf(weighingBottleByNumber.getWeight()));
    }

    @FXML
    public static <T, Y> void setTextFieldProperties(Map<TextField, Property> allTextFieldMap,
                                                     Map<TextField, Property> nonEditableTextFieldMap,
                                                     ClassificatorService<T, Y> classificatorService,
                                                     EntitiesUpdateable entitiesUpdateable) {
        nonEditableTextFieldMap.keySet().forEach(e -> e.setEditable(false));

        allTextFieldMap.keySet().forEach(textField ->
                JavaFXCommonMethods.addListenerOnTextField(textField, classificatorService, entitiesUpdateable));
    }

    public static <E> void setConverterForChoiceBox(ChoiceBox<E> choiceBox, LongFunction<SoilGroupType> getSoilGroupType) {
        choiceBox.setConverter(new StringConverter<E>() {
            @Override
            public String toString(E object) {

                if (Objects.isNull(object)) {
                    return StringUtils.EMPTY;
                }

                if (object instanceof SoilClass soilClass) {
                    return soilClass.getScName();
                } else if (object instanceof SoilClassKindGroup soilClassKindGroup) {
                    return soilClassKindGroup.getSoilKindGroup();
                } else if (object instanceof SoilKindGroupType soilKindGroupType) {
                    var soilGroupTypeId = soilKindGroupType.getSoilGroupType().getId();
                    var soilKindGroupTypeName = getSoilGroupType.apply(soilGroupTypeId);

                    return soilKindGroupTypeName.getGtName();
                } else if (object instanceof SoilSubkind soilSubkind) {
                    return soilSubkind.getSsDescr();
                } else if (object instanceof SoilSubkindAdj soilSubkindAdj) {
                    return soilSubkindAdj.getSsaDescr();
                } else if (object instanceof Color color) {
                    return color.getCltName();
                }

                return StringUtils.EMPTY;
            }

            @Override
            public E fromString(String string) {
                return null;
            }
        });
    }

    public static void initAlert(String message) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ALERT_WINDOW_TITLE);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

//TODO:
//Yes, it is possible to improve the code to show the cell at the center of the scene. One way to achieve this
//        is by using the anchorPoint property of the cell's parent node.
//
//        Here's an example of how you can modify the code to center the cell:
//        swift
//// Assuming you have a variable named cell that represents the cell node
//
//// Set the anchor point of the parent node to (0.5, 0.5) which represents the center
//        cell.parent?.anchorPoint = CGPoint(x: 0.5, y: 0.5)
//
//// Set the position of the cell to the center of the scene
//        cell.position = CGPoint(x: scene.size.width / 2, y: scene.size.height / 2)
//        By setting the anchor point of the parent node to (0.5, 0.5), the position of the node is calculated relative
//        to its center rather than its bottom-left corner. Then, by setting the position of the cell to the center of the scene, the cell will be displayed at the center.
//
//        Please note that this code assumes you have a reference to the cell node and the scene size is available.
//        Adjust the code accordingly based on your specific implementation.
