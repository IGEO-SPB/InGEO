package org.geoproject.ingeo.controllers.modalWindows;

import org.geoproject.ingeo.dto.classificators.WeighingBottleDto;
import org.geoproject.ingeo.enums.dtoenums.classificators.WeighingBottleDtoFieldsEnum;
import org.geoproject.ingeo.models.classificators.WeighingBottle;
import org.geoproject.ingeo.services.classificators.WeighingBottleService;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class WeighingBottleModalWindowController implements Initializable {

    private final WeighingBottleService weighingBottleService;
    protected final ConfigurableApplicationContext applicationContext;

    ObservableList<WeighingBottleDto> observableList;
    List<WeighingBottle> weighingBottles;
    List<WeighingBottleDto> dtos;
    List<WeighingBottleDto> newDtos;

    Map<TableColumn<WeighingBottleDto, ?>, String> columnsMap;

    @FXML
    private TableView<WeighingBottleDto> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setObservableList();
        setTableViewSettings();
        setColumnsMap();
        setCellsFormat();
    }

    public void setObservableList() {
        newDtos = new ArrayList<>();
        this.observableList = FXCollections.observableArrayList();
        this.weighingBottles = weighingBottleService.getAll();

        List<WeighingBottle> activeWeighingBottles = weighingBottleService.getActiveEntities(weighingBottles);

        dtos = weighingBottleService.getDTOs(activeWeighingBottles);
        this.observableList.addAll(dtos);
    }

    public void setTableViewSettings() {
        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(true);
    }

    public void setCellsFormat() {
        columnsMap.keySet().forEach(tableColumn -> JavaFXCommonMethods.setCellFactory(tableColumn, tableView,
                observableList, this::addNewRow, null));

        columnsMap.forEach((column, columnName) -> column.setCellValueFactory(new PropertyValueFactory<>(columnName)));

        columnsMap.forEach((column, columnName) ->
                column.setOnEditCommit(event ->
                        event.getRowValue()
                                .setFieldValue(WeighingBottleDtoFieldsEnum.getEnumByName(columnName), event.getNewValue())
                ));

        tableView.setItems(observableList);

        addNewRow();
    }

    private void addNewRow() {
        System.out.println("INIT addNewRow");
        if (!tableView.getItems().contains(new WeighingBottleDto())) {

            WeighingBottleDto newWeighingBottleDto = new WeighingBottleDto();
            tableView.getItems().add(newWeighingBottleDto);
            newDtos.add(newWeighingBottleDto);
        }
    }

    public void setColumnsMap() {
        List<TableColumn<WeighingBottleDto, ?>> columns = new ArrayList<>();
        for (var column : tableView.getColumns()) {
            columns.addAll(JavaFXCommonMethods.getAllLeaves(column));
        }

        columnsMap = new LinkedHashMap<>();

        for (TableColumn<WeighingBottleDto, ?> column : columns) {
            columnsMap.put(column, column.getId());
        }
    }

    //region Кнопки
    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        weighingBottleService.updateFromDtos(weighingBottles, dtos);

        weighingBottleService.create(newDtos);

        weighingBottles.clear();
        dtos.clear();
        observableList.clear();

        setObservableList();

        setCellsFormat();
    }

    @FXML
    public void onDeleteButtonClicked(ActionEvent event) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        WeighingBottleDto weighingBottleDto = tableView.getItems().get(selectedIndex);

        weighingBottleService.delete(weighingBottleDto, weighingBottles);

        observableList.remove(weighingBottleDto);

        tableView.layout();
        tableView.getSelectionModel().select(selectedIndex);
    }
    //endregion
}