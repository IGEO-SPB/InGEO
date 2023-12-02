package org.geoproject.ingeo.controllers.field;

import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j2;
import org.geoproject.ingeo.controllers.AbstractMainViewController;
import org.geoproject.ingeo.dto.SurveyPointDTO;
import org.geoproject.ingeo.models.*;
import org.geoproject.ingeo.models.classificators.SurveyPointsType;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.services.classificators.SurveyPointsTypesService;
import org.geoproject.ingeo.services.MainViewService;
import org.geoproject.ingeo.services.allProjects.impl.ProjectsServiceImpl;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static org.geoproject.ingeo.constants.ServiceConstants.NO_PROJECTS_SERVICE_MESSAGE;

@Component
@Log4j2
public class FieldModuleMainViewController extends AbstractMainViewController<SurveyPoint, SurveyPointDTO> implements Initializable {
    private final SurveyPointsTypesService surveyPointsTypesService;
    private final ProjectsServiceImpl projectsService;

    @FXML
    ChoiceBox<Project> chooseProjectChoiceBox;

    //    @FXML
//    private TableView<SurveyPoint> tableView;
    @FXML
    private TableColumn<SurveyPoint, String> projectName;
    //    @FXML private TableColumn<SurveyPoint, SurveyPointsType> surveyPointsType;
    @FXML
    private TableColumn<SurveyPoint, String> surveyPointsType;
    @FXML
    private TableColumn<SurveyPoint, String> pointNumber;
    @FXML
    private TableColumn<SurveyPoint, Float> depth;
    @FXML
    private TableColumn<SurveyPoint, Float> absoluteMark;
    @FXML
    private TableColumn<SurveyPoint, Float> coordinateX;
    @FXML
    private TableColumn<SurveyPoint, Float> coordinateY;
    @FXML
    private TableColumn<SurveyPoint, String> startDate;
    @FXML
    private TableColumn<SurveyPoint, String> endDate;
    @FXML
    private TableColumn<SurveyPoint, String> boringType;

    public FieldModuleMainViewController(ConfigurableApplicationContext applicationContext,
                                         MainViewService<SurveyPoint, SurveyPointDTO> service,
                                         CurrentState currentState, SurveyPointsTypesService surveyPointsTypesService,
                                         ProjectsServiceImpl projectsService) {
        super(currentState, applicationContext, service);
        this.surveyPointsTypesService = surveyPointsTypesService;
        this.projectsService = projectsService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Инициализирована таблица с точками исследований");

        init();
        tableView.setEditable(true);
        setChooseProjectChoiceBox();
        showAllObjectsInCurrentProject();
    }

    @Override
    public void showAllObjectsInCurrentProject() {

//        projectName.setCellValueFactory(new PropertyValueFactory<SurveyPoint, Project>("project"));
        projectName.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getProject().getProjectName()));

        ObservableList<String> surveyPointsTypeObservableList = FXCollections.observableArrayList();
        List<String> surveyPointsTypeShortNameList = surveyPointsTypesService.findAll().stream().map(e -> e.getSurveyTypeShortName()).collect(Collectors.toList());
        surveyPointsTypeObservableList.addAll(surveyPointsTypeShortNameList);
//        surveyPointsType.setCellValueFactory(new PropertyValueFactory<SurveyPoint, SurveyPointsType>("surveyPointsType"));
        surveyPointsType.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getSurveyPointsType().getSurveyTypeShortName()));
        surveyPointsType.setCellFactory(ChoiceBoxTableCell.forTableColumn(surveyPointsTypeObservableList));
        surveyPointsType.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurveyPoint, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SurveyPoint, String> event) {
                SurveyPoint surveyPoint = event.getRowValue();
                SurveyPointsType surveyPointsType = surveyPointsTypesService.findBySurveyTypeShortName(event.getNewValue());
                surveyPoint.setSurveyPointsType(surveyPointsType);

                updateObjectInListForView(surveyPoint);
            }
        });

        pointNumber.setCellValueFactory(new PropertyValueFactory<SurveyPoint, String>("pointNumber"));
        pointNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        pointNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurveyPoint, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SurveyPoint, String> event) {
                SurveyPoint surveyPoint = event.getRowValue();
                surveyPoint.setPointNumber(event.getNewValue());
                updateObjectInListForView(surveyPoint);
            }
        });

        depth.setCellValueFactory(new PropertyValueFactory<SurveyPoint, Float>("depth"));
        depth.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        depth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurveyPoint, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SurveyPoint, Float> event) {
                SurveyPoint surveyPoint = event.getRowValue();
                surveyPoint.setDepth(event.getNewValue());
                updateObjectInListForView(surveyPoint);
            }
        });

        absoluteMark.setCellValueFactory(new PropertyValueFactory<SurveyPoint, Float>("absoluteMark"));
        absoluteMark.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        absoluteMark.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurveyPoint, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SurveyPoint, Float> event) {
                SurveyPoint surveyPoint = event.getRowValue();
                surveyPoint.setAbsoluteMark(event.getNewValue());
                updateObjectInListForView(surveyPoint);
            }
        });

        coordinateX.setCellValueFactory(new PropertyValueFactory<SurveyPoint, Float>("coordinateX"));
        coordinateX.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        coordinateX.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurveyPoint, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SurveyPoint, Float> event) {
                SurveyPoint surveyPoint = event.getRowValue();
                surveyPoint.setCoordinateX(event.getNewValue());
                updateObjectInListForView(surveyPoint);
            }
        });

        coordinateY.setCellValueFactory(new PropertyValueFactory<SurveyPoint, Float>("coordinateY"));
        coordinateY.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        coordinateY.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurveyPoint, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SurveyPoint, Float> event) {
                SurveyPoint surveyPoint = event.getRowValue();
                surveyPoint.setCoordinateY(event.getNewValue());
                updateObjectInListForView(surveyPoint);
            }
        });

        //todo добавить реализацию календаря, может переделать тип данных
        startDate.setCellValueFactory(new PropertyValueFactory<SurveyPoint, String>("startDate"));
        startDate.setCellFactory(TextFieldTableCell.forTableColumn());
        startDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurveyPoint, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SurveyPoint, String> event) {
                SurveyPoint surveyPoint = event.getRowValue();
                surveyPoint.setStartDate(event.getNewValue());
                updateObjectInListForView(surveyPoint);
            }
        });

        endDate.setCellValueFactory(new PropertyValueFactory<SurveyPoint, String>("endDate"));
        endDate.setCellFactory(TextFieldTableCell.forTableColumn());
        endDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurveyPoint, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SurveyPoint, String> event) {
                SurveyPoint surveyPoint = event.getRowValue();
                surveyPoint.setEndDate(event.getNewValue());
                updateObjectInListForView(surveyPoint);
            }
        });

        //todo переделать на выпадающий список
        boringType.setCellValueFactory(new PropertyValueFactory<SurveyPoint, String>("boringType"));
        boringType.setCellFactory(TextFieldTableCell.forTableColumn());
        boringType.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SurveyPoint, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SurveyPoint, String> event) {
                SurveyPoint surveyPoint = event.getRowValue();
                surveyPoint.setBoringType(event.getNewValue());
                updateObjectInListForView(surveyPoint);
            }
        });

        tableView.getItems().setAll(objectListForView);
    }

    //кнопки

    @Override
    @FXML
    public void onSaveAllObjectsButtonClicked() {
        super.onSaveAllObjectsButtonClicked();
        log.info("onSaveAllBoreholeLayersButtonClicked clicked...");
    }

    @Override
    public List<SurveyPoint> setObjectListForObjectListForView() {

        System.out.println("CHEEEECK");
        System.out.println(currentState.getCurrentProject());

        if (Objects.isNull(currentState.getCurrentProject())) {
            openAlertModalWindow("Не заполнено ни одного проекта. " +
                    "Создание точки исследования невозможно без проекта." +
                    " Перейдите на страницу с проектами для добавления первого");
            return null;
        }

        return service.getByProject(currentState.getCurrentProject());
    }

    private void openAlertModalWindow(String s) {
        log.info("Alert!!!");
    }

    @Override
    @FXML
    public void onAddNewRowButtonClicked() {
        SurveyPoint surveyPoint = new SurveyPoint();
        SurveyPointsType defaultSurveyPointsType = surveyPointsTypesService.findOne(1);
        surveyPoint.setProject(currentState.getCurrentProject());
        surveyPoint.setSurveyPointsType(defaultSurveyPointsType);

        addNewObjectAtListForView(surveyPoint);
    }
    //todo придумать уведомление о необходимости нажать на сохранить для удаления из базы

    @Override
    @FXML
    public void onDeleteRowButtonClicked() {
        //todo реализовать выделение строки по умолчанию - следующая после удаленной
        super.onDeleteRowButtonClicked();
        log.info("onDeleteRowButtonClicked clicked...");
    }

    @FXML
    public void onShowSurveyPointsFromAllProjectsButtonClicked() {
        setObjectListForView();
        showAllObjectsInCurrentProject();
    }

    @FXML
    public void onChooseProjectChoiceBoxSelect() {
        chooseProjectChoiceBox.setOnAction(e -> tempSetListForTableView(chooseProjectChoiceBox));
    }

    //утилитные методы

    private void setChooseProjectChoiceBox() {
        chooseProjectChoiceBox.setConverter(new StringConverter<Project>() {
            @Override
            public String toString(Project object) {
                return Objects.nonNull(object) ? object.getProjectName() : NO_PROJECTS_SERVICE_MESSAGE;
            }

            @Override
            public Project fromString(String string) {
                return null;
            }
        });

        var projectList = projectsService.getAll();

        if (projectList.isEmpty()) {
            openAlertModalWindow("Не заполнено ни одного проекта. " +
                    "Создание точки исследования невозможно без проекта." +
                    " Перейдите на страницу с проектами для добавления первого");

            var tempProject = new Project();
            tempProject.setProjectName(NO_PROJECTS_SERVICE_MESSAGE);

            chooseProjectChoiceBox.setValue(tempProject);
        } else {
            var projectNameObservableList = FXCollections.observableArrayList(projectList);

            chooseProjectChoiceBox.getItems().addAll(projectNameObservableList);

            chooseProjectChoiceBox.setValue(currentState.getCurrentProject());
        }
    }

    private void tempSetListForTableView(ChoiceBox<Project> chooseProjectChoiceBox) {
        System.out.println(chooseProjectChoiceBox.getValue().getProjectName());
        Project currentProject = projectsService.getById(chooseProjectChoiceBox.getValue().getId());
        currentState.setCurrentProject(currentProject);
        JavaFXCommonMethods.setFooterElements(currentState, projectNameInFooter, projectCipherInFooter);
        List<SurveyPoint> surveyPointList = service.getByProject(currentProject);
        setObjectListForView(surveyPointList);
        showAllObjectsInCurrentProject();
    }
}
