package org.geoproject.ingeo.controllers.cameral;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.util.StringConverter;
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
import org.geoproject.ingeo.services.classificators.kga.SoilKindGroupTypeService;
import org.geoproject.ingeo.services.classificators.kga.SoilKindService;
import org.geoproject.ingeo.services.classificators.kga.SoilSubkindAdjService;
import org.geoproject.ingeo.services.classificators.kga.SoilSubkindService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.ServiceConstants.CLT_NAME_FIELD;
import static org.geoproject.ingeo.constants.ServiceConstants.COMMA_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.ID_FIELD;
import static org.geoproject.ingeo.constants.ServiceConstants.NOT_DEFINED_SOIL_SUBKIND_ADJ_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.SINGLE_INDEX_POINT;
import static org.geoproject.ingeo.constants.ServiceConstants.SOIL_SUBKIND_FIElD_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.WATER_FULL_PATTERN;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

@Log4j2
@Component
public class SoilKindChoiceViewController implements Initializable {

    protected final ConfigurableApplicationContext applicationContext;
    protected final CurrentState currentState;
    private final SoilClassService soilClassService;
    private final SoilKindService soilKindService;
    private final SoilClassKindGroupService soilClassKindGroupService;
    private final SoilKindGroupTypeService soilKindGroupTypeService;
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

    Scene scene;
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
                                        SoilClassService soilClassService, SoilKindService soilKindService, SoilClassKindGroupService soilClassKindGroupService, SoilKindGroupTypeService soilKindGroupTypeService, SoilSubkindService soilSubkindService, SoilSubkindAdjService soilSubkindAdjService, ColorService colorService, EgeServise egeServise) {
        this.applicationContext = applicationContext;
        this.currentState = currentState;
        this.soilClassService = soilClassService;
        this.soilKindService = soilKindService;
        this.soilClassKindGroupService = soilClassKindGroupService;
        this.soilKindGroupTypeService = soilKindGroupTypeService;
        this.soilSubkindService = soilSubkindService;
        this.soilSubkindAdjService = soilSubkindAdjService;
        this.colorService = colorService;
        this.egeServise = egeServise;
    }

    public void passEge(Ege ege) {
        this.ege = ege;
    }

    public void passTableRefreshLambda(Refreshable refreshTable) {
        this.refreshTable = refreshTable;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("SoilKindChoiceViewController init!!!");

        setDto();
        setDescriptionCredoFormularTextArea();
        setDescriptionKgaTextArea();
        setSoilSubkindListView();
        setSoilClassChoiceBox();
        setSoilKindGroupChoiceBox();
        setColumnsMap();
        setSoilKindTableView();
        setSoilKindGroupTypeChoiceBox();
        setSoilSubkindChoiceBox();
        setSoilSubkindAdjChoiceBox();
        setColorChoiceBox();
        setWaterDepth();
    }

    public void setDto() {
        egeDto = egeServise.getDto(ege);
        descriptionKgaDto = egeServise.getDescriptionKgaDto(ege);
    }

    public void setDescriptionKgaTextArea() {
        var soilSubkindMapStringBuilder = new StringBuilder();
        descriptionKgaDto.getSoilSubkindMap().values()
                .stream()
                .map(soilSubkind -> Objects.isNull(soilSubkind) ? StringUtils.EMPTY : soilSubkind.getSsDescr())
//                .forEach(soilSubkindMapStringBuilder::append);
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
//                .forEach(soilSubkindAdjMapStringBuilder::append);
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
//                .append(soilSubkindMapStringBuilder.isEmpty() ? StringUtils.EMPTY : COMMA_PATTERN)
                .append(getColor())
                .append(Objects.isNull(descriptionKgaDto.getColor()) ? StringUtils.EMPTY : COMMA_PATTERN)
                .append(soilSubkindAdjMapStringBuilder)
//                .append(soilSubkindAdjMapStringBuilder.isEmpty() ? StringUtils.EMPTY : COMMA_PATTERN)
                .append(Objects.isNull(descriptionKgaDto.getWaterDepth()) ? StringUtils.EMPTY : String.format(WATER_FULL_PATTERN, descriptionKgaDto.getWaterDepth()))
                .toString();

        descriptionKgaTextArea.setText(text);
    }

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

        if (Objects.isNull(descriptionKgaDto.getSoilKind())) {
            return StringUtils.EMPTY;
        }

        if (Objects.isNull(descriptionKgaDto.getSoilKind().getSkDescr())) {
            return StringUtils.EMPTY;
        }

        return descriptionKgaDto.getSoilKind().getSkDescr();
    }

    public void setSoilSubkindListView() {
        soilSubkindListView.getItems().clear();

        soilSubkindListView.setCellFactory(param -> new ListCell<SoilSubkind>() {
            @Override
            protected void updateItem(SoilSubkind adj, boolean empty) {
                super.updateItem(adj, empty);
                if (empty || adj == null || adj.getSsDescr() == null) {
                    setText(StringUtils.EMPTY);
                } else {
                    setText(adj.getSsDescr());
                }
            }
        });

        var items =
                FXCollections.observableArrayList(descriptionKgaDto.getSoilSubkindMap().values());

        soilSubkindListView.setItems(items);
    }

    public void setSoilClassChoiceBox() {
        soilClassChoiceBox.setConverter(new StringConverter<SoilClass>() {
            @Override
            public String toString(SoilClass object) {
                return Objects.nonNull(object) ? object.getScName() : StringUtils.EMPTY;
            }

            @Override
            public SoilClass fromString(String string) {
                return null;
            }
        });

        fillSoilClassChoiceBox();
        fillSoilKindGroupChoiceBox(soilClassChoiceBox.getValue());

        soilClassChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                fillSoilKindGroupChoiceBox(newValue);
                soilKindGroupChoiceBox.setValue(soilGroups.get(ZERO_INDEX));

                descriptionKgaDto.setSoilClass(newValue);
            }
        });
    }

    public void setSoilKindGroupChoiceBox() {
        soilKindGroupChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(SoilClassKindGroup object) {
                return Objects.nonNull(object) ? object.getSoilKindGroup() : StringUtils.EMPTY;
            }

            @Override
            public SoilClassKindGroup fromString(String string) {
                return null;
            }
        });

        soilKindGroupChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                descriptionKgaDto.setSoilClassKindGroup(newValue);
                setSoilKindTableView();
            }
        });
    }

    public void setSoilKindGroupTypeChoiceBox() {
        soilKindGroupTypeChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(SoilKindGroupType object) {
                return Objects.nonNull(object) ? String.valueOf(object.getId()) : StringUtils.EMPTY;
            }

            @Override
            public SoilKindGroupType fromString(String string) {
                return null;
            }
        });

        fillSoilKindGroupTypeChoiceBox();

        soilKindGroupTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (Objects.nonNull(newValue)) {
                fillSoilSubkindChoiceBox(newValue);

                if (!soilSubkindList.isEmpty()) {
                    soilSubkindChoiceBox.setValue(soilSubkindList.get(ZERO_INDEX));
                }
            }
        });
    }

    private void setSoilSubkindChoiceBox() {
        soilSubkindChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(SoilSubkind object) {
                return Objects.nonNull(object) ? object.getSsDescr() : StringUtils.EMPTY;
            }

            @Override
            public SoilSubkind fromString(String string) {
                return null;
            }
        });

        fillSoilSubkindChoiceBox(soilKindGroupTypeList.get(ZERO_INDEX));
    }

    private void setColorChoiceBox() {
        colorChoiceBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Color object) {
                return Objects.nonNull(object) ? object.getCltName() : StringUtils.EMPTY;
            }

            @Override
            public Color fromString(String string) {
                return null;
            }
        });

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

    private void setWaterDepth() {
        var depth = descriptionKgaDto.getWaterDepth();

        waterDepth.setText(Objects.isNull(depth) ? StringUtils.EMPTY : String.valueOf(depth));
    }

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

    @FXML
    private void setSoilSubkindAdjChoiceBox() {
        soilSubkindAdjList = soilSubkindAdjService.getAll();

        List<ChoiceBox<SoilSubkindAdj>> choiceBoxList = new ArrayList<>(List.of(SSA1, SSA2, SSA3,
                SSA4, SSA5, SSA6, SSA7, SSA8, SSA9, SSA10, SSA11, SSA12));

        for (ChoiceBox<SoilSubkindAdj> soilSubkindAdjChoiceBox : choiceBoxList) {
            soilSubkindAdjChoiceBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(SoilSubkindAdj object) {
                    return Objects.nonNull(object) ? object.getSsaDescr() : StringUtils.EMPTY;
                }

                @Override
                public SoilSubkindAdj fromString(String string) {
                    return null;
                }
            });

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

    @FXML
    void onSoilSubkindChooseButtonClicked() {
        log.info("Нажата кнопка выбора типа группы");

        var items = descriptionKgaDto.getSoilSubkindMap();

        var value = soilSubkindChoiceBox.getValue();

        var isChanged = Boolean.FALSE;

        for (String key : items.keySet()) {
            if (Objects.isNull(items.get(key))) {
                items.put(key, value);
                isChanged = Boolean.TRUE;
                break;
            }
        }

        if (isChanged) {
            setSoilSubkindListView();
            setDescriptionKgaTextArea();
        } else {
            initAlert("Все слоты для типов группы заполнены. Удалите любое значение");
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

        soilKindGroupTypeChoiceBox.setValue(soilKindGroupTypeList.get(ZERO_INDEX));
    }

    private void fillSoilSubkindChoiceBox(SoilKindGroupType newValue) {
        soilSubkindChoiceBox.getItems().clear();

        soilSubkindList = soilSubkindService.getBySoilKindGroupType(newValue);
        var items = FXCollections.observableArrayList(soilSubkindList);
        soilSubkindChoiceBox.getItems().addAll(items);

        if (!soilSubkindList.isEmpty()) {
            soilSubkindChoiceBox.setValue(soilSubkindList.get(ZERO_INDEX));
        }
    }

    public void setDescriptionCredoFormularTextArea() {
        descriptionCredoFormularTextArea.setText(egeDto.getDescriptionCredoFormular());
    }

    private void setSoilKindTableView() {
        log.info("setSoilKindTableView method called...");

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
            var button = new Button("Выбрать");
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
                setDescriptionKgaTextArea();
                setSoilKindGroupTypeChoiceBox();
                setSoilSubkindListView();
            });

            return cell;
        });
    }

    void initAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hey!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setColumnsMap() {
        List<TableColumn<SoilKindDto, ?>> columns = new ArrayList<>();

        for (var column : soilKindTableView.getColumns()) {
            columns.addAll(JavaFXCommonMethods.getAllLeaves(column));
        }

        columnsMap = new LinkedHashMap<>();

        for (TableColumn<SoilKindDto, ?> column : columns) {
            columnsMap.put(column, column.getId());
        }
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        saveEntity();
    }

    private void saveEntity() {
        descriptionKgaDto.setDescriptionKga(descriptionKgaTextArea.getText());
        egeServise.updateEge(ege, descriptionKgaDto);
    }

    @FXML
    public void onCloseButtonClicked(ActionEvent event) {
        onSaveButtonClicked(event);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

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
            initAlert("Не выбрана строка в списке типов групп");
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

}
