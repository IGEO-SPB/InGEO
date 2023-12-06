package org.geoproject.ingeo.controllers;

import lombok.extern.log4j.Log4j2;
import org.geoproject.ingeo.controllers.allProjects.CreateProjectWindowController;
import org.geoproject.ingeo.dto.mainViewsDtos.ProjectDto;
import org.geoproject.ingeo.enums.StageTitleEnum;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.services.MainViewService;
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

@Log4j2
@Component
public abstract class AbstractEditableTableMainViewController<T, Y> {

    //endregion
}
