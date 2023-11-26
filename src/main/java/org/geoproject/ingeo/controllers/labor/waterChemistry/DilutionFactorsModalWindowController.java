package org.geoproject.ingeo.controllers.modalWindows;

import org.geoproject.ingeo.dto.WaterSampleDto;
import org.geoproject.ingeo.enums.dtoenums.WaterSampleDtoFieldsEnum;
import org.geoproject.ingeo.models.WaterSample;
import org.geoproject.ingeo.services.tableViews.WaterSampleService;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class DilutionFactorsModalWindowController implements Initializable {

    private final WaterSampleService waterSampleService;
    protected final ConfigurableApplicationContext applicationContext;

    List<WaterSampleDto> waterSampleDtos;
    List<WaterSample> objects;


    ObservableList<WaterSampleDto> observableList;

    Map<TableColumn<WaterSampleDto, ?>, String> columnsMap;

    @FXML
    private TableView<WaterSampleDto> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setObservableList();
        setTableViewSettings();
        setColumnsMap();
        setCellsFormat();
    }

    public void passWaterSampleDtos(List<WaterSampleDto> waterSampleDtos) {
        this.waterSampleDtos = waterSampleDtos;
    }

    public void passObjects(List<WaterSample> objects) {
        this.objects = objects;
    }

    public void setObservableList() {
        this.observableList = FXCollections.observableArrayList();

        this.observableList.addAll(waterSampleDtos);
    }

    public void setTableViewSettings() {
        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(true);
    }

    public void setCellsFormat() {
        columnsMap.keySet().forEach(tableColumn -> JavaFXCommonMethods.setCellFactory(tableColumn, tableView,
                observableList, null, null));

        columnsMap.forEach((column, columnName) -> column.setCellValueFactory(new PropertyValueFactory<>(columnName)));

        columnsMap.forEach((column, columnName) ->
                column.setOnEditCommit(event ->
                        event.getRowValue()
                                .setFieldValue(WaterSampleDtoFieldsEnum.getEnumByName(columnName), event.getNewValue())
                ));

        tableView.setItems(observableList);
    }

    public void setColumnsMap() {
        List<TableColumn<WaterSampleDto, ?>> columns = new ArrayList<>();
        for (var column : tableView.getColumns()) {
            columns.addAll(JavaFXCommonMethods.getAllLeaves(column));
        }

        columnsMap = new LinkedHashMap<>();

        for (TableColumn<WaterSampleDto, ?> column : columns) {
            columnsMap.put(column, column.getId());
        }
    }

    //region Кнопки
    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        waterSampleService.updateFromDtos(waterSampleDtos);
    }

    @FXML
    public void onExitButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    //endregion
}