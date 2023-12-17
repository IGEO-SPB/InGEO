package org.geoproject.ingeo.controllers.cameral;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.apache.commons.lang.StringUtils;
import org.geoproject.ingeo.controllers.NewAbstractStaticTableController;
import org.geoproject.ingeo.controllers.functionalInterfaces.Description;
import org.geoproject.ingeo.controllers.functionalInterfaces.GetComboBoxValue;
import org.geoproject.ingeo.controllers.functionalInterfaces.Refreshable;
import org.geoproject.ingeo.controllers.functionalInterfaces.Settable;
import org.geoproject.ingeo.customFXnodes.CustomConsistencyDtoComboBoxTableCell;
import org.geoproject.ingeo.customFXnodes.CustomGenesisDtoComboBoxTableCell;
import org.geoproject.ingeo.customFXnodes.CustomSearchableComboBoxTableCell;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.dtoenums.EgeDTOFieldsEnum;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.services.classificators.ConsistencyService;
import org.geoproject.ingeo.services.classificators.GenesisService;
import org.geoproject.ingeo.services.classificators.HatchingService;
import org.geoproject.ingeo.services.classificators.impl.GenesisServiceImpl;
import org.geoproject.ingeo.services.MainViewService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
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

import static org.geoproject.ingeo.constants.JavaFXConstants.CONSISTENCY_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.DESCRIPTION_KGA_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.GENESIS_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.GENESIS_DESCRIPTION_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.HATCHING_COLUMN;
import static org.geoproject.ingeo.constants.ServiceConstants.COMBOBOX_LAST_COLUMN_PIXEL_GAP;
import static org.geoproject.ingeo.constants.ServiceConstants.HAVE_TO_SAVE_CHOSEN_EGE_SERVICE_MESSAGE;
import static org.geoproject.ingeo.constants.ServiceConstants.SINGLE_CODE_NUMBER;
import static org.geoproject.ingeo.constants.ServiceConstants.SINGLE_INDEX_POINT;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

@Log4j2
@Component
public class EgeListViewController extends NewAbstractStaticTableController<Ege, EgeDto> implements Initializable {

    private final GenesisService genesisService;
    private final HatchingService hatchingService;
    private final ConsistencyService consistencyService;

    Stage stage;

    ObservableList<GenesisDto> genesisDtoObservableList = FXCollections.observableArrayList();
    ObservableList<ConsistencyDto> consistencyDtoObservableList = FXCollections.observableArrayList();
    ObservableList<HatchingDto> hatchingDtoObservableList = FXCollections.observableArrayList();

    @FXML
    TableColumn<EgeDto, ComboBox<GenesisDto>> genesis = new TableColumn<>();
    @FXML
    TableColumn<EgeDto, ComboBox<ConsistencyDto>> consistency = new TableColumn<>();
    @FXML
    TableColumn<EgeDto, ComboBox<HatchingDto>> hatching = new TableColumn<>();

    @FXML
    ChoiceBox<EgeDto> egeNumberChoiceBox;

    @FXML
    private Button soilKindChoiceViewButton;


    public EgeListViewController(ConfigurableApplicationContext applicationContext,
                                 MainViewService<Ege, EgeDto> service,
                                 CurrentState currentState,
                                 GenesisServiceImpl genesisService, HatchingService hatchingService, ConsistencyService consistencyService) {
        super(currentState, applicationContext, service);
        this.genesisService = genesisService;
        this.hatchingService = hatchingService;
        this.consistencyService = consistencyService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Открыто окно список ИГЭ");

        init();

        if (tableView.getItems().isEmpty()) {
            soilKindChoiceViewButton.setDisable(Boolean.TRUE);
        }

        setEgeNumberChoiceBox();
    }

    @Override
    public void sortObservableDtoList(List<EgeDto> egeDtoList) {
        dtos.sort(Comparator.comparing(EgeDto::getCodeNumber));
        observableDtoList.sort(Comparator.comparing(EgeDto::getCodeNumber));
    }

    @Override
    public void setCellsFormat() {
        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(false);

        //список для колонок с нестандартным поведением - например, choicebox.
        // Обязательно заполнять!
        var excludeColumnNameList = new ArrayList<>(Arrays.asList(
                GENESIS_COLUMN,
                DESCRIPTION_KGA_COLUMN,
                GENESIS_DESCRIPTION_COLUMN,
                HATCHING_COLUMN,
                CONSISTENCY_COLUMN
        ));

        columnsMap.values().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView, observableDtoList,
                null, excludeColumnNameList));

        columnsMap.forEach((columnName, column) -> {
            switch (columnName) {
                case GENESIS_COLUMN -> setGenesisChoiceBox();
                case CONSISTENCY_COLUMN -> setConsistencyChoiceBox();
                case HATCHING_COLUMN -> setHatchingChoiceBox();
                default -> column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        columnsMap.forEach((columnName, column) ->
                column.setOnEditCommit(event ->
                            event.getRowValue()
                                    .setFieldValue(EgeDTOFieldsEnum.getEnumByName(columnName), event.getNewValue())
                ));

        ((TableColumn<EgeDto, String>) columnsMap.get(GENESIS_DESCRIPTION_COLUMN)).setCellValueFactory(data -> {
            var genesisDescription = StringUtils.EMPTY;

            if (Objects.nonNull(data.getValue().getGenesisDto())) {
                genesisDescription = data.getValue().getGenesisDto().getGenesisDescription();
            }

            return new SimpleStringProperty(genesisDescription);
        });

        tableView.setItems(observableDtoList);
    }

    public void setGenesisChoiceBox() {
        var genesisDtoList = genesisService.getEgeDtos();
        genesisDtoObservableList.clear();
        genesisDtoObservableList.addAll(genesisDtoList);

        Callback<TableColumn<EgeDto, ComboBox<GenesisDto>>, TableCell<EgeDto, ComboBox<GenesisDto>>> cellFactory
                = genesisColumn -> new CustomGenesisDtoComboBoxTableCell(genesisDtoObservableList, genesisColumn, observableDtoList, tableView);

        genesis.setCellFactory(cellFactory);
    }

    public void setConsistencyChoiceBox() {
        var consistencyDtoList = consistencyService.getConsistencyDtos();
        consistencyDtoObservableList.clear();
        consistencyDtoObservableList.addAll(consistencyDtoList);

        Callback<TableColumn<EgeDto, ComboBox<ConsistencyDto>>, TableCell<EgeDto, ComboBox<ConsistencyDto>>> cellFactory
                = consistencyColumn -> new CustomConsistencyDtoComboBoxTableCell(consistencyDtoObservableList, consistencyColumn,
                observableDtoList, tableView);

        consistency.setCellFactory(cellFactory);
    }

    public void setHatchingChoiceBox() {
        var hatchingDtoList = hatchingService.getHatchingDtos();
        hatchingDtoObservableList.clear();
        hatchingDtoObservableList.addAll(hatchingDtoList);

        Description<HatchingDto> description = HatchingDto::getShortName;
        GetComboBoxValue<EgeDto, HatchingDto> getComboBoxValue = EgeDto::getHatchingDto;
        Settable<EgeDto, HatchingDto> settable = EgeDto::setHatchingDto;

        Callback<TableColumn<EgeDto, ComboBox<HatchingDto>>, TableCell<EgeDto, ComboBox<HatchingDto>>> cellFactory =
                hatchingColumn -> new CustomSearchableComboBoxTableCell<>(
                        hatchingDtoObservableList,
                        hatchingColumn,
                        observableDtoList,
                        COMBOBOX_LAST_COLUMN_PIXEL_GAP,
                        description,
                        getComboBoxValue,
                        settable
                );

        hatching.setCellFactory(cellFactory);
    }

    public void setEgeNumberChoiceBox() {
        egeNumberChoiceBox.getItems().clear();
        egeNumberChoiceBox.getItems().addAll(observableDtoList);

        var converter = new StringConverter<EgeDto>() {
            @Override
            public String toString(EgeDto object) {

                if (Objects.nonNull(object)) {
                    if (Objects.nonNull(object.getEgeNumber())) {
                        return object.getEgeNumber();
                    }
                }
                return StringUtils.EMPTY;
            }

            @Override
            public EgeDto fromString(String string) {
                return null;
            }
        };

        egeNumberChoiceBox.setConverter(converter);
        egeNumberChoiceBox.setValue(observableDtoList.isEmpty() ? null : observableDtoList.get(ZERO_INDEX));
    }

    public void addNewRow() {
        log.info("Init addNewRow()");

        var egeDto = new EgeDto();
        egeDto.setCodeNumber(observableDtoList.size() + SINGLE_INDEX_POINT);

        observableDtoList.add(egeDto);

        dtos.add(egeDto);

        tableView.refresh();
    }

    public void addNewRow(int selectedIndex) {
        log.info("Init addNewRow(int selectedIndex)");

        var dtoForIndex = tableView.getItems().get(selectedIndex);

        var egeDto = new EgeDto();

        egeDto.setCodeNumber(dtoForIndex.getCodeNumber());

        for (int i = dtoForIndex.getCodeNumber() - 1; i < observableDtoList.size(); i++) {
            observableDtoList.get(i).setCodeNumber(observableDtoList.get(i).getCodeNumber() + SINGLE_INDEX_POINT);
            dtos.get(i).setCodeNumber(dtos.get(i).getCodeNumber());
        }

        observableDtoList.add(egeDto);
        observableDtoList.sort(Comparator.comparing(EgeDto::getCodeNumber));

        dtos.add(egeDto);
        dtos.sort(Comparator.comparing(EgeDto::getCodeNumber));

        tableView.refresh();
    }

    //кнопки

    @FXML
    public void onAddNewRowBeforeSelectedRowButtonClicked() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        addNewRow(selectedIndex);
    }

    @FXML
    public void onAddNewRowAtTheEndButtonClicked() {
        addNewRow();
    }

    @Override
    @FXML
    public void onSaveAllObjectsButtonClicked() {
        super.onSaveAllObjectsButtonClicked();

        if (!tableView.getItems().isEmpty() && soilKindChoiceViewButton.isDisabled()) {
            soilKindChoiceViewButton.setDisable(Boolean.FALSE);
        }

        dtos.clear();
        observableDtoList.clear();

        setObjectListForView();
        setCellsFormat();
    }

    @FXML
    public void onSoilKindChoiceViewButtonClicked(ActionEvent event) throws IOException {
        log.info("Нажата кнопка перехода на экран формирования описания КГА...");

        var childController = (SoilKindChoiceViewController) applicationContext.getBean("soilKindChoiceViewController");

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < ZERO_INDEX) {
            selectedIndex = ZERO_INDEX;
        }

        if (!tableView.getItems().isEmpty()) {
            var egeDto = tableView.getItems().get(selectedIndex);

            if (Objects.isNull(egeDto.getId())) {
                JavaFXCommonMethods.initAlert(HAVE_TO_SAVE_CHOSEN_EGE_SERVICE_MESSAGE);
            } else {
                childController.passEge(egeDto);

                Refreshable refreshTable = () -> tableView.refresh();

                childController.passTableRefreshLambda(refreshTable);

                JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.SOIL_KIND_CHOICE_VIEW.getPath(),
                        applicationContext, stage, ViewsEnum.SOIL_KIND_CHOICE_VIEW.getTitle());
            }
        }
    }

    @FXML
    public void onDuplicateRowButtonClicked() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < ZERO_INDEX) {
            selectedIndex = ZERO_INDEX;
        }

        var egeDto = tableView.getItems().get(selectedIndex);

        var cloneDto = service.cloneDto(egeDto);

        var cloneDtoCodeNumber = selectedIndex + SINGLE_INDEX_POINT + SINGLE_CODE_NUMBER;

        cloneDto.setCodeNumber(cloneDtoCodeNumber);

        dtos.add(cloneDto);

        for (int i = selectedIndex + SINGLE_CODE_NUMBER; i < observableDtoList.size(); i++) {
            dtos.get(i).setCodeNumber(dtos.get(i).getCodeNumber() + SINGLE_CODE_NUMBER);
        }

        dtos.sort(Comparator.comparing(EgeDto::getCodeNumber));

        observableDtoList.clear();
        observableDtoList.addAll(dtos);

        tableView.refresh();
    }

    @FXML
    public void onAcceptEgeChoiceButtonClicked() {
        var selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= ZERO_INDEX) {
            var egeDto = tableView.getItems().get(selectedIndex);

            var codeNumber = egeNumberChoiceBox.getValue().getCodeNumber();

            var setIndex = codeNumber - SINGLE_INDEX_POINT;

            if (setIndex != selectedIndex) {

                if (selectedIndex > setIndex) {
                    dtos.remove(selectedIndex);
                    dtos.add(setIndex, egeDto);
                } else {
                    dtos.add(setIndex, egeDto);
                    dtos.remove(selectedIndex);
                }

            for (int i = 0; i < observableDtoList.size(); i++) {
                dtos.get(i).setCodeNumber(i + SINGLE_CODE_NUMBER);
            }

                observableDtoList.clear();
                observableDtoList.addAll(dtos);

                setEgeNumberChoiceBox();
                tableView.refresh();
            }
        }
    }

    @FXML
    public void onCopyDescriptionKgaToDescriptionCredoFormularButtonClicked() {
        Settable<EgeDto, String> settable = EgeDto::setDescriptionCredoFormular;

        copyDescriptionKga(settable);
    }

    @FXML
    public void onCopyDescriptionKgaToDescriptionForOrganisationButtonClicked() {
        Settable<EgeDto, String> settable = EgeDto::setDescriptionForOrganisation;

        copyDescriptionKga(settable);
    }

    private void copyDescriptionKga(Settable<EgeDto, String> settable) {
        var selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= ZERO_INDEX) {
            var egeDto = tableView.getItems().get(selectedIndex);

            settable.setValue(egeDto, egeDto.getDescriptionKga());

            tableView.refresh();
        }
    }

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
    public void onPumpButtonClicked(){
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
}
