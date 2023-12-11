package org.geoproject.ingeo.controllers.cameral;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import org.geoproject.ingeo.controllers.NewAbstractMainViewController;
import org.geoproject.ingeo.controllers.functionalInterfaces.Refreshable;
import org.geoproject.ingeo.customFXnodes.CustomGenesisDtoComboBoxTableCell;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.dtoenums.EgeDTOFieldsEnum;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.services.classificators.GenesisService;
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
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.JavaFXConstants.CONSISTENCY_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.DESCRIPTION_KGA_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.GENESIS_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.GENESIS_DESCRIPTION_COLUMN;
import static org.geoproject.ingeo.constants.JavaFXConstants.HATCHING_COLUMN;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

@Log4j2
@Component
public class EgeListViewController extends NewAbstractMainViewController<Ege, EgeDto> implements Initializable {

    private final GenesisService genesisService;
    Stage stage;

    //    ObservableList<GenesisDto> genesisDtoObservableList = FXCollections.observableArrayList();
    ObservableList<GenesisDto> genesisDtoObservableList = FXCollections.observableArrayList();

    @FXML
    TableColumn<EgeDto, ComboBox<GenesisDto>> genesis = new TableColumn();

    @FXML
    private Button soilKindChoiceViewButton;


    public EgeListViewController(ConfigurableApplicationContext applicationContext,
                                 MainViewService<Ege, EgeDto> service,
                                 CurrentState currentState,
                                 GenesisServiceImpl genesisService) {
        super(currentState, applicationContext, service);
        this.genesisService = genesisService;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Открыто окно список ИГЭ");

        init();

        if (tableView.getItems().isEmpty()) {
            soilKindChoiceViewButton.setDisable(Boolean.TRUE);
        }
    }

    @Override
    public void sortObservableDtoList(List<EgeDto> egeDtoList) {
        egeDtoList.sort(Comparator.comparing(EgeDto::getNumber));
    }

    @Override
    public void setCellsFormat() {
                tableView.setEditable(true);

        genesis.setOnEditCommit(event -> System.out.println("EVENT!!!"));
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
            if (columnName.equals(GENESIS_COLUMN)) {
                setGenesisChoiceBox((TableColumn<EgeDto, GenesisDto>) column);
            } else {
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        columnsMap.forEach((columnName, column) ->
                column.setOnEditCommit(event -> {
                            event.getRowValue()
                                    .setFieldValue(EgeDTOFieldsEnum.getEnumByName(columnName), event.getNewValue());

                            if (columnName.equals("genesis")) {

                                System.out.println("******************* - GENESIS_COLUMN");
                                System.out.println(event);
                                System.out.println(event.getRowValue());
                                System.out.println(event.getSource());

                            }
                        }
                ));


        ((TableColumn<EgeDto, String>) columnsMap.get(GENESIS_DESCRIPTION_COLUMN)).setCellValueFactory(data -> {
            String genesisDescription = data.getValue().getGenesisDto().getGenesisDescription();

            return new SimpleStringProperty(genesisDescription);
        });

        tableView.setItems(observableDtoList);
    }

    public void setGenesisChoiceBox(TableColumn<EgeDto, GenesisDto> column) {

        System.out.println("COLUMN:");
        System.out.println(column);
        System.out.println(column.getTableView().getSelectionModel().getSelectedIndex());
        System.out.println(column.getTableView().getSelectionModel().getFocusedIndex());
        System.out.println(column.tableViewProperty());
        System.out.println(column.tableViewProperty().get().getFocusModel());

        System.out.println("==============");

        var genesisDtoList = genesisService.getEgeDtos();
        genesisDtoObservableList.clear();
        genesisDtoObservableList.addAll(genesisDtoList);

        Callback<TableColumn<EgeDto, ComboBox<GenesisDto>>, TableCell<EgeDto, ComboBox<GenesisDto>>> cellFactory
                = genesisColumn -> new CustomGenesisDtoComboBoxTableCell(genesisDtoObservableList, genesisColumn, observableDtoList, tableView);

        genesis.setCellFactory(cellFactory);
    }


    //кнопки

    @Override
    @FXML
    public void onSaveAllObjectsButtonClicked() {
        super.onSaveAllObjectsButtonClicked();

        if (!tableView.getItems().isEmpty() && soilKindChoiceViewButton.isDisabled()) {
            soilKindChoiceViewButton.setDisable(Boolean.FALSE);
        }
    }

//    @Override
//    public List<Ege> setObjectListForObjectListForView() {
//        return service.getByProject(currentState.getCurrentProject());
//    }

//    @Override
//    @FXML
//    public void onAddNewRowButtonClicked() {
//        var ege = new Ege();
//        var numberList = objectListForView.stream().map(Ege::getCodeNumber).toList();
//        var optionalMaxCodeNumber = numberList.stream().reduce(Integer::max);
//        optionalMaxCodeNumber.ifPresent(maxCodeNumber -> ege.setCodeNumber(maxCodeNumber + SINGLE_CODE_NUMBER));
//
//        if (optionalMaxCodeNumber.isEmpty()) {
//            ege.setCodeNumber(SINGLE_CODE_NUMBER);
//        }
//
//        ege.setGenesis(new Genesis());
//        addNewObjectAtListForView(ege);
//    }

    @FXML
    public void onSoilKindChoiceViewButtonClicked(ActionEvent event) throws IOException {
        log.info("onSoilKindChoiceViewButtonClicked clicked...");

        var childController = (SoilKindChoiceViewController) applicationContext.getBean("soilKindChoiceViewController");

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex < ZERO_INDEX) {
            selectedIndex = ZERO_INDEX;
        }

        if (!tableView.getItems().isEmpty()) {
            var egeDto = tableView.getItems().get(selectedIndex);

            childController.passEge(egeDto);

            Refreshable refreshTable = () -> tableView.refresh();

            childController.passTableRefreshLambda(refreshTable);

            JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.SOIL_KIND_CHOICE_VIEW.getPath(),
                    applicationContext, stage, ViewsEnum.SOIL_KIND_CHOICE_VIEW.getTitle());
        }
    }

    //todo придумать уведомление о необходимости нажать на сохранить для удаления из базы
    @Override
    @FXML
    public void onDeleteRowButtonClicked() {
        //todo реализовать выделение строки по умолчанию - следующая после удаленной
        super.onDeleteRowButtonClicked();
        System.out.println("onDeleteRowButtonClicked clicked...");
    }


    //todo перемещение строчек в таблице, сортировка по порядковому номеру либо по названию ИГЭ

    @Override
    @FXML
    public void onAllProjectsButtonClicked(ActionEvent event) throws IOException {
        super.onAllProjectsButtonClicked(event);
        System.out.println("Change scene to all projects from cameral module...");
    }

    @Override
    @FXML
    public void onFieldModuleButtonClicked(ActionEvent event) throws IOException {
        super.onFieldModuleButtonClicked(event);
        System.out.println("Change scene to field module from cameral module...");

    }

    @Override
    @FXML
    public void onLaborModuleButtonClicked(ActionEvent event) throws IOException {
        super.onLaborModuleButtonClicked(event);
        System.out.println("Change scene to labor module from cameral module...");
    }

    @Override
    @FXML
    public void onCameralModuleButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Trying change scene to cameral module from cameral module... \n" +
                "Already in cameral module");
    }

//    @FXML
//    public void onLocalSoilListButtonClicked() {
//        System.out.println("onLocalSoilListButtonClicked clicked...");
//
//    }
//
//    @FXML
//    public void onLitologyAndConsistencyButtonClicked(ActionEvent event) throws IOException {
//        var path = ViewsEnum.BOREHOLE_LAYERS_DESCRIPTION_MAIN_VIEW.getPath();
//        var title = ViewsEnum.BOREHOLE_LAYERS_DESCRIPTION_MAIN_VIEW.getTitle();
//        JavaFXCommonMethods.changeScene(event, path, applicationContext, title);
//    }

    @FXML
    public void onCameralModuleMainViewButtonClicked(ActionEvent event) throws IOException {
        log.info("onCameralModuleMainViewButtonClicked clicked...");
    }

    @FXML
    public void onPumpButtonClicked(ActionEvent event) throws IOException {
        log.info("onPumpButtonClicked clicked...");
    }

    @FXML
    public void onStatisticalProcesssingButtonClicked(ActionEvent event) throws IOException {
        log.info("onStatisticalProcesssingButtonClicked clicked...");
    }

    @FXML
    public void onEgeListButtonClicked(ActionEvent event) throws IOException {
        System.out.println("onEgeListButtonClicked clicked...");
        JavaFXCommonMethods.changeScene(event, ViewsEnum.EGE_LIST_VIEW.getPath(),
                applicationContext, ViewsEnum.EGE_LIST_VIEW.getTitle());
    }

    @FXML
    public void onBoreholeLayerButtonClicked(ActionEvent event) throws IOException {
        var path = ViewsEnum.BOREHOLE_LAYERS_DESCRIPTION_MAIN_VIEW.getPath();
        var title = ViewsEnum.BOREHOLE_LAYERS_DESCRIPTION_MAIN_VIEW.getTitle();
        JavaFXCommonMethods.changeScene(event, path, applicationContext, title);
    }
}
