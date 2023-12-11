package org.geoproject.ingeo.controllers.cameral;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.geoproject.ingeo.controllers.functionalInterfaces.Refreshable;
import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilKindDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.classificators.kga.Color;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.geoproject.ingeo.services.cameral.EgeServise;
import org.geoproject.ingeo.services.classificators.kga.ColorService;
import org.geoproject.ingeo.services.classificators.kga.SoilClassKindGroupService;
import org.geoproject.ingeo.services.classificators.kga.SoilClassService;
import org.geoproject.ingeo.services.classificators.kga.SoilGroupTypeService;
import org.geoproject.ingeo.services.classificators.kga.SoilKindGroupTypeService;
import org.geoproject.ingeo.services.classificators.kga.SoilKindService;
import org.geoproject.ingeo.services.classificators.kga.SoilSubkindAdjService;
import org.geoproject.ingeo.services.classificators.kga.SoilSubkindService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.geoproject.ingeo.constants.ServiceConstants.CHOICE_BUTTON_TEXT;
import static org.geoproject.ingeo.constants.ServiceConstants.CLT_NAME_FIELD;
import static org.geoproject.ingeo.constants.ServiceConstants.COMMA_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.NOT_DEFINED_SOIL_SUBKIND_ADJ_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.SINGLE_INDEX_POINT;
import static org.geoproject.ingeo.constants.ServiceConstants.SOIL_SUBKIND_FIElD_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.WATER_FULL_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

/**
 * Контроллер создания описания по классификатору КГА
 */
@Log4j2
@Component
public class SoilKindChoiceViewController implements Initializable {

    protected final ConfigurableApplicationContext applicationContext;
    protected final CurrentState currentState;
    private final SoilClassService soilClassService;
    private final SoilKindService soilKindService;
    private final SoilClassKindGroupService soilClassKindGroupService;
    private final SoilKindGroupTypeService soilKindGroupTypeService;
    private final SoilGroupTypeService soilGroupTypeService;
    private final SoilSubkindService soilSubkindService;
    private final SoilSubkindAdjService soilSubkindAdjService;
    private final ColorService colorService;
    private final EgeServise egeServise;

    private Ege ege;
    private EgeDto egeDto;
    private DescriptionKgaDto descriptionKgaDto;

    Refreshable refreshTable;

    List<SoilClass> soilClasses;
    List<SoilClassKindGroup> soilGroups;
    List<SoilKindGroupType> soilKindGroupTypeList;
    List<SoilSubkind> soilSubkindList;
    List<SoilSubkindAdj> soilSubkindAdjList;
    List<Color> colorList;

    @FXML
    TextArea descriptionCredoFormularTextArea;
    @FXML
    TextArea descriptionKgaTextArea;
    @FXML
    ChoiceBox<SoilClass> soilClassChoiceBox;
    @FXML
    ChoiceBox<SoilClassKindGroup> soilKindGroupChoiceBox;
    @FXML
    ChoiceBox<SoilKindGroupType> soilKindGroupTypeChoiceBox;
    @FXML
    ChoiceBox<SoilSubkind> soilSubkindChoiceBox;
    @FXML
    ChoiceBox<Color> colorChoiceBox;
    @FXML
    ListView<SoilSubkind> soilSubkindListView;
    @FXML
    TextField waterDepth;

    @FXML
    ChoiceBox<SoilSubkindAdj> SSA1;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA2;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA3;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA4;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA5;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA6;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA7;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA8;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA9;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA10;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA11;
    @FXML
    ChoiceBox<SoilSubkindAdj> SSA12;

    @FXML
    TableView<SoilKindDto> soilKindTableView;
    @FXML
    TableColumn<SoilKindDto, SoilKindDto> soilKindChooseButton;

    public Map<TableColumn<SoilKindDto, ?>, String> columnsMap;


    public SoilKindChoiceViewController(ConfigurableApplicationContext applicationContext, CurrentState currentState,
                                        SoilClassService soilClassService, SoilKindService soilKindService, SoilClassKindGroupService soilClassKindGroupService, SoilKindGroupTypeService soilKindGroupTypeService, SoilGroupTypeService soilGroupTypeService, SoilSubkindService soilSubkindService, SoilSubkindAdjService soilSubkindAdjService, ColorService colorService, EgeServise egeServise) {
        this.applicationContext = applicationContext;
        this.currentState = currentState;
        this.soilClassService = soilClassService;
        this.soilKindService = soilKindService;
        this.soilClassKindGroupService = soilClassKindGroupService;
        this.soilKindGroupTypeService = soilKindGroupTypeService;
        this.soilGroupTypeService = soilGroupTypeService;
        this.soilSubkindService = soilSubkindService;
        this.soilSubkindAdjService = soilSubkindAdjService;
        this.colorService = colorService;
        this.egeServise = egeServise;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Открыто окно создания описания КГА (SoilKindChoiceViewController)");

        setDto();

        setDescriptionCredoFormularTextArea();
        setDescriptionKgaTextArea();

        setSoilSubkindListView();

        setSoilClassChoiceBox();
        setSoilKindGroupChoiceBox();
        setSoilKindGroupTypeChoiceBox();
        setSoilSubkindChoiceBox();
        setSoilSubkindAdjChoiceBox();
        setColorChoiceBox();

        setColumnsMap();
        setSoilKindTableView();

        setWaterDepth();
    }

    //region передача объектов из вышестоящего контроллера
    public void passEge(EgeDto egeDto) {
        this.egeDto = egeDto;
    }

    /**
     * Из вышестоящего контроллера передается функциональный интерфейс для запуска обновления таблицы ИГЭ из модального окна
     *
     * @param refreshTable функциональный интерфейс, которым объявлен метод обновления таблицы
     */
    public void passTableRefreshLambda(Refreshable refreshTable) {
        this.refreshTable = refreshTable;
    }
    //endregion

    private void setDto() {
//        egeDto = egeServise.getDto(ege);
        descriptionKgaDto = egeServise.getDescriptionKgaDto(egeDto.getId());
    }

    //region заполнение текстовых блоков (TextArea)
    public void setDescriptionCredoFormularTextArea() {
        descriptionCredoFormularTextArea.setText(egeDto.getDescriptionCredoFormular());
    }

    public void setDescriptionKgaTextArea() {
        var soilSubkindMapStringBuilder = new StringBuilder();
        descriptionKgaDto.getSoilSubkindMap().values()
                .stream()
                .map(soilSubkind -> Objects.isNull(soilSubkind) ? StringUtils.EMPTY : soilSubkind.getSsDescr())
                .forEach(description -> {
                    if (!Objects.equals(description, StringUtils.EMPTY)) {
                        soilSubkindMapStringBuilder.append(description);
                        soilSubkindMapStringBuilder.append(COMMA_PATTERN);
                    }
                });

        var soilSubkindAdjMapStringBuilder = new StringBuilder();
        descriptionKgaDto.getSoilSubkindAdjMap().values()
                .stream()
                .map(soilSubkindAdj -> Objects.isNull(soilSubkindAdj) ? StringUtils.EMPTY : soilSubkindAdj.getSsaDescr())
                .forEach(description -> {
                    if (!Objects.equals(description, StringUtils.EMPTY)) {
                        soilSubkindAdjMapStringBuilder.append(description);
                        soilSubkindAdjMapStringBuilder.append(COMMA_PATTERN);
                    }
                });

        var text = new StringBuilder()
                .append(getSoilKindDescription())
                .append(Objects.isNull(descriptionKgaDto.getSoilKind()) ||
                        (Objects.isNull(descriptionKgaDto.getSoilKind().getSkDescr()) || descriptionKgaDto.getSoilKind().getSkDescr().isEmpty()) ?
                        StringUtils.EMPTY : COMMA_PATTERN)
                .append(soilSubkindMapStringBuilder)
                .append(getColor())
                .append(Objects.isNull(descriptionKgaDto.getColor()) ? StringUtils.EMPTY : COMMA_PATTERN)
                .append(soilSubkindAdjMapStringBuilder)
                .append(Objects.isNull(descriptionKgaDto.getWaterDepth()) ? StringUtils.EMPTY : String.format(WATER_FULL_PATTERN, descriptionKgaDto.getWaterDepth()))
                .toString();

        descriptionKgaTextArea.setText(text);
    }
    //endregion

    //region сеттеры таблицы и списка (ListView)
    private void setSoilKindTableView() {
        var currentSoilKindGroupChoiceBox = soilKindGroupChoiceBox.getValue();

        var dtos = soilKindService.getDtos(currentSoilKindGroupChoiceBox);
        ObservableList<SoilKindDto> observableList = FXCollections.observableArrayList();
        observableList.addAll(dtos);

        columnsMap.keySet().forEach(column -> JavaFXCommonMethods.setCellFactory(column, soilKindTableView,
                null, null, null));

        columnsMap.forEach((column, columnName) -> column.setCellValueFactory(new PropertyValueFactory<>(columnName)));

        soilKindTableView.setItems(observableList);

        soilKindChooseButton.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<SoilKindDto>(cellData.getValue()));

        soilKindChooseButton.setCellFactory(col -> {
            var button = new Button(CHOICE_BUTTON_TEXT);
            var cell = new TableCell<SoilKindDto, SoilKindDto>() {
                @Override
                public void updateItem(SoilKindDto soilKindDto, boolean empty) {
                    super.updateItem(soilKindDto, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
            };

            button.setOnAction(e -> {
                int index = cell.getTableRow().getIndex();

                var soilKindDto = soilKindTableView.getItems().get(index);

                var soilKind = soilKindService.getById(soilKindDto.getId());

                descriptionKgaDto.setSoilKind(soilKind);
                descriptionKgaDto.getSoilSubkindMap().forEach((key, value) -> descriptionKgaDto.getSoilSubkindMap().replace(key, null));
                descriptionKgaDto.getSoilSubkindAdjMap().forEach((key, value) -> descriptionKgaDto.getSoilSubkindAdjMap().replace(key, null));
                descriptionKgaDto.setColor(null);
                descriptionKgaDto.setWaterDepth(null);

                setDescriptionKgaTextArea();
                setSoilKindGroupTypeChoiceBox();
                setSoilSubkindListView();
            });

            return cell;
        });
    }

    public void setSoilSubkindListView() {
        soilSubkindListView.getItems().clear();

        soilSubkindListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(SoilSubkind soilSubkind, boolean empty) {
                super.updateItem(soilSubkind, empty);
                if (empty || soilSubkind == null || soilSubkind.getSsDescr() == null) {
                    setText(StringUtils.EMPTY);
                } else {
                    setText(soilSubkind.getSsDescr());
                }
            }
        });

        var items = FXCollections.observableArrayList(descriptionKgaDto.getSoilSubkindMap().values());

        soilSubkindListView.setItems(items);
    }
    //endregion

    //region сеттеры выпадающих списков
    public void setSoilClassChoiceBox() {
        JavaFXCommonMethods.setConverterForChoiceBox(soilClassChoiceBox, null);
        fillSoilClassChoiceBox();
        fillSoilKindGroupChoiceBox(soilClassChoiceBox.getValue());

        soilClassChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                fillSoilKindGroupChoiceBox(newValue);
                descriptionKgaDto.setSoilClass(newValue);
            }
        });
    }

    public void setSoilKindGroupChoiceBox() {
        JavaFXCommonMethods.setConverterForChoiceBox(soilKindGroupChoiceBox, null);

        soilKindGroupChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                descriptionKgaDto.setSoilClassKindGroup(newValue);
                setSoilKindTableView();
            }
        });
    }

    public void setSoilKindGroupTypeChoiceBox() {
        JavaFXCommonMethods.setConverterForChoiceBox(soilKindGroupTypeChoiceBox, soilGroupTypeService::getById);
        fillSoilKindGroupTypeChoiceBox();

        soilKindGroupTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                fillSoilSubkindChoiceBox(newValue);
            }
        });
    }

    private void setSoilSubkindChoiceBox() {
        JavaFXCommonMethods.setConverterForChoiceBox(soilSubkindChoiceBox, null);
        fillSoilSubkindChoiceBox(soilKindGroupTypeChoiceBox.getValue());
    }

    private void setColorChoiceBox() {
        JavaFXCommonMethods.setConverterForChoiceBox(colorChoiceBox, null);

        fillColorChoiceBox();

        colorChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                if (Objects.isNull(newValue.getCltName())) {
                    descriptionKgaDto.setColor(null);
                } else {
                    descriptionKgaDto.setColor(newValue);
                }

                setDescriptionKgaTextArea();
            }
        });
    }

    @FXML
    private void setSoilSubkindAdjChoiceBox() {
        soilSubkindAdjList = soilSubkindAdjService.getAll();

        List<ChoiceBox<SoilSubkindAdj>> choiceBoxList = new ArrayList<>(List.of(SSA1, SSA2, SSA3,
                SSA4, SSA5, SSA6, SSA7, SSA8, SSA9, SSA10, SSA11, SSA12));

        for (ChoiceBox<SoilSubkindAdj> soilSubkindAdjChoiceBox : choiceBoxList) {
            JavaFXCommonMethods.setConverterForChoiceBox(soilSubkindAdjChoiceBox, null);

            soilSubkindAdjChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                var items = descriptionKgaDto.getSoilSubkindAdjMap();

                var value = soilSubkindAdjChoiceBox.getValue();

                if (Objects.equals(value.getSsaDescr(), NOT_DEFINED_SOIL_SUBKIND_ADJ_PATTERN)) {
                    items.put(soilSubkindAdjChoiceBox.getId(), null);
                } else {
                    items.put(soilSubkindAdjChoiceBox.getId(), value);
                }

                setDescriptionKgaTextArea();
            });

            fillSoilSubkindAdjChoiceBox(soilSubkindAdjChoiceBox);
        }
    }
    //endregion

    //region заполнение элементов выпадающих списков
    private void fillColorChoiceBox() {
        colorList = colorService.getAll(CLT_NAME_FIELD);

        colorChoiceBox.getItems().clear();

        var items = FXCollections.observableArrayList(colorList);
        colorChoiceBox.getItems().addAll(items);

        if (Objects.isNull(descriptionKgaDto.getColor())) {
            colorChoiceBox.setValue(colorList.get(ZERO_INDEX));
        } else {
            colorChoiceBox.setValue(descriptionKgaDto.getColor());
        }
    }

    private void fillSoilSubkindAdjChoiceBox(ChoiceBox<SoilSubkindAdj> soilSubkindAdjChoiceBox) {
        soilSubkindAdjChoiceBox.getItems().clear();
        var items = FXCollections.observableArrayList(soilSubkindAdjList);
        soilSubkindAdjChoiceBox.getItems().addAll(items);

        if (Objects.isNull(descriptionKgaDto.getSoilSubkindAdjMap().get(soilSubkindAdjChoiceBox.getId()))) {
            soilSubkindAdjChoiceBox.setValue(soilSubkindAdjList.get(ZERO_INDEX));
        } else {
            soilSubkindAdjChoiceBox.setValue(descriptionKgaDto.getSoilSubkindAdjMap().get(soilSubkindAdjChoiceBox.getId()));
        }
    }

    public void fillSoilClassChoiceBox() {
        soilClassChoiceBox.getItems().clear();

        soilClasses = soilClassService.getAll();
        var items = FXCollections.observableArrayList(soilClasses);
        soilClassChoiceBox.getItems().addAll(items);

        if (Objects.nonNull(descriptionKgaDto.getSoilClass())) {
            soilClassChoiceBox.setValue(descriptionKgaDto.getSoilClass());
        } else {
            soilClassChoiceBox.setValue(soilClasses.get(ZERO_INDEX));
            descriptionKgaDto.setSoilClass(soilClasses.get(ZERO_INDEX));
        }
    }

    public void fillSoilKindGroupChoiceBox(SoilClass currentSoilClass) {
        soilKindGroupChoiceBox.getItems().clear();

        soilGroups = soilClassKindGroupService.getBySoilClass(currentSoilClass);
        var items = FXCollections.observableArrayList(soilGroups);
        soilKindGroupChoiceBox.getItems().addAll(items);

        if (Objects.nonNull(descriptionKgaDto.getSoilClassKindGroup())) {
            soilKindGroupChoiceBox.setValue(descriptionKgaDto.getSoilClassKindGroup());
        } else {
            soilKindGroupChoiceBox.setValue(soilGroups.get(ZERO_INDEX));
            descriptionKgaDto.setSoilClassKindGroup(soilGroups.get(ZERO_INDEX));
        }
    }

    public void fillSoilKindGroupTypeChoiceBox() {
        soilKindGroupTypeChoiceBox.getItems().clear();

        soilKindGroupTypeList = soilKindGroupTypeService.getBySoilKind(descriptionKgaDto.getSoilKind());

        var items = FXCollections.observableArrayList(soilKindGroupTypeList);
        soilKindGroupTypeChoiceBox.getItems().addAll(items);

        var value = soilKindGroupTypeList.isEmpty() ? null : soilKindGroupTypeList.get(ZERO_INDEX);

        soilKindGroupTypeChoiceBox.setValue(value);
    }

    private void fillSoilSubkindChoiceBox(SoilKindGroupType newValue) {
        soilSubkindChoiceBox.getItems().clear();

        var notFilteredSoilSubkindList = soilSubkindService.getBySoilKindGroupType(newValue);

        /**
         * Здесь из списка типов групп (SoilSubKind) удаляются те экземпляры, у которых в таблице
         * в поле ss_descr сохранен null.
         * Это делается для того, чтобы в выпадающем списке не было пустых полей.
         */
        soilSubkindList = notFilteredSoilSubkindList.stream()
                .filter(soilSubkind -> Objects.nonNull(soilSubkind.getSsDescr()))
                .collect(Collectors.toList());

        var items = FXCollections.observableArrayList(soilSubkindList);
        soilSubkindChoiceBox.getItems().addAll(items);

        if (!soilSubkindList.isEmpty()) {
            soilSubkindChoiceBox.setValue(soilSubkindList.get(ZERO_INDEX));
        }
    }
    //endregion

    //region buttons
    @FXML
    void onSoilSubkindChooseButtonClicked() {
        var items = descriptionKgaDto.getSoilSubkindMap();

        var value = soilSubkindChoiceBox.getValue();

        var isChanged = Boolean.FALSE;

        for (Map.Entry<String, SoilSubkind> entry : items.entrySet()) {
            if (Objects.isNull(entry.getValue())) {
                entry.setValue(value);
                isChanged = Boolean.TRUE;
                break;
            }
        }

        if (isChanged) {
            setSoilSubkindListView();
            setDescriptionKgaTextArea();
        } else {
            JavaFXCommonMethods.initAlert("Все слоты для типов группы заполнены. Удалите любое значение");
        }
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        saveEntity();
    }

    @FXML
    public void onCloseButtonClicked(ActionEvent event) {
        onSaveButtonClicked(event);

        onCloseWithoutSavingButtonClicked(event);

        refreshTable.refresh();
    }

    @FXML
    public void onCloseWithoutSavingButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onSoilSubkindDeleteButtonClicked(ActionEvent event) {
        var selectedIndex = soilSubkindListView.getSelectionModel().getSelectedIndex();

        if (Objects.isNull(selectedIndex) || selectedIndex < ZERO_INDEX) {
            JavaFXCommonMethods.initAlert("Не выбрана строка в списке типов групп");
        } else {
            descriptionKgaDto.getSoilSubkindMap().replace(SOIL_SUBKIND_FIElD_PATTERN + (selectedIndex + SINGLE_INDEX_POINT), null);
            setDescriptionKgaTextArea();
            setSoilSubkindListView();
        }
    }

    @FXML
    public void onAddWaterDepthButtonClicked(ActionEvent event) {
        descriptionKgaDto.setWaterDepth(Float.valueOf(waterDepth.getText()));
        setDescriptionKgaTextArea();
    }

    @FXML
    public void onDeleteWaterDepthButtonClicked(ActionEvent event) {
        descriptionKgaDto.setWaterDepth(null);
        setDescriptionKgaTextArea();
    }
    //endregion

    private String getColor() {

        if (Objects.isNull(descriptionKgaDto.getColor())) {
            return StringUtils.EMPTY;
        }

        if (Objects.isNull(descriptionKgaDto.getColor().getCltName()) ||
                Objects.equals(descriptionKgaDto.getColor().getCltName(), StringUtils.EMPTY)) {
            return StringUtils.EMPTY;
        }

        return descriptionKgaDto.getColor().getCltName();
    }

    private String getSoilKindDescription() {

        if (Objects.isNull(descriptionKgaDto.getSoilKind()) || Objects.isNull(descriptionKgaDto.getSoilKind().getSkDescr())) {
            return StringUtils.EMPTY;
        }

        return descriptionKgaDto.getSoilKind().getSkDescr();
    }

    private void setWaterDepth() {
        var depth = descriptionKgaDto.getWaterDepth();

        waterDepth.setText(Objects.isNull(depth) ? StringUtils.EMPTY : String.valueOf(depth));
    }

    private void saveEntity() {
        descriptionKgaDto.setDescriptionKga(descriptionKgaTextArea.getText());
        egeServise.updateEge(descriptionKgaDto);
    }

    private void setColumnsMap() {
        var columns = soilKindTableView.getColumns().stream()
                .map(JavaFXCommonMethods::getAllLeaves)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        columnsMap = columns.stream()
                .collect(Collectors.toMap(Function.identity(), TableColumn::getId, (e1, e2) -> e2, LinkedHashMap::new));
    }
}
