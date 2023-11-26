package org.geoproject.ingeo.controllers.labor;

import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.services.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.geoproject.ingeo.enums.StageTitleEnum.CAMERAL_MODULE;
import static org.geoproject.ingeo.enums.StageTitleEnum.FIELD_MODULE;
import static org.geoproject.ingeo.enums.StageTitleEnum.LABOR_MODULE;
import static org.geoproject.ingeo.enums.StageTitleEnum.PROJECTS;
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_CHEMISTRY_DATA;
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_CHEMISTRY_FINAL_RESULT;
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_CHEMISTRY_MAIN;
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_CHEMISTRY_RESULT;

@Log4j2
public abstract class AbstractLaborMethodTableController<T, Y> {
    protected final ConfigurableApplicationContext applicationContext;
    protected final SampleService sampleService;
    protected final SurveyPointsService surveyPointsService;
    protected final CurrentState currentState;
    protected final TableService<T, Y> service;

    protected Stage stage;

    @FXML
    protected Label projectNameInFooter;
    @FXML
    protected Label projectCipherInFooter;

    @FXML
    protected TableView<Y> tableView;

    protected List<Y> dtos;

    @FXML
    protected ObservableList<Y> observableList;

    protected List<T> objects;

    protected Map<String, TableColumn<Y, ?>> columnsMap;


    protected AbstractLaborMethodTableController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                                 SurveyPointsService surveyPointsService, CurrentState currentState, TableService<T, Y> service) {
        this.applicationContext = applicationContext;
        this.sampleService = sampleService;
        this.surveyPointsService = surveyPointsService;
        this.currentState = currentState;
        this.service = service;
    }

    public void init() {
        setFirstSample();

        setObjectListForView();

        setTableViewSettings();

        setColumnsMap();

        setCellsFormat();

        JavaFXCommonMethods.setFooterElements(currentState, projectNameInFooter, projectCipherInFooter);
    }

    private void setFirstSample() {
        sampleService.setFirstSampleForView();
    }

    public void setObjectListForView() {
        dtos = service.getDTOs(currentState.getSurveyPoint());

        this.observableList = FXCollections.observableArrayList(dtos);
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

    public abstract void addNewRow();

    //region Кнопки

    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        log.info("saveAllObjectsButton clicked...");
        service.updateFromDtos(dtos);
    }

    @FXML
    public void onDeleteButtonClicked(ActionEvent event) {
        log.info("onDeleteButtonClicked clicked...");

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        Y dto = tableView.getItems().get(selectedIndex);

        service.delete(dto);

        observableList.remove(dto);
        dtos.remove(dto);

        tableView.layout();
        tableView.getSelectionModel().select(selectedIndex);

        tableView.refresh();
    }

    @FXML
    public void onEnterPhysicalPropertiesDataButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_CHEMISTRY_MAIN_VIEW.getPath(),
                applicationContext, WATER_CHEMISTRY_MAIN.getTitle());
    }

    @FXML
    public void onEnterDataForCalculatingButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_CHEMISTRY_DATA_VIEW.getPath(),
                applicationContext, WATER_CHEMISTRY_DATA.getTitle());
    }

    @FXML
    public void onWaterChemistryResultTableButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_CHEMISTRY_RESULT_VIEW.getPath(),
                applicationContext, WATER_CHEMISTRY_RESULT.getTitle());
    }

    @FXML
    public void onWaterChemistryFinalResultViewButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_CHEMISTRY_FINAL_RESULT_VIEW.getPath(),
                applicationContext, WATER_CHEMISTRY_FINAL_RESULT.getTitle());
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
