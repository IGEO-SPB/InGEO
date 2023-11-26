package org.geoproject.ingeo.controllers;

import org.geoproject.ingeo.dto.SampleDto;
import org.geoproject.ingeo.enums.LaborMethodsEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.models.*;
import org.geoproject.ingeo.services.mainViews.MainViewService;
import org.geoproject.ingeo.services.mainViews.SurveyPointsService;
import org.geoproject.ingeo.services.mainViews.impl.ProjectsServiceImpl;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static org.geoproject.ingeo.constants.ServiceConstants.SINGLE_INDEX_POINT;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

@Component
public class LaborModuleMainViewController extends AbstractMainViewController<Sample, SampleDto> implements Initializable {
    private final SurveyPointsService surveyPointsService;
    private final ProjectsServiceImpl projectsService;

    @FXML
    private TableColumn<Sample, String> laborNumber;
    @FXML
    private TableColumn<Sample, SurveyPoint> surveyPoint;
    @FXML
    private TableColumn<Sample, Float> depthMin;
    @FXML
    private TableColumn<Sample, Float> depthMax;
    @FXML
    private TableColumn<Sample, Float> averageDensity;
    @FXML
    private TableColumn<Sample, Float> subterraneanWatersLevel;
    @FXML
    private TableColumn<Sample, String> structure;
    @FXML
    private TableColumn<Sample, String> soilTypeForPassport;


    @FXML
    ChoiceBox<String> methodNamesChoiceBox;

    @FXML
    protected ChoiceBox<String> surveyPointChoiceBox;
    protected List<String> surveyPointsNumbers;
    List<SurveyPoint> surveyPoints;

    private List<String> methodNames;
    ObservableList<String> methodNamesObservableList;

    String filePath;

    @Autowired
    public LaborModuleMainViewController(ConfigurableApplicationContext applicationContext,
                                         MainViewService<Sample, SampleDto> service,
                                         CurrentState currentState,
                                         SurveyPointsService surveyPointsService, ProjectsServiceImpl projectsService,
                                         List<String> methodNames) {
        super(currentState, applicationContext, service);
        this.surveyPointsService = surveyPointsService;
        this.projectsService = projectsService;
        this.methodNames = methodNames;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Survey Points Table init...");
        init();
        tableView.setEditable(true);
        methodNames = Arrays.stream(LaborMethodsEnum.values()).map(e -> e.getName()).collect(Collectors.toList());
        methodNamesObservableList = FXCollections.observableArrayList();
        methodNamesObservableList.addAll(methodNames);
        showAllObjectsInCurrentProject();
        setMethodNamesInChoiceBox();
        setSurveyPointChoiceBox();

        System.out.println("=======Check CurrentState in Labor Module=======");
        System.out.println(currentState);
        System.out.println(currentState.getSurveyPoint().getId());
        System.out.println(currentState.getSurveyPoint().getPointNumber());
        System.out.println("hashCode: " + currentState.hashCode());
        System.out.println("=================================================");
    }

    @Override
    public void showAllObjectsInCurrentProject() {

        laborNumber.setCellValueFactory(new PropertyValueFactory<Sample, String>("laborNumber"));
        laborNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        laborNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sample, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sample, String> event) {
                Sample sample = event.getRowValue();
                sample.setLaborNumber(event.getNewValue());
                updateObjectInListForView(sample);
            }
        });

        //todo переделать на динамически изменяемый проект
        Project currentProject = currentState.getCurrentProject();
        //todo лист из точек исследования текущего проекта
        ObservableList<SurveyPoint> surveyPointObservableList = FXCollections.observableArrayList(surveyPointsService.getByProject(currentProject));
        surveyPoint.setCellValueFactory(sample -> new SimpleObjectProperty<>(sample.getValue().getSurveyPoint()));
        surveyPoint.setCellFactory(ChoiceBoxTableCell.forTableColumn(surveyPointObservableList));
        surveyPoint.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sample, SurveyPoint>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sample, SurveyPoint> event) {
                Sample sample = event.getRowValue();
                sample.setSurveyPoint(event.getNewValue());
                updateObjectInListForView(sample);
            }
        });

        depthMin.setCellValueFactory(new PropertyValueFactory<Sample, Float>("depthMin"));
        depthMin.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        depthMin.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sample, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sample, Float> event) {
                Sample sample = event.getRowValue();
                sample.setDepthMin(event.getNewValue());
                updateObjectInListForView(sample);
            }
        });

        depthMax.setCellValueFactory(new PropertyValueFactory<Sample, Float>("depthMax"));
        depthMax.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        depthMax.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sample, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sample, Float> event) {
                Sample sample = event.getRowValue();
                sample.setDepthMax(event.getNewValue());
                updateObjectInListForView(sample);
            }
        });

        //todo должна подтягиваться автоматически из таблицы density
        averageDensity.setCellValueFactory(new PropertyValueFactory<Sample, Float>("averageDensity"));
        averageDensity.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        averageDensity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sample, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sample, Float> event) {
                Sample sample = event.getRowValue();
                sample.setAverageDensity(event.getNewValue());
                updateObjectInListForView(sample);
            }
        });

        subterraneanWatersLevel.setCellValueFactory(new PropertyValueFactory<Sample, Float>("subterraneanWatersLevel"));
        subterraneanWatersLevel.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        subterraneanWatersLevel.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sample, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sample, Float> event) {
                Sample sample = event.getRowValue();
                sample.setSubterraneanWatersLevel(event.getNewValue());
                updateObjectInListForView(sample);
            }
        });

        //todo вручную, переделать на enum или classif
        structure.setCellValueFactory(new PropertyValueFactory<Sample, String>("structure"));
        structure.setCellFactory(TextFieldTableCell.forTableColumn());
        structure.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sample, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sample, String> event) {
                Sample sample = event.getRowValue();
                sample.setStructure(event.getNewValue());
                updateObjectInListForView(sample);
            }
        });

        //todo откуда берется? вручную?
        soilTypeForPassport.setCellValueFactory(new PropertyValueFactory<Sample, String>("soilTypeForPassport"));
        soilTypeForPassport.setCellFactory(TextFieldTableCell.forTableColumn());
        soilTypeForPassport.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sample, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sample, String> event) {
                Sample sample = event.getRowValue();
                sample.setSoilTypeForPassport(event.getNewValue());
                updateObjectInListForView(sample);
            }
        });

        tableView.getItems().setAll(objectListForView);
    }

    @FXML
    public void setMethodNamesInChoiceBox() {
        methodNamesChoiceBox.getItems().addAll(methodNamesObservableList);
        methodNamesChoiceBox.setValue("Методы");
    }

    private ViewsEnum getViewFromMethodNamesChoiceBox() {
        if (methodNamesChoiceBox.getValue().equals("Плотность и влажность")) return ViewsEnum.DENSITY_WATER_CONTENT_METHOD_VIEW;
        if (methodNamesChoiceBox.getValue().equals("Ареометрия")) return ViewsEnum.AREOMETRIC_METHOD_VIEW;
        if (methodNamesChoiceBox.getValue().equals("Строительная сетка")) return ViewsEnum.CONSTRUCTION_MESH_METHOD_VIEW;
        if (methodNamesChoiceBox.getValue().equals("Строительная сетка (Ареометрия)")) return ViewsEnum.CONSTRUCTION_MESH_AREOMETRY_METHOD_VIEW;
        if (methodNamesChoiceBox.getValue().equals("Химия воды")) return ViewsEnum.WATER_CHEMISTRY_MAIN_VIEW;
        if (methodNamesChoiceBox.getValue().equals("Водная вытяжка")) return ViewsEnum.WATER_EXTRACT_CHOOSE_METHOD_VOLUME_VIEW;
        if (methodNamesChoiceBox.getValue().equals("Сдвиговые испытания")) return ViewsEnum.SHEAR_DATA_VIEW;
        if (methodNamesChoiceBox.getValue().equals(LaborMethodsEnum.SOIL_CORROSION_METHOD.getName())) return ViewsEnum.SOIL_CORROSION_INPUT;
        if (methodNamesChoiceBox.getValue().equals(LaborMethodsEnum.COMPRESSION_METHOD.getName())) return ViewsEnum.COMPRESSION_INPUT;
        throw new NotFoundException("Method not found");
    }

    @FXML
    public void methodNamesChoiceBoxChoose(ActionEvent event) {
        methodNamesChoiceBox.setOnAction(e -> {
            try {
                var view = getViewFromMethodNamesChoiceBox();
                JavaFXCommonMethods.changeScene(event, view.getPath(), applicationContext, methodNamesChoiceBox.getValue());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (NotFoundException ex) {
                System.out.println(ex);
            }
            System.out.println(methodNamesChoiceBox.getValue());
        });

    }

    //region Сеттеры чойсбоксов и чекбоксов

    @FXML
    public void setSurveyPointChoiceBox() {
        surveyPoints = surveyPointsService.getAllByProject(currentState.getCurrentProject());
        surveyPointsNumbers = surveyPoints.stream()
                .map(SurveyPoint::getPointNumber)
                .toList();

        ObservableList<String> surveyPointsObservableList = FXCollections.observableArrayList(surveyPointsNumbers);
        surveyPointChoiceBox.getItems().addAll(surveyPointsObservableList);
        surveyPointChoiceBox.setValue(currentState.getSurveyPoint().getPointNumber());
    }

    //endregion

    //region кнопки

    @FXML
    public void onSurveyPointChoiceBoxClicked(ActionEvent event) {
        surveyPointChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            SurveyPoint surveyPoint = surveyPointsService.getByPointNumber(newValue, currentState.getCurrentProject());

            currentState.setSurveyPoint(surveyPoint);
            surveyPointChoiceBox.setValue(currentState.getSurveyPoint().getPointNumber());

            updateObservableList();
        });


        surveyPointChoiceBox.setOnAction(e -> {
            System.out.println("Current Survey Point: " + currentState.getSurveyPoint().getPointNumber());
        });
    }

    @FXML
    public void onPreviousSurveyPointButtonClick(ActionEvent event) {
        System.out.println("onPreviousSurveyPointButtonClick");
        int indexOfCurrentSurveyPoint = surveyPointsNumbers.indexOf(currentState.getSurveyPoint().getPointNumber());

        int prevIndex = indexOfCurrentSurveyPoint > ZERO_INDEX ? indexOfCurrentSurveyPoint - SINGLE_INDEX_POINT : surveyPoints.size() - SINGLE_INDEX_POINT;

        updateCurrentState(prevIndex);

        updateObservableList();
    }

    @FXML
    public void onNextSurveyPointButtonClick(ActionEvent event) {
        System.out.println("onNextSurveyPointButtonClick");

        int indexOfCurrentSurveyPoint = surveyPointsNumbers.indexOf(currentState.getSurveyPoint().getPointNumber());

        int nextIndex = indexOfCurrentSurveyPoint < surveyPoints.size() - SINGLE_INDEX_POINT ? indexOfCurrentSurveyPoint + SINGLE_INDEX_POINT : ZERO_INDEX;

        updateCurrentState(nextIndex);

        updateObservableList();
    }

    private void updateCurrentState(int prevIndex) {
        SurveyPoint nextSurveyPoint = surveyPoints.get(prevIndex);

        currentState.setSurveyPoint(nextSurveyPoint);
        surveyPointChoiceBox.setValue(currentState.getSurveyPoint().getPointNumber());
    }

    public void updateObservableList() {
        //TODO: реализовать
//        dtos = service.getDTOs(currentState.getSurveyPoint());
//
//        this.observableList.clear();
//        this.observableList.addAll(dtos);
//
//        addNewRow();
    }

//    @Override
//    public void updateObservableList() {
//        dtos = service.getDTOs(currentState.getSurveyPoint());
//
//        this.observableList.clear();
//        this.observableList.addAll(dtos);
//    }

    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        System.out.println("saveAllObjectsButton clicked...");
//        service.updateFromDtos(dtos);
    }

    @FXML
    public void onDeleteButtonClicked(ActionEvent event) {
//        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
//
//        Y dto = tableView.getItems().get(selectedIndex);
//
//        service.delete(dto);
//
//        observableList.remove(dto);
//
//        tableView.layout();
//        tableView.getSelectionModel().select(selectedIndex);
    }

    @Override
    @FXML
    public void onSaveAllObjectsButtonClicked() {
        super.onSaveAllObjectsButtonClicked();
        System.out.println("onSaveAllSamplesButtonClicked clicked...");
    }

    @Override
    public List<Sample> setObjectListForObjectListForView() {
//        return service.findBySurveyPoint(currentState.getSurveyPoint());
        return service.getAll();
    }

    @Override
    @FXML
    public void onAddNewRowButtonClicked() {
        Sample sample = new Sample();

        sample.setSurveyPoint(currentState.getSurveyPoint());

        addNewObjectAtListForView(sample);
    }

    //todo придумать уведомление о необходимости нажать на сохранить для удаления из базы
    @Override
    @FXML
    public void onDeleteRowButtonClicked() {
        //todo реализовать выделение строки по умолчанию - следующая после удаленной
        super.onDeleteRowButtonClicked();
        System.out.println("onDeleteRowButtonClicked clicked...");
    }

    @Override
    @FXML
    public void onAllProjectsButtonClicked(ActionEvent event) throws IOException {
        super.onAllProjectsButtonClicked(event);
        System.out.println("Change scene to all projects from labor module...");
    }

    @Override
    @FXML
    public void onFieldModuleButtonClicked(ActionEvent event) throws IOException {
        super.onFieldModuleButtonClicked(event);
        System.out.println("Change scene to field module from labor module...");
    }

    @Override
    @FXML
    public void onLaborModuleButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Trying change scene to labor module from labor module... \n" +
                "Already in labor module");
    }

    @Override
    @FXML
    public void onCameralModuleButtonClicked(ActionEvent event) throws IOException {
        super.onCameralModuleButtonClicked(event);
        System.out.println("Change scene to cameral module from labor module...");
    }

    //endregion
}
