package org.geoproject.ingeo.controllers.allProjects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.geoproject.ingeo.controllers.AbstractEditableTableMainViewController;
import org.geoproject.ingeo.dto.mainViewsDtos.ProjectDto;
import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.UserInterfaceException;
import org.geoproject.ingeo.mapper.ProjectMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.repositories.ProjectsRepository;
import org.geoproject.ingeo.services.MainViewService;
import org.geoproject.ingeo.services.allProjects.ProjectsService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.text.View;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

import static org.geoproject.ingeo.constants.JavaFXConstants.SINGLE_CLICK_COUNT;
import static org.geoproject.ingeo.enums.StageTitleEnum.*;
import static org.geoproject.ingeo.enums.StageTitleEnum.CAMERAL_MODULE;

@Component
@RequiredArgsConstructor
@Scope("prototype")
@Log4j2
public class AllProjectsEditableTableMainViewController implements Initializable{

    private final ConfigurableApplicationContext applicationContext;
    private final CurrentState currentState;
    private final ProjectsRepository projectsRepository;

    @FXML
    protected TableView<Project> tableView;

    protected ObservableList<Project> objects = FXCollections.observableArrayList();

    protected Map<TableColumn<Project, ?>, String> columnsMap;


    public void init() {
        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(true);

        var queriedObjects = projectsRepository.findAll();
        //queriedObjects = objects.stream().filter(x -> !x.getIsArchive()).toList();
        objects.addAll(queriedObjects);

        setColumnsMap();

        setCellsFormat();
    }

    public void setColumnsMap() {
        List<TableColumn<Project, ?>> columns = new ArrayList<>();
        for (var column : tableView.getColumns()) {
            columns.addAll(JavaFXCommonMethods.getAllLeaves(column));
        }

        columnsMap = new LinkedHashMap<>();

        for (TableColumn<Project, ?> column : columns) {
            columnsMap.put(column, column.getId());
        }
    }

    public Button editProject;
    public Button deleteProject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        updateScene();
        log.info(objects);
        currentState.getCurrentProjectProperty().addListener((observableValue, projectDto, t1) -> {
            var chosenProjectStatus = t1 == null;
            deleteProject.setDisable(chosenProjectStatus);
            editProject.setDisable(chosenProjectStatus);
        });
    }

    public void setCellsFormat() {
        columnsMap.forEach((column, columnName) -> column.setCellValueFactory(new PropertyValueFactory<>(columnName)));

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == SINGLE_CLICK_COUNT) {
                currentState.setCurrentProject(tableView.getSelectionModel().getSelectedItem());
            }
        });

        tableView.setItems(objects);
    }

    @FXML
    public void onEditProjectButtonClicked(ActionEvent event) throws IOException {
        if (currentState.getCurrentProject() == null) {
            throw new UserInterfaceException(ExceptionTypeEnum.PROJECT_NOT_CHOSEN_EXCEPTION.getMessage());
        }

        EditProjectWindowController childController = (EditProjectWindowController) applicationContext.getBean("editProjectWindowController");
        childController.passSceneUpdateable(this::updateScene);

        childController.passChosenProject(currentState.getCurrentProject());

        JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.EDIT_PROJECT_VIEW.getPath(),
                applicationContext, StageTitleEnum.EDIT_PROJECT.getTitle());
    }

    private void updateScene() {
        tableView.setItems(objects);
    }

    public void onCreateProjectButtonClicked(ActionEvent event) throws IOException {
        CreateProjectWindowController childController = (CreateProjectWindowController) applicationContext.getBean("createProjectWindowController");
        childController.passObservableList(objects);

        JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.CREATE_NEW_PROJECT_VIEW.getPath(),
                applicationContext, ViewsEnum.CREATE_NEW_PROJECT_VIEW.getTitle());
    }

    public void onDeleteRowButtonClicked(ActionEvent actionEvent) {
        if (currentState.getCurrentProject() == null) {
            throw new UserInterfaceException(ExceptionTypeEnum.PROJECT_NOT_CHOSEN_EXCEPTION.getMessage());
        }

        objects.removeIf(project -> project == currentState.getCurrentProject());
        currentState.setCurrentProject(null);
    }
}