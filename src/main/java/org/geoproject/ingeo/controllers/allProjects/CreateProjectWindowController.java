package org.geoproject.ingeo.controllers.allProjects;

import org.geoproject.ingeo.controllers.modalWindows.AbstractTextFiledModalController;
import org.geoproject.ingeo.dto.mainViewsDtos.ProjectDto;
import org.geoproject.ingeo.enums.dtoenums.ProjectDTOFieldsEnum;
import org.geoproject.ingeo.mapper.ProjectMapper;
import org.geoproject.ingeo.models.Employee;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.classificators.ConstructionType;
import org.geoproject.ingeo.services.admin.EmployeesService;
import org.geoproject.ingeo.services.classificators.ConstructionTypeService;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.ModalWindowService;
import org.geoproject.ingeo.utils.CurrentState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.constants.JavaFXConstants.CONSTRUCTION_TYPE_CHOICE_BOX_VALUE;
import static org.geoproject.ingeo.constants.JavaFXConstants.EMPLOYEE_CHOICE_BOX_VALUE;
import static org.geoproject.ingeo.constants.JavaFXConstants.EMPLOYEE_FIELD_PATTERN;
import static org.geoproject.ingeo.constants.JavaFXConstants.SECOND_INDEX;

@Component
public class CreateProjectWindowController extends AbstractTextFiledModalController<Project, ProjectDto>
        implements Initializable {

    private final EmployeesService employeesService;
    private final ConstructionTypeService constructionTypeService;
    private final ProjectMapper mapper;

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
    @FXML
    ObservableList<Project> observableList;

    public CreateProjectWindowController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                         ModalWindowService<Project, ProjectDto> service, CurrentState currentState, EmployeesService employeesService, ConstructionTypeService constructionTypeService, ProjectMapper mapper) {
        super(applicationContext, sampleService, service, currentState);
        this.employeesService = employeesService;
        this.constructionTypeService = constructionTypeService;
        this.mapper = mapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();

        setConstructionTypeChoiceBox();
        setEmployeeChoiceBoxes();
        setDatePickerForDates();
    }

    public void passObservableList(ObservableList<Project> list) {
        observableList = list;
    }

    @Override
    public void setDTO() {
        dto = new ProjectDto();
    }

    @Override
    public void setTextFieldMap() {
        allTextFieldMap.put(contractNumber, contractNumber.getId());
        allTextFieldMap.put(archiveNumber, archiveNumber.getId());
        allTextFieldMap.put(projectName, projectName.getId());
        allTextFieldMap.put(region, region.getId());
        allTextFieldMap.put(city, city.getId());
        allTextFieldMap.put(district, district.getId());
        allTextFieldMap.put(town, town.getId());
        allTextFieldMap.put(street, street.getId());
        allTextFieldMap.put(reportName, reportName.getId());
        allTextFieldMap.put(absoluteMediumWinterTemperature, absoluteMediumWinterTemperature.getId());
        allTextFieldMap.put(projectingStage, projectingStage.getId());
        allTextFieldMap.put(coordinateX, coordinateX.getId());
        allTextFieldMap.put(coordinateY, coordinateY.getId());
    }

    @Override
    public void setFieldBinding() {
        allTextFieldMap.keySet().forEach(textField ->
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (Objects.isNull(newValue) || newValue.length() == 0) {
                        System.out.println("Введена пустая строка. Введите число");
                        textField.setText(StringUtils.EMPTY);
                    } else if (textField.isFocused()) {
                        textField.setText(newValue);
                    }

                    if (Objects.nonNull(oldValue) && !oldValue.isEmpty() && !oldValue.equals(newValue)) {
                        System.out.println("А теперь обновляется");
                        dto.setFieldValue(ProjectDTOFieldsEnum.getEnumByName(allTextFieldMap.get(textField)), newValue);
                    }
                }));
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

        choiceBoxMap.forEach((choiceBox, fieldName) -> {
            choiceBox.setValue(EMPLOYEE_CHOICE_BOX_VALUE);
            choiceBox.getItems().addAll(employeeObservableList);

            choiceBox.setOnAction(event -> {
                Long employeeId = Long.valueOf(choiceBox.getValue().split(EMPLOYEE_FIELD_PATTERN)[SECOND_INDEX]);

                Employee employee = employeesService.getById(employeeId);

                dto.setFieldValue(ProjectDTOFieldsEnum.getEnumByName(fieldName), employee);
            });
        });
    }

    private void setConstructionTypeChoiceBox() {
        ObservableList<String> constructionTypeObservableList = FXCollections.observableArrayList();

        constructionType.setValue(CONSTRUCTION_TYPE_CHOICE_BOX_VALUE);
        List<ConstructionType> constructionTypes = constructionTypeService.getAll();

        constructionTypeObservableList.addAll(constructionTypes.stream()
                .map(ConstructionType::getType)
                .toList());

        constructionType.getItems().addAll(constructionTypeObservableList);

        constructionType.setOnAction(e -> {
            String type = constructionType.getValue();

            dto.setConstructionType(type);
        });
    }

    public void setDatePickerForDates() {
        Map<DatePicker, String> datePickerMap = new HashMap<>();

        datePickerMap.put(assignmentDate, assignmentDate.getId());
        datePickerMap.put(startDate, startDate.getId());
        datePickerMap.put(endDate, endDate.getId());

        datePickerMap.forEach((datePickerField, fieldName) ->
                datePickerField.valueProperty().addListener((observableValue, oldValue, newValue) -> {
                    dto.setFieldValue(ProjectDTOFieldsEnum.getEnumByName(fieldName), newValue);
                }));
    }

    @Override
    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        saveEntity();
        observableList.add(mapper.projectDtoToProject(dto));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}