package org.geoproject.ingeo.controllers.cameral;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilKindDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.services.cameral.EgeServise;
import org.geoproject.ingeo.services.classificators.kga.SoilClassKindGroupService;
import org.geoproject.ingeo.services.classificators.kga.SoilClassService;
import org.geoproject.ingeo.services.classificators.kga.SoilKindGroupTypeService;
import org.geoproject.ingeo.services.classificators.kga.SoilKindService;
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

import static org.geoproject.ingeo.constants.ServiceConstants.COMMA_PATTERN;
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
    private final EgeServise egeServise;

    private Ege ege;
    private EgeDto egeDto;
    private DescriptionKgaDto descriptionKgaDto;

    List<SoilClass> soilClasses;
    List<SoilClassKindGroup> soilGroups;
    List<SoilKindGroupType> soilKindGroupTypeList;
    List<SoilSubkind> soilSubkindList;


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
    ListView<SoilSubkind> soilSubkindListView;
    @FXML
    TableView<SoilKindDto> soilKindTableView;
    @FXML
    TableColumn<SoilKindDto, SoilKindDto> soilKindChooseButton;

    public Map<TableColumn<SoilKindDto, ?>, String> columnsMap;

    public SoilKindChoiceViewController(ConfigurableApplicationContext applicationContext, CurrentState currentState,
                                        SoilClassService soilClassService, SoilKindService soilKindService, SoilClassKindGroupService soilClassKindGroupService, SoilKindGroupTypeService soilKindGroupTypeService, SoilSubkindService soilSubkindService, EgeServise egeServise) {
        this.applicationContext = applicationContext;
        this.currentState = currentState;
        this.soilClassService = soilClassService;
        this.soilKindService = soilKindService;
        this.soilClassKindGroupService = soilClassKindGroupService;
        this.soilKindGroupTypeService = soilKindGroupTypeService;
        this.soilSubkindService = soilSubkindService;
        this.egeServise = egeServise;
    }

    public void passEge(Ege ege) {
        this.ege = ege;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("SoilKindChoiceViewController init!!!");

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
                .forEach(soilSubkindMapStringBuilder::append);

        var soilSubkindAdjMapStringBuilder = new StringBuilder();
        descriptionKgaDto.getSoilSubkindAdjMap().values()
                .stream()
                .map(soilSubkindAdj -> Objects.isNull(soilSubkindAdj) ? StringUtils.EMPTY : soilSubkindAdj.getSsaDescr())
                .forEach(soilSubkindAdjMapStringBuilder::append);


        var text = new StringBuilder()
                .append(Objects.isNull(descriptionKgaDto.getSoilKind()) ? StringUtils.EMPTY : descriptionKgaDto.getSoilKind().getSkDescr())
                .append(Objects.isNull(descriptionKgaDto.getSoilKind()) ? StringUtils.EMPTY : COMMA_PATTERN)
                .append(soilSubkindMapStringBuilder)
                .append(soilSubkindMapStringBuilder.isEmpty() ? StringUtils.EMPTY : COMMA_PATTERN)
                .append(Objects.isNull(descriptionKgaDto.getColor()) ? StringUtils.EMPTY : descriptionKgaDto.getColor())
                .append(Objects.isNull(descriptionKgaDto.getColor()) ? StringUtils.EMPTY : COMMA_PATTERN)
                .append(soilSubkindAdjMapStringBuilder)
                .append(soilSubkindAdjMapStringBuilder.isEmpty() ? StringUtils.EMPTY : COMMA_PATTERN)
                .append(Objects.isNull(descriptionKgaDto.getWaterDepth()) ? StringUtils.EMPTY : String.format(WATER_FULL_PATTERN, descriptionKgaDto.getWaterDepth()))
                .toString();

        descriptionKgaTextArea.setText(text);
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
                    soilSubkindListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        if (soilSubkindListView.isFocused()) {
                            System.out.println(newValue.toString());
                        }
                    });
                }
            }
        });

        var items =
                FXCollections.observableArrayList(descriptionKgaDto.getSoilSubkindMap().values());

        soilSubkindListView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            System.out.println("ListView item");
            System.out.println(newValue);
            System.out.println(newValue.getId());
            System.out.println(newValue.getSsDescr());
        });

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
                soilSubkindChoiceBox.setValue(soilSubkindList.get(ZERO_INDEX));
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

//        if (Objects.nonNull(descriptionKgaDto.getSoilClass())) {
//            soilClassChoiceBox.setValue(descriptionKgaDto.getSoilClass());
//        } else {
        soilKindGroupTypeChoiceBox.setValue(soilKindGroupTypeList.get(ZERO_INDEX));
//            descriptionKgaDto.setSoilClass(soilClasses.get(ZERO_INDEX));
//        }

    }

    private void fillSoilSubkindChoiceBox(SoilKindGroupType newValue) {
        soilSubkindChoiceBox.getItems().clear();

        soilSubkindList = soilSubkindService.getBySoilKindGroupType(newValue);
        var items = FXCollections.observableArrayList(soilSubkindList);
        soilSubkindChoiceBox.getItems().addAll(items);

        soilSubkindChoiceBox.setValue(soilSubkindList.get(ZERO_INDEX));
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

//        columnsMap.keySet().forEach(column -> column.setCellFactory(TextFieldTableCell.forTableColumn()));
        columnsMap.keySet().forEach(column -> JavaFXCommonMethods.setCellFactory(column, soilKindTableView,
                null, null, null));

        columnsMap.forEach((column, columnName) -> column.setCellValueFactory(new PropertyValueFactory<>(columnName)));

        soilKindTableView.setItems(observableList);

//        soilKindChooseButton

//        soilKindChooseButton.setCellValueFactory(new PropertyValueFactory<Ege, Genesis>("genesis"));
//        soilKindChooseButton.setCellFactory(ButtonTableCell.forTableColumn(genesisObservableList));
//        genesis.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, Genesis>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<Ege, Genesis> event) {
//                Ege ege = event.getRowValue();
//                Genesis genesis = genesisService.findByGiId(event.getNewValue().getGiId());
//                ege.setGenesis(genesis);
//                updateObjectInListForView(ege);
//            }
//        });

//        TableColumn<SoilKindDto, SoilKindDto> editColumn = column("Edit", ReadOnlyObjectWrapper<Person>::new, 60);
//        TableColumn<SoilKindDto, SoilKindDto> editColumn = column("Edit", ReadOnlyObjectWrapper<Person>::new, 60);
//
//        TableColumn<SoilKindDto, SoilKindDto> col = new TableColumn<>(title);


        soilKindChooseButton.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<SoilKindDto>(cellData.getValue()));

//        table.getColumns().add(editColumn);

        soilKindChooseButton.setCellFactory(col -> {
            var editButton = new Button("Выбрать");
            var cell = new TableCell<SoilKindDto, SoilKindDto>() {
                @Override
                public void updateItem(SoilKindDto soilKindDto, boolean empty) {
                    super.updateItem(soilKindDto, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
                }
            };

            editButton.setOnAction(e -> {
                int index = cell.getTableRow().getIndex();

                var soilKindDto = soilKindTableView.getItems().get(index);

                var soilKind = soilKindService.getById(soilKindDto.getId());

                descriptionKgaDto.setSoilKind(soilKind);
                setDescriptionKgaTextArea();
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
        egeServise.updateEge(ege, descriptionKgaDto);
    }

    @FXML
    public void onCloseButtonClicked(ActionEvent event) {
        onSaveButtonClicked(event);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCloseWithoutSavingButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
