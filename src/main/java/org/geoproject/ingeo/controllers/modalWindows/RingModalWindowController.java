package com.geoproject.igeo.controllers.modalWindows;

import com.geoproject.igeo.dto.classificators.RingDto;
import com.geoproject.igeo.enums.dtoenums.classificators.RingDtoFieldsEnum;
import com.geoproject.igeo.models.classificators.Ring;
import com.geoproject.igeo.services.classificators.RingService;
import com.geoproject.igeo.utils.JavaFXCommonMethods;
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
public class RingModalWindowController implements Initializable {

    private final RingService ringService;
    protected final ConfigurableApplicationContext applicationContext;

    ObservableList<RingDto> observableList;
    List<Ring> rings;
    List<RingDto> dtos;
    List<RingDto> newDtos;

    Map<TableColumn<RingDto, ?>, String> columnsMap;

    @FXML
    private TableView<RingDto> tableView;

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
        this.rings = ringService.findAll();

        List<Ring> activeRings = ringService.getActiveEntities(rings);

        dtos = ringService.getDTOs(activeRings);
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
                                .setFieldValue(RingDtoFieldsEnum.getEnumByName(columnName), event.getNewValue())
                ));

        tableView.setItems(observableList);

        addNewRow();
    }

    private void addNewRow() {
        System.out.println("INIT addNewRow");
        if (!tableView.getItems().contains(new RingDto())) {

            RingDto newRingDto = new RingDto();
            tableView.getItems().add(newRingDto);
            newDtos.add(newRingDto);
        }
    }

    public void setColumnsMap() {
        List<TableColumn<RingDto, ?>> columns = new ArrayList<>();
        for (var column : tableView.getColumns()) {
            columns.addAll(JavaFXCommonMethods.getAllLeaves(column));
        }

        columnsMap = new LinkedHashMap<>();

        for (TableColumn<RingDto, ?> column : columns) {
            columnsMap.put(column, column.getId());
        }
    }

    //region Кнопки
    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        ringService.updateFromDtos(rings, dtos);

        ringService.create(newDtos);

        rings.clear();
        dtos.clear();
        observableList.clear();

        setObservableList();

        setCellsFormat();
    }

    @FXML
    public void onDeleteButtonClicked(ActionEvent event) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        RingDto ringDto = tableView.getItems().get(selectedIndex);

        ringService.delete(ringDto, rings);

        observableList.remove(ringDto);

        tableView.layout();
        tableView.getSelectionModel().select(selectedIndex);
    }
    //endregion
}
