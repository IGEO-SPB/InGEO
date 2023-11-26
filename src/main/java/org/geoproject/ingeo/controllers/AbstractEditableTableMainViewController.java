package org.geoproject.ingeo.controllers;

import org.geoproject.ingeo.controllers.modalWindows.CreateProjectWindowController;
import org.geoproject.ingeo.dto.mainViewsDto.ProjectDto;
import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.services.mainViews.MainViewService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.geoproject.ingeo.enums.StageTitleEnum.CAMERAL_MODULE;
import static org.geoproject.ingeo.enums.StageTitleEnum.FIELD_MODULE;
import static org.geoproject.ingeo.enums.StageTitleEnum.LABOR_MODULE;
import static org.geoproject.ingeo.enums.StageTitleEnum.PROJECTS;

@Component
public abstract class NewAbstractMainViewController<T, Y> {

    protected final ConfigurableApplicationContext applicationContext;
    protected final MainViewService<T, Y> service;
    protected final CurrentState currentState;

    @FXML
    Label projectNameInFooter;
    @FXML
    Label projectCipherInFooter;

    @FXML
    TableView<Y> tableView;

    Scene scene;
    Stage stage;

    List<Y> dtos;

    @FXML
    ObservableList<Y> observableList; //objectListForView

    List<T> objects;

    Map<TableColumn<Y, ?>, String> columnsMap;

    public NewAbstractMainViewController(ConfigurableApplicationContext applicationContext,
                                         CurrentState currentState,
                                         MainViewService<T, Y> service) {
        this.currentState = currentState;
        this.applicationContext = applicationContext;
        this.service = service;
    }

    public void init() {
        setObjectListForView();

        setTableViewSettings();

        setColumnsMap();

        setCellsFormat();

        JavaFXCommonMethods.setFooterElements(currentState, projectNameInFooter, projectCipherInFooter);
    }

    public void setObjectListForView() {
        objects = service.getByProject(currentState.getCurrentProject());
        dtos = service.getDtos(objects);

        this.observableList = FXCollections.observableArrayList();
        this.observableList.addAll(dtos);
    }

    public void setObjectListForView(List<T> objectListForView) {
        dtos = service.getDtos(objectListForView);
        observableList.addAll(dtos);
    }

    public void setTableViewSettings() {
        tableView.setEditable(true);
//        tableView.getSelectionModel().setCellSelectionEnabled(true);
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
    public void onCreateProjectButtonClicked(ActionEvent event) throws IOException {
//        JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsPathNamesEnum.CREATE_NEW_PROJECT_VIEW.getPath(),
//                applicationContext, stage, StageTitleEnum.NEW_PROJECT.getTitle());

        System.out.println("onCreateProjectButtonClicked inited");

        Stage dialog = new Stage();
        dialog.setTitle(StageTitleEnum.NEW_PROJECT.getTitle());

        CreateProjectWindowController childController = (CreateProjectWindowController) applicationContext.getBean("createProjectWindowController");
        childController.passObservableList((ObservableList<ProjectDto>) observableList);

        var path = ViewsEnum.CREATE_NEW_PROJECT_VIEW.getPath();
        var title = ViewsEnum.CREATE_NEW_PROJECT_VIEW.getTitle();
        JavaFXCommonMethods.changeSceneToModalWindow(event, path, applicationContext, stage, title);
    }
    @FXML
    public void onEditProjectButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.EDIT_PROJECT_VIEW.getPath(),
                applicationContext, stage, StageTitleEnum.EDIT_PROJECT.getTitle());
    }

    @FXML
    public void onSaveChangesButtonClicked(ActionEvent event) {
        System.out.println("saveAllObjectsButton clicked...");
        service.updateFromDtos(objects, dtos);
    }

    @FXML
    public void onDeleteRowButtonClicked(ActionEvent event) {
        System.out.println("Abstract method 'onDeleteRowButtonClicked()' inited...");

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        Y dto = tableView.getItems().get(selectedIndex);

        service.delete(dto);

        observableList.remove(dto);

        tableView.layout();
        tableView.getSelectionModel().select(selectedIndex);
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
