package org.geoproject.ingeo.utils;

import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.geoproject.ingeo.controllers.functionalInterfaces.EntitiesUpdateable;
import org.geoproject.ingeo.controllers.functionalInterfaces.NewRowAddable;
import org.geoproject.ingeo.customFXnodes.CustomTextFieldTableCell;
import org.geoproject.ingeo.dto.classificators.kga.ColorDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilClassDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilClassKindGroupDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilGroupTypeDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilKindGroupTypeDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindAdjDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindDto;
import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.classificators.Pot;
import org.geoproject.ingeo.models.classificators.Ring;
import org.geoproject.ingeo.models.classificators.WeighingBottle;
import org.geoproject.ingeo.services.classificators.ClassificatorService;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.LongFunction;

import static org.geoproject.ingeo.constants.JavaFXConstants.FLOAT_SPLIT_PATTERN;
import static org.geoproject.ingeo.constants.JavaFXConstants.IS_FLOAT_VALUE_PATTERN;
import static org.geoproject.ingeo.constants.JavaFXConstants.TEXTFIELD_NUMERIC_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.ALERT_WINDOW_TITLE;

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
                                                List<String> excludeColumnNameList) {
        tableColumn.setCellFactory(column ->
                new CustomTextFieldTableCell<>(column, tableView, observableList, newRowAddable, excludeColumnNameList)
        );
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

    public static <E> void setConverterForChoiceBox(ChoiceBox<E> choiceBox, LongFunction<SoilGroupTypeDto> getSoilGroupTypeDto) {
        choiceBox.setConverter(new StringConverter<E>() {
            @Override
            public String toString(E object) {

                if (Objects.isNull(object)) {
                    return StringUtils.EMPTY;
                }

                if (object instanceof SoilClassDto soilClassDto) {
                    return soilClassDto.getScName();
                } else if (object instanceof SoilClassKindGroupDto soilClassKindGroupDto) {
                    return soilClassKindGroupDto.getSoilKindGroup();
                } else if (object instanceof SoilKindGroupTypeDto soilKindGroupTypeDto) {
                    var soilGroupTypeId = soilKindGroupTypeDto.getSoilGroupTypeDto().getId();
                    var soilKindGroupTypeName = getSoilGroupTypeDto.apply(soilGroupTypeId);

                    return soilKindGroupTypeName.getGtName();
                } else if (object instanceof SoilSubkindDto soilSubkindDto) {
                    return soilSubkindDto.getSsDescr();
                } else if (object instanceof SoilSubkindAdjDto soilSubkindAdjDto) {
                    return soilSubkindAdjDto.getSsaDescr();
                } else if (object instanceof ColorDto colorDto) {
                    return colorDto.getCltName();
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

    public static StringConverter<Float> getFloatStringConverter() {
        var floatStringConverter = new StringConverter<Float>() {
            @Override
            public String toString(Float object) {
//              var valueFormatter = new DecimalFormat("0.000E0");
                var valueFormatter = new DecimalFormat("0.00");

                try {
                    return valueFormatter.format(object);
                } catch (IllegalArgumentException e) {
                    log.error("JavaFXCommonMethods, string 623");
                    log.error(e.getMessage());
                    return valueFormatter.format(0);
                }

//                return StringUtils.EMPTY;
            }

            @Override
            public Float fromString(String string) {
                if (string.matches(".*[,\\.].*")) {
                    var text = string.replace(',', '.');

                    return Float.valueOf(text);
                }

                return null;
            }
        };

        return floatStringConverter;
    }

    /**
     * Проверка на глубину слоя. Низ слоя не должен быть больше, чем общая глубина.
     *
     * @param layerBottomDepth глубина подошвы слоя
     * @param surveyPointDepth общая глубина выработки (точки исследования)
     * @return true если глубина подошвы слоя меньше или равна глубине скважины, иначе - false
     */
    public static Boolean checkLayerDepth(Float layerBottomDepth, Float surveyPointDepth) {
        return layerBottomDepth <= surveyPointDepth;
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
