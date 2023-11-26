package org.geoproject.ingeo.controllers.labor;

import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.geoproject.ingeo.enums.StageTitleEnum.CAMERAL_MODULE;
import static org.geoproject.ingeo.enums.StageTitleEnum.FIELD_MODULE;
import static org.geoproject.ingeo.enums.StageTitleEnum.LABOR_MODULE;
import static org.geoproject.ingeo.enums.StageTitleEnum.PROJECTS;

public abstract class AbstractLaborMethodResultTableController<T, Y> {
    protected final ConfigurableApplicationContext applicationContext;
    protected final SampleService sampleService;
    protected final CurrentState currentState;
    protected final TableService<T, Y> service;

//    Scene scene;
//    Stage stage;

    @FXML
    public Label projectNameInFooter;
    @FXML
    public Label projectCipherInFooter;

    @FXML
    public TableView<Y> tableView;

    public List<Y> dtos;

    @FXML
    public ObservableList<Y> observableList;

    public List<T> objects;

    public Map<TableColumn<Y, ?>, String> columnsMap;


    public AbstractLaborMethodResultTableController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                                    CurrentState currentState, TableService<T, Y> service) {
        this.applicationContext = applicationContext;
        this.sampleService = sampleService;
        this.currentState = currentState;
        this.service = service;
    }

    public void init() {
        setFirstSample();

        setObjectListForView();

        setTableViewSettings();

        setColumnsMap();

        setCellsFormat();

        JavaFXCommonMethods.setFooterElements(currentState, projectNameInFooter, projectCipherInFooter);
    }

    private void setFirstSample() {
        sampleService.setFirstSampleForView();
    }

    public void setObjectListForView() {
//        objects = new ArrayList<>();
//        objects = service.getEntitiesBySurveyPoint(currentState.getSurveyPoint());
        dtos = service.getDTOs(currentState.getSurveyPoint());
        this.observableList = FXCollections.observableArrayList();
        this.observableList.addAll(dtos);
    }

    public void setTableViewSettings() {
        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(true);
    }

    public void setColumnsMap() {
        List<TableColumn<Y, ?>> columns = new ArrayList<>();
        for (var column : tableView.getColumns()) {
            columns.addAll(JavaFXCommonMethods.getAllLeaves(column));
        }

        columnsMap = new LinkedHashMap<>();

        for (TableColumn<Y, ?> column : columns) {
            columnsMap.put(column, column.getId());
        }
    }

    public abstract void setCellsFormat();

    //region Кнопки
    @FXML
    public void onSaveChangesButtonClicked(ActionEvent event) {
        System.out.println("saveAllObjectsButton clicked...");

        service.updateFromDtos(dtos);
    }

    @FXML
    public void onAllProjectsButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.ALL_PROJECTS_VIEW.getPath(),
                applicationContext, PROJECTS.getTitle());
    }

    @FXML
    public void onFieldModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.FIELD_MODULE_MAIN_VIEW.getPath(),
                applicationContext, FIELD_MODULE.getTitle());
    }

    @FXML
    public void onLaborModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.LABOR_MODULE_MAIN_VIEW.getPath(),
                applicationContext, LABOR_MODULE.getTitle());
    }

    @FXML
    public void onCameralModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.CAMERAL_MODULE_MAIN_VIEW.getPath(),
                applicationContext, CAMERAL_MODULE.getTitle());
    }
    //endregion
}