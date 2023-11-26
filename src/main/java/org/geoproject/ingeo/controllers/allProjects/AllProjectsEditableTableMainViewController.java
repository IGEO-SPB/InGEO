package org.geoproject.ingeo.controllers.objectChoice;

import org.geoproject.ingeo.controllers.AbstractEditableTableMainViewController;
import org.geoproject.ingeo.dto.mainViewsDtos.ProjectDto;
import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.UserInterfaceException;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.services.MainViewService;
import org.geoproject.ingeo.services.objectChoice.ProjectsService;
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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.JavaFXConstants.SINGLE_CLICK_COUNT;

@Component
@Scope("prototype")
public class AllProjectsEditableTableMainViewController extends AbstractEditableTableMainViewController<Project, ProjectDto> implements Initializable {

    ProjectDto chosenProjectDto;
    CurrentState currentState;

    public AllProjectsEditableTableMainViewController(CurrentState currentState, ConfigurableApplicationContext applicationContext, MainViewService<Project, ProjectDto> service) {
        super(applicationContext,currentState,service);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    @Override
    public void setObjectListForView() {
        objects = service.getAll();

        List<Project> nonArchiveObjects = objects.stream()
                .filter(object -> Boolean.FALSE.equals(object.getIsArchive()))
                .toList();

        dtos = service.getDtos(nonArchiveObjects);

        this.observableList = FXCollections.observableArrayList();
        this.observableList.addAll(dtos);
    }

    @Override
    public void setTableViewSettings() {
        tableView.setEditable(false);
        tableView.getSelectionModel().setCellSelectionEnabled(false);
    }

    @Override
    public void setCellsFormat() {
        columnsMap.forEach((column, columnName) -> column.setCellValueFactory(new PropertyValueFactory<>(columnName)));

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == SINGLE_CLICK_COUNT) {
                chosenProjectDto = tableView.getSelectionModel().getSelectedItem();
            }
        });

        tableView.setItems(observableList);
    }

    @Override
    @FXML
    public void onEditProjectButtonClicked(ActionEvent event) throws IOException {
        if (chosenProjectDto == null) {
            throw new UserInterfaceException(ExceptionTypeEnum.PROJECT_NOT_CHOSEN_EXCEPTION.getMessage());
        }

        EditProjectWindowController childController = (EditProjectWindowController) applicationContext.getBean("editProjectWindowController");
        childController.passSceneUpdateable(this::updateScene);

        Project chosenProject = ((ProjectsService) service).getByContractNumber(chosenProjectDto.getContractNumber());
        childController.passChosenProject(chosenProject);

        JavaFXCommonMethods.changeSceneToModalWindow(event, ViewsEnum.EDIT_PROJECT_VIEW.getPath(),
                applicationContext, stage, StageTitleEnum.EDIT_PROJECT.getTitle());
    }

    private void updateScene() {
        setObjectListForView();
        tableView.setItems(observableList);
    }
}