package org.geoproject.ingeo.controllers.modalWindows;

import org.geoproject.ingeo.dto.classificators.PotDto;
import org.geoproject.ingeo.enums.dtoenums.classificators.PotDtoFieldsEnum;
import org.geoproject.ingeo.models.classificators.Pot;
import org.geoproject.ingeo.services.classificators.PotService;
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
public class PotModalWindowController implements Initializable {

    private final PotService potService;
    protected final ConfigurableApplicationContext applicationContext;

    ObservableList<PotDto> observableList;
    List<Pot> pots;
    List<PotDto> dtos;
    List<PotDto> newDtos;

    Map<TableColumn<PotDto, ?>, String> columnsMap;

    @FXML
    private TableView<PotDto> tableView;

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
        this.pots = potService.findAll();

        List<Pot> activePots = potService.getActiveEntities(pots);

        dtos = potService.getDTOs(activePots);
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
                                .setFieldValue(PotDtoFieldsEnum.getEnumByName(columnName), event.getNewValue())
                ));

        tableView.setItems(observableList);

        addNewRow();
    }

    private void addNewRow() {
        System.out.println("INIT addNewRow");
        if (!tableView.getItems().contains(new PotDto())) {

            PotDto newPotDto = new PotDto();
            tableView.getItems().add(newPotDto);
            newDtos.add(newPotDto);
        }
    }

    public void setColumnsMap() {
        List<TableColumn<PotDto, ?>> columns = new ArrayList<>();
        for (var column : tableView.getColumns()) {
            columns.addAll(JavaFXCommonMethods.getAllLeaves(column));
        }

        columnsMap = new LinkedHashMap<>();

        for (TableColumn<PotDto, ?> column : columns) {
            columnsMap.put(column, column.getId());
        }
    }

    //region Кнопки
    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        potService.updateFromDtos(pots, dtos);

        potService.create(newDtos);

        pots.clear();
        dtos.clear();
        observableList.clear();

        setObservableList();

        setCellsFormat();
    }

    @FXML
    public void onDeleteButtonClicked(ActionEvent event) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        PotDto potDto = tableView.getItems().get(selectedIndex);

        potService.delete(potDto, pots);

        observableList.remove(potDto);

        tableView.layout();
        tableView.getSelectionModel().select(selectedIndex);
    }
    //endregion
}
