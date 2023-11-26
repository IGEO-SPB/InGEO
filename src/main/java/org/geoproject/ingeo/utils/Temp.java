package org.geoproject.ingeo.utils;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.FloatStringConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Temp {

//    @FXML
//    private void onCancelButtonClicked(ActionEvent event) {
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.close();
//    }
//
//
//    @FXML
//    public void onSaveNewWeighingBottleButtonClick(ActionEvent event) throws IOException {
//
//        WeighingBottle newWeighingBottle = new WeighingBottle();
//
//        Optional<WeighingBottle> optionalWeighingBottle = weighingBottleService.findByNumber(number.getText());
//
//        String weightText = weight.getText().replace(',', '.');
//
//        if (optionalWeighingBottle.isPresent()) {
//            System.out.println("Бюкс с таким номером уже существует");
//            number.clear();
//        } else {
//            newWeighingBottle.setNumber(number.getText());
//            newWeighingBottle.setWeight(Float.parseFloat(weightText));
//
//            number.clear();
//            weight.clear();
//
//            weighingBottleService.saveOne(newWeighingBottle);
//        }
//    }

//    private void closeHandle() {
//        dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                isClosed = true;
//            }
//        });
//    }

    public static <K, R, Y> void setCellFactory(TableColumn<K, R> tableColumn, TableView<Y> tableView, ObservableList<Y> observableList) {
        tableColumn.setCellFactory(column -> new TableCell<K, R>() {
            private TextField textField;

            @Override
            public void startEdit() {
                super.startEdit();
                if (textField == null) {
                    createTextField();
                }
                textField.setText(getItem() == null ? "" : getItem().toString());
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                Platform.runLater(() -> {
                    textField.requestFocus();
                    textField.selectAll();
                });
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                setText(getItem().toString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }

            @Override
            protected void updateItem(R item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(getItem().toString());
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }

            private void createTextField() {
                textField = new TextField(getItem().toString());
                textField.setMinWidth(getWidth() - getGraphicTextGap() * 2);
                textField.setOnKeyPressed(event -> handleKeyEvent(event, tableView));
                textField.focusedProperty().addListener((observable, oldValue, isFocused) -> handleFocusChange(isFocused));
            }

            private void handleKeyEvent(KeyEvent event, TableView<Y> tableView) {
                int rowIndex = tableView.getSelectionModel().getSelectedIndex();
                if (event.getCode() == KeyCode.ENTER) {
                    commitEditOrConvert();
                    TableColumn<Y, ?> nextColumn = getNextColumn(true);
                    if (nextColumn != null) {
                        tableView.getSelectionModel().select(rowIndex + 1, nextColumn);
                        tableView.edit(rowIndex + 1, nextColumn);
                    }
                } else if (event.getCode() == KeyCode.TAB) {
                    commitEditOrConvert();
                    TableColumn<Y, ?> nextColumn = getNextColumn(!event.isShiftDown());
                    if (nextColumn != null) {
                        tableView.getSelectionModel().select(rowIndex, nextColumn);
                        tableView.edit(rowIndex, nextColumn);
                    }
                } else if (event.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }

            private void handleFocusChange(boolean isFocused) {
                if (isFocused) {
                    Platform.runLater(textField::selectAll);
                } else {
                    commitEditOrConvert();
                }
            }

            private void commitEditOrConvert() {
                try {
                    commitEdit((R) textField.getText());
                } catch (ClassCastException e) {
                    try {
                        commitEdit((R) new FloatStringConverter().fromString(textField.getText()));
                    } catch (NumberFormatException exception) {
                        commitEdit((R) Boolean.valueOf(textField.getText()));
                    }
                }
            }

            private TableColumn<Y, ?> getNextColumn(boolean forward) {
                List<TableColumn<Y, ?>> columns = getEditableColumns();

                if (columns.size() < 2) {
                    return null;
                }
                int currentIndex = columns.indexOf(getTableColumn());

                int nextIndex = forward ? currentIndex + 1 : currentIndex - 1;

                if (nextIndex < 0) {
                    nextIndex = columns.size() - 1;
                } else if (nextIndex >= columns.size()) {
                    nextIndex = 0;
                }
                return columns.get(nextIndex);
            }

            private List<TableColumn<Y, ?>> getEditableColumns() {
                List<TableColumn<Y, ?>> columns = new ArrayList<>();
                for (TableColumn<Y, ?> column : tableView.getColumns()) {
                    if (column.isEditable()) {
                        columns.add(column);
                    }
                }
                return columns;
            }
        });
    }






        private static final BigDecimal ONE_POINT_FOUR = new BigDecimal("1.4");
        private static final BigDecimal TEN_POWER_MINUS_FOUR = new BigDecimal("0.000185");

        public void puch(Object[] rst, int flag_empty) {

            int i;
            String DisNm = "";
            BigDecimal[] z = new BigDecimal[16];

            z[3] = nz(rst[3]);
            z[4] = nz(rst[4]);
            z[5] = nz(rst[5]);
            z[6] = nz(rst[6]);
            z[7] = nz(rst[7]);
            z[8] = nz(rst[8]);
            z[9] = nz(rst[9]).add(nz(rst[10]));
            z[10] = nz(rst[11]);

            BigDecimal eee;

            BigDecimal dis = null;
            if (flag_empty == 0) {
                dis = null;
                DisNm = null;
            } else {
                if (!rst[12].equals(0)) {
                    eee = (BigDecimal) rst[12];
                } else {
                    switch ((String) rst[13]) {
                        case "гравий":
                        case "галеч":
                            return;
                        case "гравел":
                        case "крупн":
                        case "ср. крупн":
                            eee = new BigDecimal("0.65");
                            break;
                        case "мелк":
                        case "пылеват":
                            eee = new BigDecimal("0.7");
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid RaznGr: " + rst[13]);
                    }
                }

                BigDecimal spd;

                BigDecimal[] dd = {BigDecimal.TEN, new BigDecimal("5"), new BigDecimal("2"), BigDecimal.ONE,
                        new BigDecimal("0.5"), new BigDecimal("0.25"), new BigDecimal("0.1"), new BigDecimal("0.05"),
                        new BigDecimal("0.01"), new BigDecimal("0.002"),
                        new BigDecimal("0.002").divide(ONE_POINT_FOUR, 5, RoundingMode.HALF_UP).divide(ONE_POINT_FOUR, 5, RoundingMode.HALF_UP)};

                spd = BigDecimal.ZERO;
                for (i = 3; i <= 10; i++) {
                    BigDecimal diam = dd[i].multiply(ONE_POINT_FOUR).divide(BigDecimal.TEN);
                    spd = spd.add(z[i].divide(BigDecimal.valueOf(100)).divide(diam, RoundingMode.HALF_UP));
                }

                if (spd.equals(BigDecimal.ZERO)) {
                    return;
                }

                BigDecimal avd = BigDecimal.ONE.divide(spd, RoundingMode.HALF_UP);
                dis = TEN_POWER_MINUS_FOUR.divide(avd.multiply(avd).multiply(eee), RoundingMode.HALF_UP);
                DisNm = "";
                if (dis.compareTo(BigDecimal.ONE) < 0) {
                    DisNm = "непуч";
                } else if (dis.compareTo(new BigDecimal("5")) >= 0) {
                    DisNm = "пучин";
                } else if (dis.compareTo(BigDecimal.ONE) >= 0 && dis.compareTo(new BigDecimal("5")) <= 0) {
                    DisNm = "сл.пуч";
                }
            }

            // rst.Edit
            rst[14] = dis;
            rst[15] = DisNm;
            // rst.Update
        }

        private static BigDecimal nz(Object value) {
            if (value == null) {
                return BigDecimal.ZERO;
            } else {
                return (BigDecimal) value;
            }
        }


//Установка ChoiceBox

//    private void setCellFactoryForEmployeeChoiceBox(TableColumn<ProjectDto, String> tableColumn) {
//        ObservableList<String> employeeObservableList = FXCollections.observableArrayList();
//        List<Employee> employees = employeesService.getAll();
//        employeeObservableList.addAll(employees.stream()
//                .map(employee -> employee.getName() + EMPLOYEE_FIELD_PATTERN + employee.getId())
//                .toList());
//
//        tableColumn.setCellValueFactory(data -> {
//            var text = Objects.isNull(data.getValue().getCreatedBy()) ? StringUtils.EMPTY :
//                    data.getValue().getCreatedBy().getName();
////            return new SimpleObjectProperty<>(data.getValue().getCreatedBy().getName());
//            return new SimpleObjectProperty<>(text);
//        });
//
//        tableColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(employeeObservableList));
//
//        tableColumn.setOnEditCommit(event -> {
//            ProjectDto projectDto = event.getRowValue();
//            Long id = Long.valueOf(event.getNewValue().split(EMPLOYEE_FIELD_PATTERN)[1]);
//
//            Employee employee = employeesService.getById(id);
//            projectDto.setCreatedBy(employee);
//        });
//    }


//    public static <K, R, Y> void setCellFactoryForChoiceBox(TableColumn<K, R> tableColumn, TableView<Y> tableView,
//                                                            ObservableList<Y> observableList, NewRowAddable newRowAddable) {
//
//        tableColumn.setCellFactory(column -> new TableCell<K, R>() {
//            private final ChoiceBox box = new ChoiceBox(observableList);
//            private boolean selectRowOnClick = true;
//
//
//            private void createChoiceBox() {
//                box.disableProperty().bind(column.editableProperty().not());
//                box.showingProperty().addListener(event -> {
//                    final TableView tableView = getTableView();
//                    if (selectRowOnClick) {
//                        tableView.getSelectionModel().select(getTableRow().getIndex());
//                        tableView.edit(tableView.getSelectionModel().getSelectedIndex(), column);
//                    } else {
//                        tableView.edit(getTableRow().getIndex(), column);
//                    }
//                });
//                this.box.valueProperty().addListener(
//                        (observable, oldValue, newValue) -> {
//                            if (isEditing()) {
//                                commitEdit((R) newValue);
//                            }
//                        });
//                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//
//                this.box.setOnKeyPressed(this::handleKeyEvent);
//
//            }
//
//            @Override
//            public void startEdit() {
//                super.startEdit();
//                if (this.box == null) {
//                    this.createChoiceBox();
//                }
//
//                this.box.setValue(box.getValue() == null ? "" : this.getString());
//
//                super.setGraphic(this.box);
//                super.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//
//                Platform.runLater(() -> {
//                    this.box.requestFocus();
////                    this.box.selectAll();
//                });
//            }
//
//            @Override
//            public void cancelEdit() {
//                super.cancelEdit();
//                super.setText(this.getString());
//                super.setContentDisplay(ContentDisplay.TEXT_ONLY);
//            }
//
//            public TableCell<K, R> selectRowOnClick(boolean selectRowOnClick) {
//                this.selectRowOnClick = selectRowOnClick;
//                return this;
//            }
//
//            @Override
//            protected void updateItem(R item, boolean empty) {
//                super.updateItem(item, empty);
//
//                setText(null);
//                if (empty || item == null) {
//                    setGraphic(null);
//                } else {
//                    this.box.setValue(item);
//                    this.setGraphic(this.box);
//                }
//            }
//
//            private String getString() {
//                return super.getItem() == null ? StringUtils.EMPTY : String.valueOf(super.getItem());
//            }
//
//            private void handleKeyEvent(KeyEvent keyEvent) {
//                int rowIndex = tableView.getSelectionModel().getSelectedIndex();
//                if (keyEvent.getCode() == KeyCode.ENTER) {
//                    System.out.println("ENTER pressed");
//
////                    commitEdit();
//
//                    if ((observableList.get(0) instanceof AbstractClassificator || observableList.get(0) instanceof WaterSampleDto) &&
//                            observableList.size() == rowIndex + SINGLE_ROW_COUNT) {
//                        newRowAddable.add();
//                    }
//
//                    TableColumn<Y, ?> nextColumn = getEditableColumns().get(0);
//                    selectAndEditCell(tableView, rowIndex + SINGLE_COLUMN_INDEX, nextColumn);
//
//
//
//                } else if (keyEvent.getCode() == KeyCode.TAB) {
//                    System.out.println("TAB pressed");
//                    if (column.getId().toLowerCase().contains("number") &&
//                            !column.getId().equalsIgnoreCase("labornumber") &&
//                            !column.getId().equalsIgnoreCase("surveyPointNumber")) {
//                        List<String> numbers = observableList.stream()
//                                .map(container -> ((AbstractClassificator) container).getNumber())
//                                .toList();
////
////                        if (checkIfNumberExistInDataBase(textField.getText(), numbers)) {
////                            this.cancelEdit();
////                        }
//                    }
//
//
//
////                    commitEdit();
//
//                    TableColumn<Y, ?> nextColumn = getNextColumn(!keyEvent.isShiftDown());
//
//                    selectAndEditCell(tableView, rowIndex, nextColumn);
//                    System.out.println("----selectedProperty()----");
//                    System.out.println(selectedProperty());
//
//                } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
//                    this.cancelEdit();
//                }
//            }
//
//            private void selectAndEditCell(TableView<Y> tableView, int rowIndex, TableColumn<Y, ?> nextColumn) {
//                System.out.println("Check selectAndEditCell");
//
//                tableView.layout();
//                tableView.getSelectionModel().select(rowIndex, nextColumn);
//                tableView.edit(rowIndex, nextColumn);
//                tableView.scrollToColumn(nextColumn);
//            }
//
////            private void commitEditOrConvert() {
////                System.out.println("Check commitEditOrConvert");
////                try {
////                    commitEdit((R) textField.getText());
////                } catch (ClassCastException e) {
////                    try {
////                        commitEdit((R) new FloatStringConverter().fromString(textField.getText()));
////                    } catch (NumberFormatException exception) {
////                        try {
////                            commitEdit((R) Boolean.valueOf(textField.getText()));
////                        } catch (ClassCastException e1) {
////                            String[] split = textField.getText().split("-");
//////                            commitEdit((R) textField.getText());
////                            commitEdit((R) LocalDate.of(Integer.parseInt(split[0]),
////                                    Integer.parseInt(split[1]),
////                                    Integer.parseInt(split[2])));
////                        }
////                    }
////                }
////            }
//
////            private void handleFocusChange(Boolean isFocused) {
////                if (isFocused) {
////                    Platform.runLater(textField::selectAll);
////                } else if (!isFocused && textField != null) {
////                    commitEditOrConvert();
////                }
////            }
//
//            private TableColumn<Y, ?> getNextColumn(Boolean forward) {
//                System.out.println("Check getNextColumn");
//                List<TableColumn<Y, ?>> columns = getEditableColumns();
//
//                if (columns.size() < 2) {
//                    return null;
//                }
//                int currentIndex = columns.indexOf(this.getTableColumn());
//
//                int nextIndex = forward ? currentIndex + SINGLE_COLUMN_INDEX : currentIndex - SINGLE_COLUMN_INDEX;
//
//                if (forward && nextIndex > columns.size() - 1) {
//                    nextIndex = 0;
//                } else if (!forward && currentIndex < 0) {
//                    nextIndex = columns.size() - SINGLE_COLUMN_INDEX;
//                }
//
//                System.out.println("CHECK COLUMNS");
//                columns.forEach(column -> System.out.println(column.getId()));
//
//                return columns.get(nextIndex);
//            }
//
//            private List<TableColumn<Y, ?>> getEditableColumns() {
//                List<TableColumn<Y, ?>> columns = new ArrayList<>();
//                for (var column : tableView.getColumns()) {
//                    columns.addAll(getEditableLeaves((TableColumn<Y, ?>) column));
//                }
//
//                return columns;
//            }
//
//            private List<TableColumn<Y, ?>> getEditableLeaves(TableColumn<Y, ?> root) {
//                List<TableColumn<Y, ?>> columns = new ArrayList<>();
//                if (root.getColumns().isEmpty()) {
//                    if (root.isEditable()) {
//                        columns.add(root);
//                    }
//                    return columns;
//                } else {
//                    for (TableColumn<Y, ?> column : root.getColumns()) {
//                        columns.addAll(getEditableLeaves(column));
//                    }
//
//                    return columns;
//                }
//            }
//        });
//    }

    }

//    Project fields:

//contractNumber
//archiveNumber
//projectName
//region
//city
//district
//town
//street
//constructionType
//reportName
//absoluteMediumWinterTemperature
//assignmentDate
//startDate
//endDate
//projectingStage
//coordinateX
//coordinateY
//createdBy
//executor
//approver


//GranCompositionResultTableController fields:

//    @FXML
//    private TableColumn<GranCompositionDTO, String> laborNumber;
//    @FXML
//    private TableColumn<GranCompositionDTO, String> surveyPointNumber;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> depthMin;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> depthMax;

//    @FXML
//    private TableColumn<GranCompositionDTO, Float> included_more_2;
//
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_more_10;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_10_5_old_10_2;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_5_2;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_2_1;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_1_05;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_05_025;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_025_01;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_01_005;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_005_001;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_001_0002_old_001_0005;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> X_less_0002;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> sum_2_005;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> sum_005_0002_old_005_0005;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> sum_less_0002_old_less_0005;
//    @FXML
//    private TableColumn<GranCompositionDTO, String> soilKind;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> uniformityCoefficient;
//    @FXML
//    private TableColumn<GranCompositionDTO, String> uniformityDegree;
//    @FXML
//    private TableColumn<GranCompositionDTO, Float> dispersityIndex;
//    @FXML
//    private TableColumn<GranCompositionDTO, String> heavingDegree;
//    @FXML
//    private TableColumn<GranCompositionDTO, Boolean> isSand;




//WaterSample fields:


//HCO3_1;
//
//        HCO3_2;
//
//        OHa_1;
//
//        OHa_2;
//
//        OHb_1;
//
//        OHb_2;
//
//        CO3_1;
//
//        CO3_2;
//
//        CL_1;
//
//        CL_2;
//
//        SO4_1;
//
//        SO4_2;
//
//        NO2;
//
//        NO3;
//
//        Ca_1;
//
//        Ca_2;
//
//        pH;
//
//        Mg_izm_1;
//
//        Mg_izm_2;
//
//        NH4;
//
//        Fe;
//
//        dry_1;
//
//        dry_2;
//
//        O2_1;
//
//        O2_2;
//
//        CO2sv_1;
//
//        CO2sv_2;
//
//        CO2ag_1;
//
//        CO2ag_2;
//
//        cemSlag;
//
//        cemSulRes;
//
//        clCoef;


//WaterSampleResult fields

//    waterGroup
//            aquifer
//    depth
//            samplingDate
//    laboratoryAcceptanceDate
//            transparency
//    color
//            odor
//    CO3_eq
//            CL_eq
//    CL_v
//            SO4
//    NO2
//            NO3
//    anSumWithOh
//            anSumWithoutOh
//    Ca_eq
//            pH
//    Mg_izm
//            Mg_eq
//    Mg_v
//            NH4
//    Fe
//            Na_eq
//    Na_v
//            dry
//    hdnGen
//            hdnTmp
//    hdn_con
//            SiO2
//    O2
//            CO2sv
//    CO2ag_izm
//            CO2ag
//    H2S
//            gum
//    cemPor
//            cemSlag
//    cemSulRes
//            HCO3_eq
//            CL_SO4
//    catSum
//            HCO3_v
//    CO3_v
//            Ca_v
//    OH_eq
//            OH_v
//isArchive


// попытка сделать bidirectional динамически

//    private Map<String, Property> propertyMap = new HashMap<>();

//    public void setFieldBinding() {
//        propertyMap.put("particleDensity", areometricDTO.particleDensityProperty());
//        propertyMap.put("totalSubsample", areometricDTO.totalSubsampleProperty());
//        propertyMap.put("undisturbedSampleWaterContent", areometricDTO.undisturbedSampleWaterContentProperty());
//        propertyMap.put("subsampleWetSoil", areometricDTO.subsampleWetSoilProperty());
//        propertyMap.put("weighingBottleNumber", areometricDTO.weighingBottleNumberProperty());
//
//        bindTextField(particleDensity);
//        bindTextField(totalSubsample);
//        bindTextField(undisturbedSampleWaterContent);
//        bindTextField(subsampleWetSoil);
//        bindTextField(weighingBottleNumber);
//    }
//
//    private void bindTextField(TextField textField) {
//        String propertyName = textField.getId(); // Assuming the ID of the text field is set to the property name
//        Property property = propertyMap.get(propertyName);
//        if (property != null) {
//            textField.textProperty().bindBidirectional(property, JavaFXCommonMethods.getConverter());
//        }
//    }
//
//
//    public void setFieldBinding() {
//        editableTextFields.forEach(field -> {
//            bindTextField(field, field.getId());
//        });
//    }
//
//    private void bindTextField(TextField textField, String propertyName) {
//        try {
//            Property property = (Property) areometricDTO.getClass().getDeclaredField(propertyName + "Property")
//                    .get(areometricDTO);
//            if (property instanceof FloatProperty) {
//                textField.textProperty().bindBidirectional(property, JavaFXCommonMethods.getConverter());
//            } else {
//                textField.textProperty().bindBidirectional(property);
//            }
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }




//    Sub Puch(rst, flag_empty)
//
//        Dim i As Integer
//        Dim DisNm As String
//        Dim z(15) As Variant
//
//
//
//        With rst
//
//
//
//        z(3) = Nz(![z3])
//        z(4) = Nz(![z4])
//        z(5) = Nz(![z5])
//        z(6) = Nz(![z6])
//        z(7) = Nz(![b1])
//        z(8) = Nz(![b2])
//        z(9) = Nz(![b3]) + Nz(![b4])
//
//        z(10) = Nz(![b5])
//
//        ' 'eee = ![e]
//
//
//        pp1 = ![b1] / 100
//        pp2 = ![b2] / 100
//        pp3 = (![b3] + ![b4]) / 100
//
//        pp4 = ![b5] / 100
//
//        End With
//
//        If flag_empty = 0 Then
//        dis = Empty
//        DisNm = Empty
//        GoTo p2
//        End If
//
//        If rst![e] <> 0 Then
//        eee = rst![e]
//        GoTo p111
//        End If
//
//        Select Case rst!RaznGr
//        Case "ãðàâèé"
//        Exit Sub
//        Case "ãàëå÷"
//        Exit Sub
//        Case "ãðàâåë", "êðóïí", "ñð. êðóïí"
//        eee = 0.65
//        Case "ìåëê"
//        eee = 0.7
//        Case "ïûëåâàò"
//        eee = 0.7
//        End Select
//
//        p111:
//        '
//        GoTo p23
//
//        d0 = (0.1 * 1.4) / 10
//        d1 = (0.05 * 1.4) / 10
//        d2 = (0.01 * 1.4) / 10
//        d3 = (0.002 * 1.4) / 10
//
//        d4 = (0.002 / 1.4) / 10
//
//        p0 = 1 - (pp1 + pp2 + pp3 + pp4)
//        p1 = pp1
//        p2 = (pp2)
//        p3 = (pp3)
//        p4 = pp4
//
//
//        pd0 = p0 / d0: pd1 = p1 / d1: pd2 = p2 / d2: pd3 = p3 / d3: pd4 = p4 / d4:
//        spd = pd0 + pd1 + pd2 + pd3 + pd4
//
//
//        GoTo p22
//        p23:
//        Dim dd As Variant
//        dd = Array(10, 5, 2, 1, 0.5, 0.25, 0.1, 0.05, 0.01, 0.002, Round(((0.002 / 1.4) / 1.4), 5)) 'äëÿ 10-5-2
//        spd = 0
//
//        For i = 3 To 10
//        diam = ((dd(i) * 1.4) / 10)  'ñì
//
//        spd = spd + ((z(i) / 100)) / diam
//
//
//        Next i
//
//        p22:
//        If spd = 0 Then GoTo p1
//
//        avd = (1 / spd)
//        dis = 1.85 * 10 ^ (-4) / ((avd * avd * eee))
//        DisNm = ""
//        Select Case dis
//        Case Is < 1
//        DisNm = "íåïó÷"
//        Case Is >= 5
//        DisNm = "ïó÷èí"
//        Case 1 To 5
//        DisNm = "ñë.ïó÷"
//
//        End Select
//
//        p2:
//        rst.Edit
//        rst!Disp = dis
//        rst!DispName = DisNm
//        rst.Update
//        p1:
//
//
//        End Sub



//    @FXML
//    protected void onLoginButtonClicked(ActionEvent event) throws IOException {
//        System.out.println("CURRENT PROJECT:");
//        System.out.println(currentState.getCurrentProject());
//
//        JavaFXCommonMethods.changeScene(event, ViewsPathNamesEnum.ALL_PROJECTS_VIEW.getPath(),
//                applicationContext, StageTitleEnum.PROJECTS.getTitle());
//
//        URL url = new File("C:\\Users\\Professional\\IdeaProjects\\IGEO\\geoproject.igeo\\src\\main\\resources\\org.geoproject.ingeo\\all-projects-view.fxml").toURI().toURL();
//        FXMLLoader fxmlLoader = new FXMLLoader(url);
//
//        fxmlLoader.setControllerFactory(applicationContext::getBean);
//
//        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
//        scene = new Scene(fxmlLoader.load(), screenBounds.getWidth()-50, screenBounds.getHeight()-50);
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.centerOnScreen();
//        stage.show();
//        System.out.println("Scene has been changed");
//    }


//интересная идея с Robot:

//tableReceipt.setOnKeyPressed(new EventHandler<KeyEvent>() {
//@Override
//public void handle(KeyEvent t) {
//        if (t.getCode() == KeyCode.TAB) {
//        tableReceipt.getFocusModel().focusRightCell();
//        Robot r = new Robot();
//        r.keyPress(KeyCode.ENTER);
//        }
//        }
//        });

//TODO: попробовать для переключения с помощью таба. На первый взгляд, попроще, чем то, что уже реализовано:
//https://stackoverflow.com/questions/55283510/javafx-11-tableview-cell-navigation-by-tab-key-pressed-without-custom-editablece

//TODO: стратегия выделения ячеек и строк, в т.ч. через shift нескольких:
//https://ru.stackoverflow.com/questions/951490/tableview-%D0%BA%D0%B0%D0%BA-%D0%B2%D1%8B%D0%B4%D0%B5%D0%BB%D1%8F%D1%82%D1%8C-%D1%8F%D1%87%D0%B5%D0%B9%D0%BA%D1%83-%D0%B0-%D0%BD%D0%B5-%D0%B2%D1%81%D1%8E-%D1%81%D1%82%D1%80%D0%BE%D0%BA%D1%83
