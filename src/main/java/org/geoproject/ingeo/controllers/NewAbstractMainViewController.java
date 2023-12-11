package org.geoproject.ingeo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
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
public abstract class NewAbstractMainViewController<T, Y> {
    protected final ConfigurableApplicationContext applicationContext;
    protected final MainViewService<T, Y> service;
    protected final CurrentState currentState;

    protected ObservableList<Y> observableDtoList;
//    List<T> newObjectList;
//    List<T> updatedObjectList;
//    List<T> deletedObjectList;

    protected List<Y> dtos;

    Scene scene;
    Stage stage;

    @FXML
    protected TableView<Y> tableView;
    public Map<String, TableColumn<Y, ?>> columnsMap;


    protected @FXML Label projectNameInFooter;
    protected @FXML Label projectCipherInFooter;


    public NewAbstractMainViewController(CurrentState currentState, ConfigurableApplicationContext applicationContext,
                                         MainViewService<T, Y> service) {
        this.applicationContext = applicationContext;
        this.service = service;
        this.currentState = currentState;

//        this.newObjectList = new ArrayList<>();
//        this.updatedObjectList = new ArrayList<>();
//        this.deletedObjectList = new ArrayList<>();
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

//    public void addNewObjectAtListForView(T object) {
//        this.objectListForView.add(object);
//        this.newObjectList.add(object);
//
//        showAllObjectsInCurrentProject();
//    }

//    public void updateObjectInListForView(T object) {
//        T updatedObject = objectListForView
//                .stream()
//                .filter(e -> e.equals(object))
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("Такого объекта нет в списке для представления"));
//
//        if (newObjectList.contains(updatedObject)) {
//            newObjectList.remove(updatedObject);
//            newObjectList.add(object);
//        } else {
//            updatedObjectList.remove(updatedObject);
//            updatedObjectList.add(object);
//        }
//
//        objectListForView.remove(updatedObject);
//        objectListForView.add(object);
//
//        showAllObjectsInCurrentProject();
//    }

//    public abstract void showAllObjectsInCurrentProject();

    public void onSaveAllObjectsButtonClicked() {
        log.info("Abstract method 'onSaveAllObjectsButtonClicked()' inited...");
//        service.create(updatedObjectList);
//        service.create(newObjectList);
//        service.delete(deletedObjectList);
//        updatedObjectList.clear();
//        newObjectList.clear();
//        deletedObjectList.clear();
//        List<T> objectList = setObjectListForObjectListForView();
//
//        setObjectListForView(objectList);
//        showAllObjectsInCurrentProject();

        dtos = tableView.getItems();

        service.updateFromDtos(dtos);

        sortObservableDtoList(dtos);
        tableView.refresh();
    }



    public void onDeleteRowButtonClicked() {
        //todo реализовать выделение строки по умолчанию - следующая после удаленной
        log.info("Abstract method 'onDeleteRowButtonClicked()' inited...");

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Y object = tableView.getItems().get(selectedIndex);
        dtos.remove(object);
        observableDtoList.clear();
        observableDtoList.addAll(dtos);
        tableView.refresh();

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
        JavaFXCommonMethods.changeScene(event, ViewsEnum.ALL_PROJECTS_VIEW.getPath(),
                applicationContext, ViewsEnum.ALL_PROJECTS_VIEW.getTitle());
    }

    @FXML
    public void onFieldModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.FIELD_MODULE_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.FIELD_MODULE_MAIN_VIEW.getTitle());
    }

    @FXML
    public void onLaborModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.LABOR_MODULE_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.LABOR_MODULE_MAIN_VIEW.getTitle());
    }

    @FXML
    public void onCameralModuleButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.CAMERAL_MODULE_MAIN_VIEW.getPath(),
                applicationContext, ViewsEnum.CAMERAL_MODULE_MAIN_VIEW.getTitle());
    }
    //endregion
}