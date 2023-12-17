package org.geoproject.ingeo.customFXnodes;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;

public class CustomChoiceBoxTableCell extends TableCell<EgeDto, ConsistencyDto> {

    private final ChoiceBox<ConsistencyDto> box;
    private boolean selectRowOnClick = true;

    public CustomChoiceBoxTableCell(TableColumn<EgeDto, ConsistencyDto> column, ObservableList<ConsistencyDto> choiceList) {
        this.box = new ChoiceBox<ConsistencyDto>(choiceList);
        this.box.disableProperty().bind(column.editableProperty().not());
        this.box.showingProperty().addListener(event -> {
            final TableView<EgeDto> tableView = getTableView();
            if (selectRowOnClick) {
                tableView.getSelectionModel().select(getTableRow().getIndex());
                tableView.edit(tableView.getSelectionModel().getSelectedIndex(), column);
            } else {
                tableView.edit(getTableRow().getIndex(), column);
            }
        });
        this.box.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
            if (isEditing()) {
                commitEdit(newValue);
            }
        });
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    public CustomChoiceBoxTableCell selectRowOnClick(boolean selectRowOnClick) {
        this.selectRowOnClick = selectRowOnClick;
        return this;
    }

    @Override
    protected void updateItem(ConsistencyDto item, boolean empty) {
        super.updateItem(item, empty);

        setText(null);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            this.box.setValue(item);
            this.setGraphic(this.box);
        }
    }

}
