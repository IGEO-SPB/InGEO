package org.geoproject.ingeo.controllers.allProjects;

import org.geoproject.ingeo.controllers.labor.functionalInterfaces.SceneUpdateable;
import org.geoproject.ingeo.controllers.modalWindows.AbstractTextFiledModalController;
import org.geoproject.ingeo.dto.mainViewsDtos.EditProjectDto;
import org.geoproject.ingeo.models.Employee;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.classificators.ConstructionType;
import org.geoproject.ingeo.services.admin.EmployeesService;
import org.geoproject.ingeo.services.classificators.ConstructionTypeService;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.ModalWindowService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.JavaFXConstants.EMPLOYEE_FIELD_PATTERN;

@Component
public class EditProjectWindowController extends AbstractTextFiledModalController<Project, EditProjectDto>
        implements Initializable {

    private final EmployeesService employeesService;
    private final ConstructionTypeService constructionTypeService;

    @FXML
    private TextField contractNumber;
    @FXML
    private TextField archiveNumber;
    @FXML
    private TextField projectName;
    @FXML
    private TextField region;
    @FXML
    private TextField city;
    @FXML
    private TextField district;
    @FXML
    private TextField town;
    @FXML
    private TextField street;
    @FXML
    private ChoiceBox<String> constructionType;
    @FXML
    private TextField reportName;
    @FXML
    private TextField absoluteMediumWinterTemperature;
    @FXML
    private DatePicker assignmentDate;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField projectingStage;
    @FXML
    private TextField coordinateX;
    @FXML
    private TextField coordinateY;
    @FXML
    private ChoiceBox<String> createdBy;
    @FXML
    private ChoiceBox<String> executor;
    @FXML
    private ChoiceBox<String> approver;

    SceneUpdateable sceneUpdateable;
    Project chosenProject;

    protected Map<Control, Property> allTextFieldMap = new HashMap<>();

    public EditProjectWindowController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                       ModalWindowService<Project, EditProjectDto> service, CurrentState currentState,
                                       EmployeesService employeesService, ConstructionTypeService constructionTypeService) {
        super(applicationContext, sampleService, service, currentState);
        this.employeesService = employeesService;
        this.constructionTypeService = constructionTypeService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();

        setConstructionTypeChoiceBox();
        setEmployeeChoiceBoxes();
    }


    public void passSceneUpdateable(SceneUpdateable sceneUpdateable) {
        this.sceneUpdateable = sceneUpdateable;
    }

    public void passChosenProject(Project project) {
        chosenProject = project;
    }

    @Override
    public void setDTO() {
        dto = service.getDto(chosenProject);
    }

    @Override
    public void setTextFieldMap() {
        allTextFieldMap.put(contractNumber, dto.contractNumberProperty());
        allTextFieldMap.put(archiveNumber, dto.archiveNumberProperty());
        allTextFieldMap.put(projectName, dto.projectNameProperty());
        allTextFieldMap.put(region, dto.regionProperty());
        allTextFieldMap.put(city, dto.cityProperty());
        allTextFieldMap.put(district, dto.districtProperty());
        allTextFieldMap.put(town, dto.townProperty());
        allTextFieldMap.put(street, dto.streetProperty());
        allTextFieldMap.put(reportName, dto.reportNameProperty());
        allTextFieldMap.put(absoluteMediumWinterTemperature, dto.absoluteMediumWinterTemperatureProperty());
        allTextFieldMap.put(projectingStage, dto.projectingStageProperty());
        allTextFieldMap.put(coordinateX, dto.coordinateXProperty());
        allTextFieldMap.put(coordinateY, dto.coordinateYProperty());

        allTextFieldMap.put(constructionType, dto.constructionTypeProperty());
        allTextFieldMap.put(assignmentDate, dto.assignmentDateProperty());
        allTextFieldMap.put(startDate, dto.startDateProperty());
        allTextFieldMap.put(endDate, dto.endDateProperty());
        allTextFieldMap.put(createdBy, dto.createdByProperty());
        allTextFieldMap.put(executor, dto.executorProperty());
        allTextFieldMap.put(approver, dto.approverProperty());
    }

    @Override
    public void setFieldBinding() {
        JavaFXCommonMethods.setControlFieldBinding(allTextFieldMap);
    }

    private void setEmployeeChoiceBoxes() {
        Map<ChoiceBox<String>, String> choiceBoxMap = new HashMap<>();

        choiceBoxMap.put(createdBy, createdBy.getId());
        choiceBoxMap.put(executor, executor.getId());
        choiceBoxMap.put(approver, approver.getId());

        ObservableList<String> employeeObservableList = FXCollections.observableArrayList();

        List<Employee> employees = employeesService.getAll();

        employeeObservableList.addAll(employees.stream()
                .map(employee -> employee.getName() + EMPLOYEE_FIELD_PATTERN + employee.getId())
                .toList());

        choiceBoxMap.forEach((choiceBox, fieldName) -> choiceBox.getItems().addAll(employeeObservableList));
    }

    private void setConstructionTypeChoiceBox() {
        ObservableList<String> constructionTypeObservableList = FXCollections.observableArrayList();

        constructionType.setValue(dto.getConstructionType());
        List<ConstructionType> constructionTypes = constructionTypeService.getAll();

        constructionTypeObservableList.addAll(constructionTypes.stream()
                .map(ConstructionType::getType)
                .toList());

        constructionType.getItems().addAll(constructionTypeObservableList);
    }

    @Override
    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        saveEntity();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void saveEntity() {
        service.update(dto);
        sceneUpdateable.updateScene();
    }
}