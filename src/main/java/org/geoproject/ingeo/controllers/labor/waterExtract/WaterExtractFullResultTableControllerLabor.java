package org.geoproject.ingeo.controllers.laborMethods.waterExtract;

import org.geoproject.ingeo.controllers.laborMethods.AbstractLaborMethodTableController;
import org.geoproject.ingeo.dto.WaterExtractFullResultDto;
import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.enums.dtoenums.WaterExtractFullResultDtoFieldsEnum;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.models.WaterExtractFullResult;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.mainViews.SurveyPointsService;
import org.geoproject.ingeo.services.tableViews.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_EXTRACT_CHOOSE_METHOD_VOLUME;
import static org.geoproject.ingeo.enums.StageTitleEnum.WATER_EXTRACT_FULL_DATA;

@Component
public class WaterExtractFullResultTableControllerLabor extends AbstractLaborMethodTableController<WaterExtractFullResult, WaterExtractFullResultDto>
        implements Initializable {

    @FXML
    private TableColumn<WaterExtractFullResultDto, Boolean> isBlocked;


    public WaterExtractFullResultTableControllerLabor(ConfigurableApplicationContext applicationContext,
                                                      SampleService sampleService, SurveyPointsService surveyPointsService, CurrentState currentState,
                                                      TableService<WaterExtractFullResult, WaterExtractFullResultDto> service) {
        super(applicationContext, sampleService, surveyPointsService, currentState, service);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    @Override
    public void setCellsFormat() {
        columnsMap.values().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView,
                observableList, null, null));

        columnsMap.forEach((columnName, column) -> {

            if (columnName.equals(WaterExtractFullResultDtoFieldsEnum.IS_BLOCKED.getName())) {
                setIsBlockedCheckBox();
            } else {
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        columnsMap.forEach((columnName, column) ->
                column.setOnEditCommit(event -> {
                    var value = getValueForDtoSetting(event);

                    var dtoFieldEnum = WaterExtractFullResultDtoFieldsEnum.getEnumByName(columnName);

                    event.getRowValue().setFieldValue(dtoFieldEnum, value);
                }));

        tableView.setItems(observableList);
    }

    @Override
    public void addNewRow() {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("addNewRow"));
    }

    private void setIsBlockedCheckBox() {
        isBlocked.setCellFactory(cellValue -> new CheckBoxTableCell<>());
        isBlocked.setCellValueFactory(cellData -> {
            WaterExtractFullResultDto waterExtractFullResultDto = cellData.getValue();
            BooleanProperty property = new ReadOnlyBooleanWrapper(waterExtractFullResultDto.getIsBlocked());

            property.addListener((observable, oldValue, newValue) -> {
                waterExtractFullResultDto.setIsBlockedTransient(new SimpleBooleanProperty(newValue));
                waterExtractFullResultDto.setIsBlocked(newValue);
            });

            return property;
        });
    }

    private static Object getValueForDtoSetting(TableColumn.CellEditEvent<WaterExtractFullResultDto, ?> event) {
        var value = event.getNewValue();

        if (event.getNewValue().equals(StringUtils.EMPTY)) {
            value = null;
        }
        return value;
    }

    //region кнопки


    @FXML
    public void onWaterExtractChooseMethodVolumeButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_CHOOSE_METHOD_VOLUME_VIEW.getPath(),
                applicationContext, WATER_EXTRACT_CHOOSE_METHOD_VOLUME.getTitle());
    }

    @FXML
    public void onWaterExtractFullDataTableButtonClicked(ActionEvent event) throws IOException {
        JavaFXCommonMethods.changeScene(event, ViewsEnum.WATER_EXTRACT_FULL_DATA_VIEW.getPath(),
                applicationContext, WATER_EXTRACT_FULL_DATA.getTitle());
    }

    @FXML
    public void onWaterExtractFullResultTableButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Уже на экране результирующей таблицы");
    }

    //endregion
}
