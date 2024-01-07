package org.geoproject.ingeo.controllers.cameral;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.controlsfx.control.SearchableComboBox;
import org.geoproject.ingeo.controllers.NewAbstractStaticTableController;
import org.geoproject.ingeo.controllers.functionalInterfaces.Description;
import org.geoproject.ingeo.controllers.functionalInterfaces.GetComboBoxValue;
import org.geoproject.ingeo.controllers.functionalInterfaces.Refreshable;
import org.geoproject.ingeo.controllers.functionalInterfaces.Settable;
import org.geoproject.ingeo.customFXnodes.CustomSearchableComboBoxTableCell;
import org.geoproject.ingeo.dto.SurveyPointDto;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.dto.mainViewsDtos.BoreholeLayerDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.dtoenums.BoreholeLayerDtoFieldsEnum;
import org.geoproject.ingeo.models.BoreholeLayer;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.cameral.EgeServise;
import org.geoproject.ingeo.services.MainViewService;
import org.geoproject.ingeo.services.classificators.ConsistencyService;
import org.geoproject.ingeo.services.classificators.HatchingService;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.GeoCommonMethods;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static org.geoproject.ingeo.constants.JavaFXConstants.ABSOLUTE_TOP_MARK_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.DESCRIPTION_KGA_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.EGE_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.FIRST_LAYER_CONSISTENCY_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.FIRST_LAYER_TOP_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.HATCHING_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.IS_EDITABLE_FROM_EGE_LIST;
import static org.geoproject.ingeo.constants.JavaFXConstants.LAYER_BOTTOM_DEPTH_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.LAYER_POWER_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.LAYER_TOP_DEPTH_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.SECOND_LAYER_CONSISTENCY_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.SURVEY_POINT_CHOICE_BOX;
import static org.geoproject.ingeo.constants.JavaFXConstants.SURVEY_POINT_NUMBER_CHOICE_BOX;
import static org.geoproject.ingeo.constants.ServiceConstants.COMBOBOX_COLUMN_PIXEL_GAP;
import static org.geoproject.ingeo.constants.ServiceConstants.HAVE_TO_SAVE_CHOSEN_BOREHOLE_LAYER_SERVICE_MESSAGE;
import static org.geoproject.ingeo.constants.ServiceConstants.HAVE_TO_SAVE_CHOSEN_EGE_SERVICE_MESSAGE;
import static org.geoproject.ingeo.constants.ServiceConstants.SINGLE_INDEX_POINT;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

/**
 * Контроллер для экрана послойного описания выработок
 * (ВА: Колонка (литология + консистенция))
 */
@Log4j2
@Component
public class BoreholeLayerViewController extends NewAbstractStaticTableController<BoreholeLayer, BoreholeLayerDto> implements Initializable {
    private final SurveyPointsService surveyPointsService;
    private final EgeServise egeServise;

    private final CurrentState currentState;

    private final HatchingService hatchingService;
    private final ConsistencyService consistencyService;

    Stage stage;

    ObservableList<EgeDto> egeDtoObservableList = FXCollections.observableArrayList();
    ObservableList<HatchingDto> hatchingDtoObservableList = FXCollections.observableArrayList();
    ObservableList<ConsistencyDto> consistencyDtoObservableList = FXCollections.observableArrayList();

    @FXML
    TableColumn<BoreholeLayerDto, SearchableComboBox<EgeDto>> ege = new TableColumn<>();
    @FXML
    TableColumn<BoreholeLayerDto, SearchableComboBox<HatchingDto>> hatching = new TableColumn<>();
    @FXML
    TableColumn<BoreholeLayerDto, SearchableComboBox<ConsistencyDto>> firstLayerConsistency = new TableColumn<>();
    @FXML
    TableColumn<BoreholeLayerDto, SearchableComboBox<ConsistencyDto>> secondLayerConsistency = new TableColumn<>();

    @FXML
    ChoiceBox<SurveyPointDto> surveyPointChoiceBox;
    @FXML
    ChoiceBox<SurveyPointDto> surveyPointNumberChoiceBox;

    @FXML
    private TableColumn<BoreholeLayerDto, Boolean> isEditableFromEgeList;
    @FXML
    private TableColumn<BoreholeLayerDto, String> shortName;

//    @FXML
//    private TableColumn<BoreholeLayerDto, Float> layerBottomDepth;

    @FXML
    private TextField surveyPointDepth;
    @FXML
    private TextField surveyPointAbsoluteMark;

    @Autowired
    public BoreholeLayerViewController(ConfigurableApplicationContext applicationContext,
                                       MainViewService<BoreholeLayer, BoreholeLayerDto> service,
                                       CurrentState currentState, SurveyPointsService surveyPointsService,
                                       EgeServise egeServise, HatchingService hatchingService,
                                       ConsistencyService consistencyService) {
        super(currentState, applicationContext, service);
        this.surveyPointsService = surveyPointsService;
        this.egeServise = egeServise;
        this.currentState = currentState;
        this.hatchingService = hatchingService;
        this.consistencyService = consistencyService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Открыт экран послойного описания");

        init();

        setSurveyPointChoiceBox(surveyPointChoiceBox);
        setSurveyPointChoiceBox(surveyPointNumberChoiceBox);

        setSurveyPointDepthTextField();
        setSurveyPointAbsoluteMarkTextField();
    }

    @Override
    public void setObjectListForView() {
        dtos = service.getDtosBySurveyPointId(currentState.getSurveyPoint().getId());
        this.observableDtoList = FXCollections.observableArrayList();
        this.observableDtoList.addAll(dtos);

        sortObservableDtoList(dtos);
    }

    private void setSurveyPointDepthTextField() {
        surveyPointDepth.setText(String.valueOf(currentState.getSurveyPoint().getDepth()));
    }

    private void setSurveyPointAbsoluteMarkTextField() {
        surveyPointAbsoluteMark.setText(String.valueOf(currentState.getSurveyPoint().getAbsoluteMark()));
    }

    @Override
    public void sortObservableDtoList(List<BoreholeLayerDto> boreholeLayerDtoList) {
        dtos.sort(Comparator.comparing(BoreholeLayerDto::getLayerBottomDepth));
        observableDtoList.sort(Comparator.comparing(BoreholeLayerDto::getLayerBottomDepth));
    }

    /**
     * Поля absoluteTopMark, layerPower, layerTopDepth, firstLayerTop при изменении значения в колонке
     * layerBottomDepth рассчитываются автоматически (метод setLayerDepths() класса GeoCommonMethods)
     */
    @Override
    public void setCellFactory(List<String> excludeColumnNameList) {
        columnsMap.values().forEach(column -> {
            if (
                    Objects.equals(column.getId(), ABSOLUTE_TOP_MARK_COLUMN) ||
                            Objects.equals(column.getId(), LAYER_POWER_COLUMN) ||
                            Objects.equals(column.getId(), LAYER_TOP_DEPTH_COLUMN) ||
                            Objects.equals(column.getId(), FIRST_LAYER_TOP_COLUMN)
            ) {
                ((TableColumn<BoreholeLayerDto, Float>) column).setCellFactory(TextFieldTableCell.forTableColumn(
                        JavaFXCommonMethods.getFloatStringConverter()
                ));
            } else {
                JavaFXCommonMethods.setCellFactory(column, tableView, observableDtoList,
                        null, excludeColumnNameList);
            }
        });
    }

    private void setCellValueFactoryForIsEditableFromEgeList() {
        isEditableFromEgeList.setCellFactory(column -> new CheckBoxTableCell<>());
        isEditableFromEgeList.setCellValueFactory(cellData -> {
            var cellValue = cellData.getValue();
            var property = new ReadOnlyBooleanWrapper(cellValue.getIsEditableFromEgeListBasic());

            property.addListener((observable, oldValue, newValue) -> {
                cellValue.setIsEditableFromEgeList(new SimpleBooleanProperty(newValue));
                cellValue.setIsEditableFromEgeListBasic(newValue);
            });

            return property;
        });
    }

    private void setCellValueFactoryForLayerBottomDepth() {
//        layerBottomDepth.setCellValueFactory(new PropertyValueFactory<BoreholeLayerDto, Float>("layerBottomDepth"));
    }

    /**
     * shortName - заполняется в локальном списке грунтов при выборе вида и разновидности грунта из классификаторов
     * либо из микроклассификатора из трех типов и пустого
     * либо вручную.
     * В послойном описании автоматически подтягивается вместе с ИГЭ и заполняться не может.
     */
    private void setCellFactoryForShortName() {
        shortName.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @Override
    public List<String> getExcludeColumnNameList() {
        return new ArrayList<>(Arrays.asList(
                EGE_COLUMN,
                FIRST_LAYER_CONSISTENCY_COLUMN,
                SECOND_LAYER_CONSISTENCY_COLUMN,
                HATCHING_COLUMN,
                IS_EDITABLE_FROM_EGE_LIST,
                DESCRIPTION_KGA_COLUMN
        ));
    }

    @Override
    public void setTableViewCheckBoxes() {
    }

    @Override
    public void setTableViewChoiceBoxes() {
        columnsMap.forEach((columnName, column) -> {
            switch (columnName) {
                case EGE_COLUMN -> setEgeChoiceBox();
                case FIRST_LAYER_CONSISTENCY_COLUMN ->
                        setConsistencyChoiceBox(firstLayerConsistency, FIRST_LAYER_CONSISTENCY_COLUMN);
                case SECOND_LAYER_CONSISTENCY_COLUMN ->
                        setConsistencyChoiceBox(secondLayerConsistency, SECOND_LAYER_CONSISTENCY_COLUMN);
                case HATCHING_COLUMN -> setHatchingChoiceBox();
            }
        });
    }

    @Override
    public void mapDtosAndTableView() {
        columnsMap.forEach((columnName, column) -> {
                    if (Objects.equals(column.getId(), LAYER_BOTTOM_DEPTH_COLUMN)) {
                        column.setOnEditCommit(event -> {

                            System.out.println("EDIT EDIT EDIT");

                            if (checkLayerDepth((Float) event.getNewValue(), currentState.getSurveyPoint().getDepth())) {
                                event.getRowValue()
                                        .setFieldValue(BoreholeLayerDtoFieldsEnum.getEnumByName(columnName), event.getNewValue());

                                GeoCommonMethods.setLayerDepths(
                                        event.getRowValue(),
                                        (Float) event.getNewValue(),
                                        observableDtoList,
                                        surveyPointChoiceBox.getValue());

                            } else {
                                JavaFXCommonMethods.initAlert("Глубина подошвы слоя не должна превышать общую глубину выработки");

                                var oldValue = (Float) event.getOldValue();

                                if (oldValue != null && oldValue != 0) {
                                    event.getRowValue().setLayerBottomDepth(oldValue);
                                } else {
                                    event.getRowValue().setLayerBottomDepth(0);
                                }

                                tableView.getItems().clear();
                                observableDtoList.clear();
                                observableDtoList.addAll(dtos);
                                tableView.setItems(observableDtoList);
                            }
                        });
                    } else {
                        column.setOnEditCommit(event -> {
                            System.out.println("&&&&&&&&&&&");
                            System.out.println(event.getNewValue());

                            event.getRowValue()
                                    .setFieldValue(BoreholeLayerDtoFieldsEnum.getEnumByName(columnName), event.getNewValue());
                        });
                    }
                }
        );

        columnsMap.forEach((columnName, column) -> {

            if (!Objects.equals(columnName, EGE_COLUMN) &&
                    !Objects.equals(columnName, FIRST_LAYER_CONSISTENCY_COLUMN) &&
                    !Objects.equals(columnName, SECOND_LAYER_CONSISTENCY_COLUMN) &&
                    !Objects.equals(columnName, HATCHING_COLUMN)
            ) {
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        setCellFactoryForShortName();
        setCellValueFactoryForIsEditableFromEgeList();
//        setCellValueFactoryForLayerBottomDepth();
    }

    public void setConsistencyChoiceBox(TableColumn<BoreholeLayerDto, SearchableComboBox<ConsistencyDto>> consistency, String columnName) {
        var consistencyDtoList = consistencyService.getConsistencyDtos();
        consistencyDtoObservableList.clear();
        consistencyDtoObservableList.addAll(consistencyDtoList);

        Description<ConsistencyDto> description = ConsistencyDto::getConsistencyType;
        GetComboBoxValue<BoreholeLayerDto, ConsistencyDto> getComboBoxValue;
        Settable<BoreholeLayerDto, ConsistencyDto> settable;

        if (Objects.equals(columnName, FIRST_LAYER_CONSISTENCY_COLUMN)) {
            getComboBoxValue = BoreholeLayerDto::getFirstLayerConsistencyDto;
            settable = BoreholeLayerDto::setFirstLayerConsistencyDto;
        } else {
            getComboBoxValue = BoreholeLayerDto::getSecondLayerConsistencyDto;
            settable = BoreholeLayerDto::setSecondLayerConsistencyDto;
        }

        Callback<TableColumn<BoreholeLayerDto, SearchableComboBox<ConsistencyDto>>, TableCell<BoreholeLayerDto, SearchableComboBox<ConsistencyDto>>> cellFactory =
                consistencyColumn -> new CustomSearchableComboBoxTableCell<>(
                        observableDtoList,
                        consistencyColumn,
                        consistencyDtoObservableList,
                        COMBOBOX_COLUMN_PIXEL_GAP,
                        description,
                        getComboBoxValue,
                        settable
                );

        consistency.setCellFactory(cellFactory);
    }

    public void setHatchingChoiceBox() {
        var hatchingDtoList = hatchingService.getHatchingDtos();
        hatchingDtoObservableList.clear();
        hatchingDtoObservableList.addAll(hatchingDtoList);

        Description<HatchingDto> description = HatchingDto::getShortName;
        GetComboBoxValue<BoreholeLayerDto, HatchingDto> getComboBoxValue = BoreholeLayerDto::getHatchingDto;
        Settable<BoreholeLayerDto, HatchingDto> settable = BoreholeLayerDto::setHatchingDto;

        Callback<TableColumn<BoreholeLayerDto, SearchableComboBox<HatchingDto>>, TableCell<BoreholeLayerDto, SearchableComboBox<HatchingDto>>> cellFactory =
                hatchingColumn -> new CustomSearchableComboBoxTableCell<>(
                        observableDtoList,
                        hatchingColumn,
                        hatchingDtoObservableList,
                        COMBOBOX_COLUMN_PIXEL_GAP,
                        description,
                        getComboBoxValue,
                        settable
                );

        hatching.setCellFactory(cellFactory);
    }

    public void setEgeChoiceBox() {
        var egeDtoList = egeServise.getDtosByProject(currentState.getCurrentProject());
        egeDtoObservableList.clear();
        egeDtoObservableList.addAll(egeDtoList);

        Description<EgeDto> description = EgeDto::getEgeNumber;
        GetComboBoxValue<BoreholeLayerDto, EgeDto> getComboBoxValue = BoreholeLayerDto::getEgeDto;
        Settable<BoreholeLayerDto, EgeDto> settable = BoreholeLayerDto::setEgeDto;

        Callback<TableColumn<BoreholeLayerDto, SearchableComboBox<EgeDto>>, TableCell<BoreholeLayerDto, SearchableComboBox<EgeDto>>> cellFactory =
                egeColumn -> new CustomSearchableComboBoxTableCell<>(
                        observableDtoList,
                        egeColumn,
                        egeDtoObservableList,
                        COMBOBOX_COLUMN_PIXEL_GAP,
                        description,
                        getComboBoxValue,
                        settable
                );

        ege.setCellFactory(cellFactory);

        ege.setOnEditCommit(e -> {
            if (e.getRowValue().getEgeDto() != null) {

                //извлекаем DTO, представленное в строке таблицы
                var rowBoreholeLayerDto = e.getRowValue();
                var egeDto = rowBoreholeLayerDto.getEgeDto();

                var shortName = egeDto.getShortName();
                var hatching = egeDto.getHatchingDto();
                var consistency = egeDto.getConsistencyDto();
                var descriptionCredoFormular = egeDto.getDescriptionCredoFormular();
                var descriptionKga = egeDto.getDescriptionKga();

                rowBoreholeLayerDto.setShortName(shortName);
                rowBoreholeLayerDto.setHatchingDto(hatching);
                rowBoreholeLayerDto.setFirstLayerConsistencyDto(consistency);
                rowBoreholeLayerDto.setDescriptionCredoFormular(descriptionCredoFormular);
                rowBoreholeLayerDto.setDescriptionKga(descriptionKga);

                setHatchingChoiceBox();
                setConsistencyChoiceBox(firstLayerConsistency, FIRST_LAYER_CONSISTENCY_COLUMN);
            }
        });
    }

    /**
     * Проверка на глубину слоя. Низ слоя не должен быть больше, чем общая глубина.
     *
     * @param layerBottomDepth глубина подошвы слоя
     * @param surveyPointDepth общая глубина выработки (точки исследования)
     * @return true если глубина подошвы слоя меньше или равна глубине скважины, иначе - false
     */
    private Boolean checkLayerDepth(Float layerBottomDepth, Float surveyPointDepth) {
        return layerBottomDepth <= surveyPointDepth;
    }

    //кнопки

    @FXML
    public void onSurveyPointChoiceBoxClicked(ActionEvent event) {
        surveyPointChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                var nextIndex = surveyPointChoiceBox.getItems().indexOf(newValue);

                updateScene(nextIndex);
            }
        });
    }

    @FXML
    public void onPreviousSurveyPointButtonClick(ActionEvent event) {
        if (Objects.nonNull(currentState.getSurveyPoint())) {
            var indexOfCurrentSurveyPoint = getSurveyPointChoiceBoxCurrentIndex();

            var prevIndex = indexOfCurrentSurveyPoint > ZERO_INDEX ? indexOfCurrentSurveyPoint - SINGLE_INDEX_POINT : surveyPointChoiceBox.getItems().size() - SINGLE_INDEX_POINT;

            updateScene(prevIndex);
        }
    }

    @FXML
    public void onNextSurveyPointButtonClick(ActionEvent event) {
        if (Objects.nonNull(currentState.getSurveyPoint())) {
            var indexOfCurrentSurveyPoint = getSurveyPointChoiceBoxCurrentIndex();

            var nextIndex = indexOfCurrentSurveyPoint < surveyPointChoiceBox.getItems().size() - SINGLE_INDEX_POINT ? indexOfCurrentSurveyPoint + SINGLE_INDEX_POINT : ZERO_INDEX;

            updateScene(nextIndex);
        }
    }

    private Integer getSurveyPointChoiceBoxCurrentIndex() {
        var surveyPointsNumbers = surveyPointChoiceBox.getItems().stream()
                .map(SurveyPointDto::getPointNumber)
                .collect(Collectors.toList());

        return surveyPointsNumbers.indexOf(currentState.getSurveyPoint().getPointNumber());
    }

    private void updateScene(int nextIndex) {
//        surveyPointNumberChoiceBox.setValue(null);
        setSurveyPointChoiceBox(surveyPointNumberChoiceBox);

        updateCurrentState(nextIndex);

        setSurveyPointDepthTextField();
        setSurveyPointAbsoluteMarkTextField();

        setObjectListForView();
        tableView.getItems().clear();
        tableView.setItems(observableDtoList);
    }

    private void updateCurrentState(Integer index) {
        var surveyPointDto = surveyPointChoiceBox.getItems().get(index);

        currentState.setSurveyPointId(surveyPointDto.getId());
        surveyPointChoiceBox.setValue(surveyPointDto);
    }

    @Override
    @FXML
    public void onSaveAllObjectsButtonClicked() {
        var eges = observableDtoList.stream()
                .map(BoreholeLayerDto::getEgeDto)
                .toList();

        var depths = observableDtoList.stream()
                .map(BoreholeLayerDto::getLayerBottomDepth)
                .toList();

        if (eges.contains(null)) {
            JavaFXCommonMethods.initAlert("Невозможно сохранить слои без выбранных ИГЭ");
        } else if (depths.contains(null)) {
            JavaFXCommonMethods.initAlert("Невозможно сохранить слои без заполненной глубины подошвы");
        } else {
//            super.onSaveAllObjectsButtonClicked();

            service.updateFromDtos(dtos);


            dtos.clear();
            observableDtoList.clear();

            setObjectListForView();
            setCellsFormat();


            tableView.refresh();
        }
    }

    @Override
    @FXML
    public void onDeleteRowButtonClicked() {
        super.onDeleteRowButtonClicked();
    }

    @FXML
    public void onDuplicateRowButtonClicked() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < ZERO_INDEX) {
            selectedIndex = ZERO_INDEX;
        }

        var boreholeLayerDto = tableView.getItems().get(selectedIndex);

        var cloneDto = service.cloneDto(boreholeLayerDto);

        dtos.add(selectedIndex + SINGLE_INDEX_POINT, cloneDto);

        observableDtoList.clear();
        observableDtoList.addAll(dtos);

        tableView.setItems(observableDtoList);
    }

    @FXML
    public void onAddNewRowAtTheEndButtonClicked() {
        addNewRow();
    }

    private void addNewRow() {
        log.info("Добавлена новая строка");

        var boreholeLayerDto = new BoreholeLayerDto();

        boreholeLayerDto.setIsEditableFromEgeListBasic(Boolean.TRUE);

        observableDtoList.add(boreholeLayerDto);

        dtos.add(boreholeLayerDto);

        tableView.setItems(observableDtoList);
    }

    @FXML
    public void onSurveyPointNumberChoiceBoxClicked(ActionEvent event) {
        System.out.println("!-!-!-!-!-!-!-!");
//        surveyPointNumberChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
//            System.out.println("=!=!=!=!=!=");
//
//            if (Objects.nonNull(newValue)) {
//                var dtosFromOtherSurveyPoint = service.getDtosBySurveyPointId(newValue.getId());
//
//                dtos.addAll(dtosFromOtherSurveyPoint);
//                observableDtoList.clear();
//                observableDtoList.addAll(dtos);
//                sortObservableDtoList(observableDtoList);
//
//                tableView.setItems(observableDtoList);
//            }
//        });
    }

    @FXML
    public void onSoilKindChoiceViewButtonClicked(ActionEvent event) throws IOException {
        log.info("Нажата кнопка перехода на экран формирования описания КГА...");

        var childController = (BoreholeLayerSoilKindChoiceViewController) applicationContext.getBean("boreholeLayerSoilKindChoiceViewController");

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < ZERO_INDEX) {
            selectedIndex = ZERO_INDEX;
        }

        if (!tableView.getItems().isEmpty()) {
            var boreholeLayerDto = tableView.getItems().get(selectedIndex);

            if (Objects.isNull(boreholeLayerDto.getId())) {
                JavaFXCommonMethods.initAlert(HAVE_TO_SAVE_CHOSEN_BOREHOLE_LAYER_SERVICE_MESSAGE);
            } else {
                childController.passBoreholeLayerDto(boreholeLayerDto);

//                Refreshable refreshTable = () -> tableView.refresh();
                Refreshable refreshTable = () -> {
                    observableDtoList.clear();
                    observableDtoList.addAll(dtos);
                    tableView.setItems(observableDtoList);
                };

                childController.passTableRefreshLambda(refreshTable);

                JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.BOREHOLE_LAYER_SOIL_KIND_CHOICE_VIEW.getPath(),
                        applicationContext, stage, ViewsEnum.BOREHOLE_LAYER_SOIL_KIND_CHOICE_VIEW.getTitle());

            }
        }

//        var childController = (SoilKindChoiceViewController) applicationContext.getBean("soilKindChoiceViewController");
//
//        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
//
//        if (selectedIndex < ZERO_INDEX) {
//            selectedIndex = ZERO_INDEX;
//        }
//
//        if (!tableView.getItems().isEmpty()) {
//            var egeDto = tableView.getItems().get(selectedIndex);
//
//            if (Objects.isNull(egeDto.getId())) {
//                JavaFXCommonMethods.initAlert(HAVE_TO_SAVE_CHOSEN_EGE_SERVICE_MESSAGE);
//            } else {
//                childController.passEge(egeDto);
//
//                Refreshable refreshTable = () -> tableView.refresh();
//
//                childController.passTableRefreshLambda(refreshTable);
//
       //            }
//        }
    }

    @FXML
    public void onCopyDescriptionKgaToDescriptionCredoFormularButtonClicked() {
        System.out.println("onCopyDescriptionKgaToDescriptionCredoFormularButtonClicked");
        Settable<BoreholeLayerDto, String> settable = BoreholeLayerDto::setDescriptionCredoFormular;

//
        copyDescriptionKga(settable);
    }

    private void copyDescriptionKga(Settable<BoreholeLayerDto, String> settable) {
        var selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= ZERO_INDEX) {
            var boreholeLayerDto = tableView.getItems().get(selectedIndex);

            settable.setValue(boreholeLayerDto, boreholeLayerDto.getDescriptionKga());

            tableView.refresh();
        } else {
            JavaFXCommonMethods.initAlert("Необходимо выбрать строку");
        }
    }

//    @FXML
//    public void onChoiceBoxClicked() {
//        surveyPointChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
//                surveyPointChoiceBox.setOnAction(e -> tempSetListForTableView(surveyPointChoiceBox)));
//    }

    @Override
    @FXML
    public void onCameralModuleButtonClicked(ActionEvent event) throws IOException {
        log.info("Нажата кнопка смены экрана на Камеральный модуль. Уже на экране камерального модуля");
    }

    @FXML
    public void onCameralModuleMainViewButtonClicked() {
        log.info("Нажата кнопка смены экрана на главное окно Камерального модуля...");
    }

    @FXML
    public void onPumpButtonClicked() {
        log.info("Нажата кнопка смены экрана на окно Колонка...");
    }

    @FXML
    public void onStatisticalProcessingButtonClicked() {
        log.info("Нажата кнопка смены экрана на окно Статистической обработки...");
    }

    @FXML
    public void onEgeListButtonClicked(ActionEvent event) throws IOException {
        log.info("Нажата кнопка смены экрана на Список ИГЭ...");

        JavaFXCommonMethods.changeScene(event, ViewsEnum.EGE_LIST_VIEW.getPath(),
                applicationContext, ViewsEnum.EGE_LIST_VIEW.getTitle());
    }

    @FXML
    public void onBoreholeLayerButtonClicked(ActionEvent event) throws IOException {
        log.info("Нажата кнопка смены экрана на окно Послойное описание...");

        JavaFXCommonMethods.changeScene(event, ViewsEnum.BOREHOLE_LAYERS_DESCRIPTION_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.BOREHOLE_LAYERS_DESCRIPTION_MAIN_VIEW.getTitle());
    }

    @FXML
    public void onCameralModuleMainViewButtonClicked(ActionEvent event) throws IOException {

    }


    private void setSurveyPointChoiceBox(ChoiceBox<SurveyPointDto> choiceBox) {
        choiceBox.getItems().clear();

//        var surveyPoints = surveyPointsService.getDtosBySurveyPoint(currentState.getSurveyPoint());
        var surveyPoints = surveyPointsService.getDtosByProject(currentState.getCurrentProject());

        if (Objects.equals(choiceBox.getId(), SURVEY_POINT_NUMBER_CHOICE_BOX)) {
            surveyPoints = surveyPoints.stream()
                    .filter(surveyPointDto -> !Objects.equals(surveyPointDto.getPointNumber(), surveyPointChoiceBox.getValue().getPointNumber()))
                    .collect(Collectors.toList());
        }

        choiceBox.getItems().addAll(surveyPoints);

        var converter = new StringConverter<SurveyPointDto>() {
            @Override
            public String toString(SurveyPointDto object) {

                if (Objects.nonNull(object)) {
                    if (Objects.nonNull(object.getNameNumber())) {
                        return object.getNameNumber();
                    }
                }
                return StringUtils.EMPTY;
            }

            @Override
            public SurveyPointDto fromString(String string) {
                return null;
            }
        };

        choiceBox.setConverter(converter);

        if (Objects.equals(choiceBox.getId(), SURVEY_POINT_CHOICE_BOX)) {
            choiceBox.setValue(surveyPoints.isEmpty() ? null : surveyPoints.get(ZERO_INDEX));

            choiceBox.setOnAction(event -> {
                tempSetListForTableView(choiceBox);
            });
        } else {
            choiceBox.setValue(null);

            surveyPointNumberChoiceBox.setOnAction(actionEvent -> {
                if (Objects.nonNull(surveyPointNumberChoiceBox.getValue())) {
                    var dtosFromOtherSurveyPoint = service.getDtosBySurveyPointId(surveyPointNumberChoiceBox.getValue().getId());

                    dtosFromOtherSurveyPoint.forEach(dto -> dto.setId(null));

                    dtos.addAll(dtosFromOtherSurveyPoint);
                    observableDtoList.clear();
                    observableDtoList.addAll(dtos);
                    sortObservableDtoList(observableDtoList);

                    tableView.setItems(observableDtoList);
                }
            });
        }
    }

    //TODO: переписать на сеттинг current state в сервисе
    private void tempSetListForTableView(ChoiceBox<SurveyPointDto> boreholeChoiceBox) {
        var surveyPointDto = boreholeChoiceBox.getValue();
        SurveyPoint currentSurveyPoint = surveyPointsService.findByPointNumberAndProject(surveyPointDto.getPointNumber(), currentState.getCurrentProject());
        currentState.setSurveyPoint(currentSurveyPoint);
        JavaFXCommonMethods.setFooterElements(currentState, projectNameInFooter, projectCipherInFooter);
        setObjectListForView();

        tableView.refresh();
    }
}
