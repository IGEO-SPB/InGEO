package org.geoproject.ingeo.controllers;

import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.services.MainViewService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AbstractMainViewController<T, Y> {
    protected final ConfigurableApplicationContext applicationContext;
    protected final MainViewService<T, Y> service;
    protected final CurrentState currentState;

    protected ObservableList<T> objectListForView;
    List<T> newObjectList;
    List<T> updatedObjectList;
    List<T> deletedObjectList;

    @FXML
    protected TableView<T> tableView;

    public AbstractMainViewController(CurrentState currentState,ConfigurableApplicationContext applicationContext, MainViewService<T, Y> service) {
        this.applicationContext = applicationContext;
        this.service = service;
        this.currentState = currentState;

        this.newObjectList = new ArrayList<>();
        this.updatedObjectList = new ArrayList<>();
        this.deletedObjectList = new ArrayList<>();
    }

    public void init() {
        setObjectListForView();
    }

    public void setObjectListForView() {
        this.objectListForView = FXCollections.observableArrayList();
        this.objectListForView.addAll(service.getAll());
    }

    public void setObjectListForView(List<T> objectListForView) {
        objectListForView = FXCollections.observableArrayList();
        objectListForView.addAll(objectListForView);
    }

    public void addNewObjectAtListForView(T object) {
        this.objectListForView.add(object);
        this.newObjectList.add(object);

        showAllObjectsInCurrentProject();
    }

    public void updateObjectInListForView(T object) {
        T updatedObject = objectListForView
                .stream()
                .filter(e -> e.equals(object))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Такого объекта нет в списке для представления"));

        if (newObjectList.contains(updatedObject)) {
            newObjectList.remove(updatedObject);
            newObjectList.add(object);
        } else {
            updatedObjectList.remove(updatedObject);
            updatedObjectList.add(object);
        }

        objectListForView.remove(updatedObject);
        objectListForView.add(object);

        showAllObjectsInCurrentProject();
    }

    public abstract void showAllObjectsInCurrentProject();
    public void onSaveAllObjectsButtonClicked() {
        System.out.println("Abstract method 'onSaveAllObjectsButtonClicked()' inited...");
        service.create(updatedObjectList);
        service.create(newObjectList);
        service.delete(deletedObjectList);
        updatedObjectList.clear();
        newObjectList.clear();
        deletedObjectList.clear();
        List<T> objectList = setObjectListForObjectListForView();

        setObjectListForView(objectList);
        showAllObjectsInCurrentProject();
    }

    public abstract List<T> setObjectListForObjectListForView();

    public abstract void onAddNewRowButtonClicked();
    //todo придумать уведомление о необходимости нажать на сохранить для удаления из базы

    public void onDeleteRowButtonClicked() {
        //todo реализовать выделение строки по умолчанию - следующая после удаленной
        System.out.println("Abstract method 'onDeleteRowButtonClicked()' inited...");

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        T object = tableView.getItems().get(selectedIndex);
        objectListForView.remove(object);
        deletedObjectList.add(object);

        showAllObjectsInCurrentProject();
    }
}