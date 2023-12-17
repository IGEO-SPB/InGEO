package org.geoproject.ingeo.controllers.cameral;

import org.geoproject.ingeo.controllers.AbstractMainViewController;
import org.geoproject.ingeo.dto.mainViewsDtos.BoreholeLayerDTO;
import org.geoproject.ingeo.models.BoreholeLayer;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.cameral.EgeServise;
import org.geoproject.ingeo.services.MainViewService;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.GeoCommonMethods;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.ServiceConstants.NOT_EDITABLE_FROM_EGE_LIST;

//Колонка (литология + консистенция)
@Component
public class BoreholeLayerMainViewController extends AbstractMainViewController<BoreholeLayer, BoreholeLayerDTO> implements Initializable {
    private final SurveyPointsService surveyPointsService;
    private final EgeServise egeServise;

    private final CurrentState currentState;

    @FXML
    ChoiceBox<String> boreholeChoiceBox;

    //порядковый номер в рамках данной колонки
    @FXML
    private TableColumn<BoreholeLayer, Integer> number;
    @FXML
    private TableColumn<BoreholeLayer, String> egeNumber;
    //todo из другой таблицы (найти, откуда)
    @FXML
    private TableColumn<BoreholeLayer, String> soilType;
    @FXML
    private TableColumn<BoreholeLayer, Float> layerBottomDepth;
    @FXML
    private TableColumn<BoreholeLayer, Float> absoluteTopMark;
    @FXML
    private TableColumn<BoreholeLayer, Float> layerPower;
    @FXML
    private TableColumn<BoreholeLayer, Float> layerTopDepth;
    @FXML
    private TableColumn<BoreholeLayer, Float> firstLayerTop;
    @FXML
    private TableColumn<BoreholeLayer, Float> firstLayerBottom;
    @FXML
    private TableColumn<BoreholeLayer, String> firstLayerType;
    @FXML
    private TableColumn<BoreholeLayer, Float> secondLayerBottom;
    @FXML
    private TableColumn<BoreholeLayer, String> secondLayerType;
    @FXML
    private TableColumn<BoreholeLayer, String> hatchingNameCredoAutocad;
    @FXML
    private TableColumn<BoreholeLayer, String> color;
    @FXML
    private TableColumn<BoreholeLayer, Float> water;
    @FXML
    private TableColumn<BoreholeLayer, Boolean> isEditableFromEgeList ;

    @FXML
    private TableColumn<BoreholeLayer, String> descriptionCredoFormular;
    @FXML
    private TableColumn<BoreholeLayer, String> descriptionKga;

    @Autowired
    public BoreholeLayerMainViewController(ConfigurableApplicationContext applicationContext,
                                           MainViewService<BoreholeLayer, BoreholeLayerDTO> service,
                                           CurrentState currentState,
                                           SurveyPointsService surveyPointsService, EgeServise egeServise) {
        super(currentState, applicationContext, service);
        this.surveyPointsService = surveyPointsService;
        this.egeServise = egeServise;
        this.currentState = currentState;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Borehole Layers Table init...");
        init();
        tableView.setEditable(true);
        setBoreholeChoiceBox();
        showAllObjectsInCurrentProject();
    }

    @Override
    @FXML
    public void showAllObjectsInCurrentProject() {
        if (objectListForView.isEmpty()) {
            return;
        }

        number.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, Integer>("number"));

        ObservableList<String> egeNumberObservableList = FXCollections.observableArrayList();
        Project currentProject = currentState.getCurrentProject();

        Hibernate.initialize(currentProject.getEgeList());
        List<Ege> egesInCurrentProject = currentProject.getEgeList();

        List<String> egesNumberList = egesInCurrentProject.stream().map(e -> e.getEgeNumber()).toList();

        egeNumberObservableList.addAll(egesNumberList);
        egeNumber.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getEge().getEgeNumber()));
        egeNumber.setCellFactory(ChoiceBoxTableCell.forTableColumn(egeNumberObservableList));
        egeNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<BoreholeLayer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<BoreholeLayer, String> event) {
                BoreholeLayer boreholeLayer = event.getRowValue();
                Ege ege = egeServise.getByNumberAndProject(event.getNewValue(), currentState.getCurrentProject());
                boreholeLayer.setEge(ege);
                updateObjectInListForView(boreholeLayer);
            }
        });

        //todo временно ввод вручную. Должен автоматически подгружаться
        //todo переделать - добавить поле в сущность и таблицу или как-то еще, может, просто списком
        //заполняется в локальном списке грунтов при выборе вида и разновидности грунта из классификаторов
        //либо из микроклассификатора из трех типов и пустого
        //либо вручную
        //а здесь автоматически подтягивается вместе с ИГЭ
        soilType.setCellValueFactory(boreholeLayer -> new SimpleObjectProperty<>(boreholeLayer.getValue().getEge().getShortName()));

        //todo проверка на глубину слоя. Низ слоя не должен быть больше, чем общая глубина.
        // Создать Exception, alert window, не сохранять новые значения, но поля не очищать, добавить в логирование
        layerBottomDepth.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, Float>("layerBottomDepth"));
        layerBottomDepth.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        layerBottomDepth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<BoreholeLayer, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<BoreholeLayer, Float> event) {
                //todo вынести в отдельный метод. Вероятно потребуется рефлексия
                BoreholeLayer boreholeLayer = event.getRowValue();
                boreholeLayer.setLayerBottomDepth(event.getNewValue());
                GeoCommonMethods.setLayerDepths(boreholeLayer, event.getNewValue(), objectListForView);
                updateObjectInListForView(boreholeLayer);
            }
        });

        //следующие 4 поля рассчитываются автоматически (метод setLayerDepths()):
        absoluteTopMark.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, Float>("absoluteTopMark"));
        layerPower.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, Float>("layerPower"));
        layerTopDepth.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, Float>("layerTopDepth"));
        firstLayerTop.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, Float>("firstLayerTop"));

        firstLayerBottom.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, Float>("firstLayerBottom"));
        firstLayerBottom.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        firstLayerBottom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<BoreholeLayer, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<BoreholeLayer, Float> event) {
                BoreholeLayer boreholeLayer = event.getRowValue();
                boreholeLayer.setFirstLayerBottom(event.getNewValue());
                updateObjectInListForView(boreholeLayer);
            }
        });

        //todo временно ввод вручную. Переделать на выпадающий список (вероятно сущность SoilType ???)
        firstLayerType.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, String>("firstLayerType"));

        //следующее поле вероятно рассчитывается автоматически:
        secondLayerBottom.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, Float>("secondLayerBottom"));

        //todo временно ввод вручную. Переделать на выпадающий список (вероятно сущность SoilType ???)
        secondLayerType.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, String>("secondLayerType"));
        //todo временно ввод вручную. Переделать на выпадающий список
        hatchingNameCredoAutocad.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, String>("hatchingNameCredoAutocad"));
        //todo временно ввод вручную. Переделать на выпадающий список (сущность SoilType ???)
        color.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, String>("color"));

        water.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, Float>("water"));
        water.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        water.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<BoreholeLayer, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<BoreholeLayer, Float> event) {
                BoreholeLayer boreholeLayer = event.getRowValue();
                boreholeLayer.setWater(event.getNewValue());
                updateObjectInListForView(boreholeLayer);
            }
        });

        //Описание для credo и формуляра. Вводится вручную. В поле может копировать информация из descriptionKga
        descriptionCredoFormular.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, String>("descriptionCredoFormular"));
        descriptionCredoFormular.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCredoFormular.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<BoreholeLayer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<BoreholeLayer, String> event) {
                BoreholeLayer boreholeLayer = event.getRowValue();
                boreholeLayer.setDescriptionCredoFormular(event.getNewValue());
                updateObjectInListForView(boreholeLayer);
            }
        });

        //Описание по классификатору КГА. Вводится набором грунтов из классификатора
        //fixme!!! здесь пробую реализовать набор грунтов из классификатора
        descriptionKga.setCellValueFactory(new PropertyValueFactory<BoreholeLayer, String>("descriptionKga"));

        isEditableFromEgeList.setCellFactory(column -> new CheckBoxTableCell<>());
        isEditableFromEgeList.setCellValueFactory(cellData -> {
                BoreholeLayer cellValue = cellData.getValue();
                BooleanProperty property = new ReadOnlyBooleanWrapper(cellValue.getIsEditableFromEgeListBasic());
            System.out.println("propertyBefore:");
            System.out.println(property);
                property.addListener((observable, oldValue, newValue) -> {
                    cellValue.setIsEditableFromEgeList(new SimpleBooleanProperty(newValue));
                    cellValue.setIsEditableFromEgeListBasic(newValue);
                    updateObjectInListForView(cellValue);
                });

            return property;
            });

        tableView.getItems().setAll(objectListForView);
    }

    //кнопки

    @Override
    @FXML
    public void onSaveAllObjectsButtonClicked() {
        super.onSaveAllObjectsButtonClicked();
        System.out.println("onSaveAllBoreholeLayersButtonClicked clicked...");
    }

    @Override
    public List<BoreholeLayer> setObjectListForObjectListForView() {
        return service.getBySurveyPoint(currentState.getSurveyPoint(), Sort.by("laborNumber"));
    }

    @Override
    @FXML
    public void onAddNewRowButtonClicked() {
        BoreholeLayer boreholeLayer = new BoreholeLayer();
        if (!objectListForView.isEmpty()) {
            List<Integer> numberList = objectListForView.stream().map(e -> e.getNumber()).toList();
            int maxCodeNumber = numberList.stream().reduce(Integer::max).get();
            boreholeLayer.setNumber(maxCodeNumber + 1);
        } else {
            boreholeLayer.setNumber(1);
        }
        boreholeLayer.setSurveyPoint(currentState.getSurveyPoint());
        //todo переделать на первый ИГЭ из текущего проекта.
        // Это возможно приведет к изменению списков - где-то нужно автоматически сортировать
        boreholeLayer.setEge(egeServise.getById(1L));
        boreholeLayer.setIsEditableFromEgeList(new SimpleBooleanProperty(NOT_EDITABLE_FROM_EGE_LIST));
        boreholeLayer.setIsEditableFromEgeListBasic(NOT_EDITABLE_FROM_EGE_LIST);

        addNewObjectAtListForView(boreholeLayer);
    }

    //todo придумать уведомление о необходимости нажать на сохранить для удаления из базы
    @Override
    @FXML
    public void onDeleteRowButtonClicked() {
        //todo реализовать выделение строки по умолчанию - следующая после удаленной
        super.onDeleteRowButtonClicked();
        System.out.println("onDeleteRowButtonClicked clicked...");
    }

    @FXML
    public void onChoiceBoxClicked() {
        boreholeChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        boreholeChoiceBox.setOnAction(e -> tempSetListForTableView(boreholeChoiceBox)));
    }

    @Override
    @FXML
    public void onAllProjectsButtonClicked(ActionEvent event) throws IOException {
        super.onAllProjectsButtonClicked(event);
        System.out.println("Change scene to all projects from borehole layer module...");
    }

    @Override
    @FXML
    public void onFieldModuleButtonClicked(ActionEvent event) throws IOException {
        super.onFieldModuleButtonClicked(event);
        System.out.println("Change scene to field module from borehole layer module...");
    }

    @Override
    @FXML
    public void onLaborModuleButtonClicked(ActionEvent event) throws IOException {
        super.onLaborModuleButtonClicked(event);
        System.out.println("Change scene to labor module from borehole layer module...");
    }

    @Override
    @FXML
    public void onCameralModuleButtonClicked(ActionEvent event) throws IOException {
        super.onCameralModuleButtonClicked(event);
        System.out.println("Change scene to cameral module from borehole layer module...");
    }

    //утилитные методы. todo Имеет смысл вынести в отдельный утилитный класс

    //todo добавить поля общая глубина скважины и абсолютная отметка (рядом с выпадающим списком скважин). Должно подтягиваться из Survey Point

    private void setBoreholeChoiceBox() {
        boreholeChoiceBox.setValue("№ выработки");
        List<SurveyPoint> surveyPointList = surveyPointsService.getAll();
        List<String> nameNumberList = surveyPointList.stream().map(SurveyPoint::getNameNumber).toList();
        ObservableList<String> boreholesList = FXCollections.observableArrayList(nameNumberList);
        boreholeChoiceBox.getItems().addAll(boreholesList);
    }

    private void tempSetListForTableView(ChoiceBox<String> boreholeChoiceBox) {
        String surveyPointNumber = boreholeChoiceBox.getValue();
        SurveyPoint currentSurveyPoint = surveyPointsService.findByPointNumberAndProject(surveyPointNumber, currentState.getCurrentProject());
        currentState.setSurveyPoint(currentSurveyPoint);
        JavaFXCommonMethods.setFooterElements(currentState, projectNameInFooter, projectCipherInFooter);
        List<BoreholeLayer> boreholeLayerList = service.getByProject(currentState.getCurrentProject());
        setObjectListForView(boreholeLayerList);
        showAllObjectsInCurrentProject();
    }
}
