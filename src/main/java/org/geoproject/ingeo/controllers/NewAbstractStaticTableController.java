package org.geoproject.ingeo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.extern.log4j.Log4j2;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.services.MainViewService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Log4j2
public abstract class NewAbstractStaticTableController<T, Y> {
    protected final ConfigurableApplicationContext applicationContext;
    protected final MainViewService<T, Y> service;
    protected final CurrentState currentState;

    protected ObservableList<Y> observableDtoList;

    protected List<Y> dtos;

    @FXML
    protected TableView<Y> tableView;
    public Map<String, TableColumn<Y, ?>> columnsMap;

    protected @FXML Label projectNameInFooter;
    protected @FXML Label projectCipherInFooter;

    public NewAbstractStaticTableController(CurrentState currentState, ConfigurableApplicationContext applicationContext,
                                            MainViewService<T, Y> service) {
        this.applicationContext = applicationContext;
        this.service = service;
        this.currentState = currentState;
    }

    public void init() {
        setObjectListForView();

        setTableViewSettings();

        setColumnsMap();

        setCellsFormat();

        JavaFXCommonMethods.setFooterElements(currentState, projectNameInFooter, projectCipherInFooter);
    }

    public void setObjectListForView() {
        dtos = service.getDtosByProject(currentState.getCurrentProject());
        this.observableDtoList = FXCollections.observableArrayList();
        this.observableDtoList.addAll(dtos);

        sortObservableDtoList(dtos);
    }

    public abstract void sortObservableDtoList(List<Y> dtos);

    public void onSaveAllObjectsButtonClicked() {
        log.info("Abstract method 'onSaveAllObjectsButtonClicked()' inited...");

        service.updateFromDtos(dtos);

        setObjectListForView();

        tableView.refresh();
    }

    public void onDeleteRowButtonClicked() {
        log.info("Abstract method 'onDeleteRowButtonClicked()' inited...");

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        Y object = tableView.getItems().get(selectedIndex);
        dtos.remove(object);
        observableDtoList.clear();
        observableDtoList.addAll(dtos);
        tableView.refresh();

        tableView.getSelectionModel().select(selectedIndex);

        service.deleteByDto(object);
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
            columnsMap.put(column.getId(), column);
        }
    }

    public abstract void setCellsFormat();

    //region Кнопки
    @FXML
    public void onAllProjectsButtonClicked(ActionEvent event) throws IOException {
        log.info("Change scene to all projects...");

        JavaFXCommonMethods.changeScene(event, ViewsEnum.ALL_PROJECTS_VIEW.getPath(),
                applicationContext, ViewsEnum.ALL_PROJECTS_VIEW.getTitle());
    }

    @FXML
    public void onFieldModuleButtonClicked(ActionEvent event) throws IOException {
        log.info("Change scene to field module...");

        JavaFXCommonMethods.changeScene(event, ViewsEnum.FIELD_MODULE_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.FIELD_MODULE_MAIN_VIEW.getTitle());
    }

    @FXML
    public void onLaborModuleButtonClicked(ActionEvent event) throws IOException {
        log.info("Change scene to labor module...");

        JavaFXCommonMethods.changeScene(event, ViewsEnum.LABOR_MODULE_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.LABOR_MODULE_MAIN_VIEW.getTitle());
    }

    @FXML
    public void onCameralModuleButtonClicked(ActionEvent event) throws IOException {
        log.info("Change scene to cameral module...");

        JavaFXCommonMethods.changeScene(event, ViewsEnum.CAMERAL_MODULE_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.CAMERAL_MODULE_MAIN_VIEW.getTitle());
    }
    //endregion
}