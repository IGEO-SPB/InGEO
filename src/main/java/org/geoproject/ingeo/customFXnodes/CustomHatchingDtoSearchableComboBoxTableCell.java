package org.geoproject.ingeo.customFXnodes;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import org.apache.commons.lang.StringUtils;
import org.controlsfx.control.SearchableComboBox;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;

import java.util.Objects;

import static org.geoproject.ingeo.constants.ServiceConstants.COMBOBOX_LAST_COLUMN_PIXEL_GAP;

public class CustomHatchingDtoSearchableComboBoxTableCell extends TableCell<EgeDto, ComboBox<HatchingDto>> {

    private final ObservableList<HatchingDto> dtoObservableList;
    private final TableColumn<EgeDto, ComboBox<HatchingDto>> column;
    private final ObservableList<EgeDto> observableDtoList;
    private final TableView<EgeDto> tableView;

    private final StringConverter<HatchingDto> converter;

    {
        converter = getStringConverter();
    }

    public CustomHatchingDtoSearchableComboBoxTableCell(ObservableList<HatchingDto> dtoObservableList,
                                                        TableColumn<EgeDto, ComboBox<HatchingDto>> column,
                                                        ObservableList<EgeDto> observableDtoList,
                                                        TableView<EgeDto> tableView) {
        this.dtoObservableList = dtoObservableList;
        this.column = column;
        this.observableDtoList = observableDtoList;
        this.tableView = tableView;
    }

    @Override
    public void updateItem(ComboBox<HatchingDto> item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            var box = createBox();
            box.setPrefWidth(column.getWidth());
            setGraphic(box);

            column.widthProperty().addListener((o, ow, newWidth) -> box.setMinWidth((Double) newWidth - COMBOBOX_LAST_COLUMN_PIXEL_GAP));

            if (Objects.isNull(observableDtoList.get(getIndex()).getHatchingDto())) {
                var defaultHatchingDto = new HatchingDto();
                defaultHatchingDto.setShortName(StringUtils.EMPTY);
                box.setValue(defaultHatchingDto);
            } else {
                box.setValue(observableDtoList.get(getIndex()).getHatchingDto());
            }
        }
    }

    private ComboBox<HatchingDto> createBox() {
        var box = new SearchableComboBox<HatchingDto>();
        box.getItems().addAll(dtoObservableList);
        box.setConverter(converter);

        box.setOnAction(event -> {

            if ((TableCell<EgeDto, ChoiceBox<HatchingDto>>) box.getParent() != null) {
                var tableRow = ((TableCell<EgeDto, ComboBox<HatchingDto>>) box.getParent()).getTableRow();

                var egeDto = observableDtoList.get(tableRow.getIndex());
                egeDto.setHatchingDto((HatchingDto) box.getValue());
            }
        });

        return box;
    }

    private static StringConverter<HatchingDto> getStringConverter() {
        return new StringConverter<HatchingDto>() {
            @Override
            public String toString(HatchingDto object) {

                if (Objects.nonNull(object)) {
                    return object.getShortName();
                }

                return StringUtils.EMPTY;
            }

            @Override
            public HatchingDto fromString(String string) {
                return null;
            }
        };
    }
}
