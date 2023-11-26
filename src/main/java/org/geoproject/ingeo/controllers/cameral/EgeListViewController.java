package com.geoproject.igeo.controllers.cameral;

import com.geoproject.igeo.controllers.AbstractMainViewController;
import com.geoproject.igeo.dto.mainViewsDto.EgeDTO;
import com.geoproject.igeo.enums.ViewsEnum;
import com.geoproject.igeo.models.Ege;
import com.geoproject.igeo.models.Genesis;
import com.geoproject.igeo.services.classificators.impl.GenesisServiceImpl;
import com.geoproject.igeo.services.mainViews.MainViewService;
import com.geoproject.igeo.utils.CurrentState;
import com.geoproject.igeo.utils.JavaFXCommonMethods;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.geoproject.igeo.enums.StageTitleEnum.DILUTION_FACTORS;

@Log4j2
@Component
public class EgeListViewController extends AbstractMainViewController<Ege, EgeDTO> implements Initializable {

    private final GenesisServiceImpl genesisService;
    Stage stage;

    @FXML
    private TableColumn<Ege, String> number;
    @FXML
    private TableColumn<Ege, Integer> codeNumber;
    @FXML
    private TableColumn<Ege, String> shortName;
    @FXML
    private TableColumn<Ege, Genesis> genesis;
    @FXML
    private TableColumn<Ege, String> soilKindEnum;
    @FXML
    private TableColumn<Ege, String> descriptionCredoFormular;
    @FXML
    private TableColumn<Ege, String> descriptionKga;
    //    @FXML
//    private TableColumn<Ege, String> descriptionForCameralTask;
    @FXML
    private TableColumn<Ege, String> genesisDescription;
    @FXML
    private TableColumn<Ege, String> descriptionForOrganisation;
    @FXML
    private TableColumn<Ege, String> hatchingNameCredoAutocad;
    @FXML
    private TableColumn<Ege, String> consistency;
    @FXML
    private TableColumn<Ege, String> color;


    public EgeListViewController(ConfigurableApplicationContext applicationContext,
                                 MainViewService<Ege, EgeDTO> service,
                                 CurrentState currentState,
                                 GenesisServiceImpl genesisService) {
        super(currentState, applicationContext, service);
        this.genesisService = genesisService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Ege Table init...");
        init();
        tableView.setEditable(true);
        showAllObjectsInCurrentProject();
    }

    public void showAllObjectsInCurrentProject() {

        codeNumber.setCellValueFactory(new PropertyValueFactory<Ege, Integer>("codeNumber"));

        number.setCellValueFactory(new PropertyValueFactory<Ege, String>("number"));
        number.setCellFactory(TextFieldTableCell.forTableColumn());
        number.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, String> event) {
                Ege ege = event.getRowValue();
                ege.setNumber(event.getNewValue());
                updateObjectInListForView(ege);
            }
        });

        shortName.setCellValueFactory(new PropertyValueFactory<Ege, String>("shortName"));
        shortName.setCellFactory(TextFieldTableCell.forTableColumn());
        shortName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, String> event) {
                Ege ege = event.getRowValue();
                ege.setShortName(event.getNewValue());
                updateObjectInListForView(ege);
            }
        });

        ObservableList<Genesis> genesisObservableList = FXCollections.observableArrayList();
        genesisObservableList.addAll(genesisService.findAll());
        genesis.setCellValueFactory(new PropertyValueFactory<Ege, Genesis>("genesis"));
        genesis.setCellFactory(ChoiceBoxTableCell.forTableColumn(genesisObservableList));
        genesis.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, Genesis>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, Genesis> event) {
                Ege ege = event.getRowValue();
                Genesis genesis = genesisService.findByGiId(event.getNewValue().getGiId());
                ege.setGenesis(genesis);
                updateObjectInListForView(ege);
            }
        });


        ObservableList<String> soilObservableList = FXCollections.observableArrayList();
        //todo для чего пустое поле?
        //todo посмотреть урок про Enum в гибере
        soilObservableList.addAll("Почва", "Пески", "Глинистые", "Пустое поле");
        soilKindEnum.setCellValueFactory(new PropertyValueFactory<Ege, String>("soilKind"));
        soilKindEnum.setCellFactory(ChoiceBoxTableCell.forTableColumn(soilObservableList));
//        soilKindEnum.setCellFactory(new TableCell<Ege, String>());
        soilKindEnum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, String> event) {
                Ege ege = event.getRowValue();
                ege.setSoilKindEnum(event.getNewValue());
                updateObjectInListForView(ege);
            }
        });

        descriptionCredoFormular.setCellValueFactory(new PropertyValueFactory<Ege, String>("descriptionCredoFormular"));
        descriptionCredoFormular.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCredoFormular.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, String> event) {
                Ege ege = event.getRowValue();
                ege.setDescriptionCredoFormular(event.getNewValue());
                updateObjectInListForView(ege);
            }
        });

        descriptionKga.setCellValueFactory(new PropertyValueFactory<Ege, String>("descriptionKga"));
        descriptionKga.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionKga.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, String> event) {
                Ege ege = event.getRowValue();
                ege.setDescriptionKga(event.getNewValue());
                updateObjectInListForView(ege);
            }
        });

        genesisDescription.setCellValueFactory(ege -> new SimpleObjectProperty<>(ege.getValue().getGenesis().getName()));

        descriptionForOrganisation.setCellValueFactory(new PropertyValueFactory<Ege, String>("descriptionForOrganisation"));
        descriptionForOrganisation.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionForOrganisation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, String> event) {
                Ege ege = event.getRowValue();
                ege.setDescriptionForOrganisation(event.getNewValue());
                updateObjectInListForView(ege);
            }
        });

        //todo временно ввод вручную. Переделать на выпадающий список (сущность SoilType)
        //        hatching_name_credo_autocad IS 'ACAD,
        //        private TableColumn<Ege, String> hatchingNameCredoAutocad;
        hatchingNameCredoAutocad.setCellValueFactory(new PropertyValueFactory<Ege, String>("hatchingNameCredoAutocad"));
        hatchingNameCredoAutocad.setCellFactory(TextFieldTableCell.forTableColumn());
        hatchingNameCredoAutocad.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, String> event) {
                Ege ege = event.getRowValue();
                ege.setHatchingNameCredoAutocad(event.getNewValue());
                updateObjectInListForView(ege);
            }
        });

        //todo временно ввод вручную. Переделать на выпадающий список. Сделать классификатор, сущность с репозиторием и сервисом.
        //Консистенция, выбирается из классификатора
        //        @Column(name = "consistency")
        //        private TableColumn<Ege, String> consistency;

        consistency.setCellValueFactory(new PropertyValueFactory<Ege, String>("consistency"));
        consistency.setCellFactory(TextFieldTableCell.forTableColumn());
        consistency.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, String> event) {
                Ege ege = event.getRowValue();
                ege.setConsistency(event.getNewValue());
                updateObjectInListForView(ege);
            }
        });

        //todo временно ввод вручную. Переделать на выпадающий список. Сделать классификатор, сущность с репозиторием и сервисом.
        //private TableColumn<Ege, String> color;

        color.setCellValueFactory(new PropertyValueFactory<Ege, String>("color"));
        color.setCellFactory(TextFieldTableCell.forTableColumn());
        color.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ege, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ege, String> event) {
                Ege ege = event.getRowValue();
                ege.setColor(event.getNewValue());
                updateObjectInListForView(ege);
            }
        });

        tableView.getItems().setAll(objectListForView);
    }

    //кнопки

    @Override
    @FXML
    public void onSaveAllObjectsButtonClicked() {
        super.onSaveAllObjectsButtonClicked();
        System.out.println("onSaveAllEgesButtonClicked clicked...");
    }

    @Override
    public List<Ege> setObjectListForObjectListForView() {
        return service.getByProject(currentState.getCurrentProject());
    }

    @Override
    @FXML
    public void onAddNewRowButtonClicked() {
        Ege ege = new Ege();
        List<Integer> numberList = objectListForView.stream().map(e -> e.getCodeNumber()).toList();
        int maxCodeNumber = numberList.stream().reduce(Integer::max).get();
        ege.setCodeNumber(maxCodeNumber + 1);
        ege.setGenesis(new Genesis());
        addNewObjectAtListForView(ege);
    }

    @FXML
    public void onSoilKindChoiceViewButtonClicked(ActionEvent event) throws IOException {
        log.info("onSoilKindChoiceViewButtonClicked clicked...");
//        JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.SOIL_KIND_CHOICE_VIEW.getPath(),
//                applicationContext, ViewsEnum.SOIL_KIND_CHOICE_VIEW.getTitle());

        JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.SOIL_KIND_CHOICE_VIEW.getPath(),
                applicationContext, stage, ViewsEnum.SOIL_KIND_CHOICE_VIEW.getTitle());
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
