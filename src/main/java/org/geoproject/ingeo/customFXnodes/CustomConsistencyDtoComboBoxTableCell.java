package org.geoproject.ingeo.customFXnodes;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import org.apache.commons.lang.StringUtils;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;

import java.util.Objects;

import static org.geoproject.ingeo.constants.ServiceConstants.COMBOBOX_LAST_COLUMN_PIXEL_GAP;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_ID;

public class CustomConsistencyDtoComboBoxTableCell extends TableCell<EgeDto, ComboBox<ConsistencyDto>> {

    private final ObservableList<ConsistencyDto> dtoObservableList;
    private final TableColumn<EgeDto, ComboBox<ConsistencyDto>> column;
    private final ObservableList<EgeDto> observableDtoList;
    private final TableView<EgeDto> tableView;

    private final StringConverter<ConsistencyDto> converter;

    {
        converter = getStringConverter();
    }

    public CustomConsistencyDtoComboBoxTableCell(ObservableList<ConsistencyDto> dtoObservableList,
                                                 TableColumn<EgeDto, ComboBox<ConsistencyDto>> column,
                                                 ObservableList<EgeDto> observableDtoList,
                                                 TableView<EgeDto> tableView) {
        this.dtoObservableList = dtoObservableList;
        this.column = column;
        this.observableDtoList = observableDtoList;
        this.tableView = tableView;
    }

    @Override
    public void updateItem(ComboBox<ConsistencyDto> item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            var box = createBox();
            box.setPrefWidth(column.getWidth());
            setGraphic(box);

            column.widthProperty().addListener((o, ow, newWidth) -> box.setMinWidth((Double) newWidth - COMBOBOX_LAST_COLUMN_PIXEL_GAP));

            if (Objects.isNull(observableDtoList.get(getIndex()).getConsistencyDto())) {
                var defaultConsistencyDto = dtoObservableList.stream()
                        .filter(consistencyDto -> Objects.equals(consistencyDto.getId(), ZERO_ID))
                        .findFirst()
                        .orElse(null);
                box.setValue(defaultConsistencyDto);
            } else {
                box.setValue(observableDtoList.get(getIndex()).getConsistencyDto());
            }
        }
    }

    private ComboBox<ConsistencyDto> createBox() {
        var box = new ComboBox<ConsistencyDto>();
        box.getItems().addAll(dtoObservableList);
        box.setConverter(converter);

        box.setOnAction(event -> {

            if ((TableCell<EgeDto, ChoiceBox<ConsistencyDto>>) box.getParent() != null) {
                var tableRow = ((TableCell<EgeDto, ComboBox<ConsistencyDto>>) box.getParent()).getTableRow();

                var egeDto = observableDtoList.get(tableRow.getIndex());
                egeDto.setConsistencyDto((ConsistencyDto) box.getValue());

                tableView.refresh();
            }
        });

        return box;
    }

    private static StringConverter<ConsistencyDto> getStringConverter() {
        return new StringConverter<ConsistencyDto>() {
            @Override
            public String toString(ConsistencyDto object) {

                if (Objects.nonNull(object)) {
                    return object.getConsistencyType();
                }

                return StringUtils.EMPTY;
            }

            @Override
            public ConsistencyDto fromString(String string) {
                return null;
            }
        };
    }
}
