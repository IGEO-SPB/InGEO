package org.geoproject.ingeo.customFXnodes;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import org.apache.commons.lang.StringUtils;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;

import java.util.Objects;

import static org.geoproject.ingeo.constants.ServiceConstants.CHOICE_BUTTON_TEXT;
import static org.geoproject.ingeo.constants.ServiceConstants.COMBOBOX_COLUMN_PIXEL_GAP;
import static org.geoproject.ingeo.constants.ServiceConstants.VERTICAL_SPLITTER_PATTERN;

public class CustomGenesisDtoComboBoxTableCell extends TableCell<EgeDto, ComboBox<GenesisDto>> {

    private final ObservableList<GenesisDto> genesisDtoObservableList;
    private final TableColumn<EgeDto, ComboBox<GenesisDto>> column;
    private final ObservableList<EgeDto> observableDtoList;
    private final TableView<EgeDto> tableView;

    private final StringConverter<GenesisDto> firstConverter;
    private final StringConverter<GenesisDto> secondConverter;

    {
        firstConverter = getStringConverter(Boolean.FALSE);
        secondConverter = getStringConverter(Boolean.TRUE);
    }

    public CustomGenesisDtoComboBoxTableCell(ObservableList<GenesisDto> genesisDtoObservableList,
                                             TableColumn<EgeDto, ComboBox<GenesisDto>> column,
                                             ObservableList<EgeDto> observableDtoList,
                                             TableView<EgeDto> tableView) {
        this.genesisDtoObservableList = genesisDtoObservableList;
        this.column = column;
        this.observableDtoList = observableDtoList;
        this.tableView = tableView;
    }

    @Override
    public void updateItem(ComboBox<GenesisDto> item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            var box = createBox();
            box.setPrefWidth(column.getWidth());
            setGraphic(box);

            column.widthProperty().addListener((o, ow, newWidth) -> box.setMinWidth((Double) newWidth - COMBOBOX_COLUMN_PIXEL_GAP));

            if (Objects.isNull(observableDtoList.get(getIndex()).getGenesisDto())) {
                box.setConverter(firstConverter);
                var defaultGenesisDto = new GenesisDto();
                defaultGenesisDto.setGenesisCode(CHOICE_BUTTON_TEXT);
                box.setValue(defaultGenesisDto);
            } else {
                box.setValue(observableDtoList.get(getIndex()).getGenesisDto());
            }
        }
    }

    private ComboBox<GenesisDto> createBox() {
        var box = new ComboBox<GenesisDto>();
        box.getItems().addAll(genesisDtoObservableList);
        box.setConverter(secondConverter);

        box.setOnShowing(event -> box.setConverter(firstConverter));
        box.setOnHidden(event -> box.setConverter(secondConverter));

        box.setOnAction(event -> {

            if ((TableCell<EgeDto, ChoiceBox<GenesisDto>>) box.getParent() != null) {
                var tableRow = ((TableCell<EgeDto, ComboBox<GenesisDto>>) box.getParent()).getTableRow();

                var egeDto = observableDtoList.get(tableRow.getIndex());
                egeDto.setGenesisDto((GenesisDto) box.getValue());

                tableView.refresh();
            }
        });

        return box;
    }

    private static StringConverter<GenesisDto> getStringConverter(Boolean isLabelText) {
        return new StringConverter<GenesisDto>() {
            @Override
            public String toString(GenesisDto object) {

                if (isLabelText) {
                    return object.getGenesisCode();
                }

                if (Objects.nonNull(object)) {
                    if (Objects.nonNull(object.getGenesisDescription())) {
                        return object.getGenesisCode() + VERTICAL_SPLITTER_PATTERN + object.getGenesisDescription();
                    } else {
                        return object.getGenesisCode();
                    }
                }

                return StringUtils.EMPTY;
            }

            @Override
            public GenesisDto fromString(String string) {
                return null;
            }
        };
    }
}
