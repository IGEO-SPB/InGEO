package org.geoproject.ingeo.controllers.laborMethods.waterChemistry;

import org.geoproject.ingeo.controllers.laborMethods.AbstractLaborMethodTableController;
import org.geoproject.ingeo.dto.WaterSampleResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.models.WaterSampleResult;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.mainViews.SurveyPointsService;
import org.geoproject.ingeo.services.tableViews.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class WaterChemistryFinalResultTableControllerLabor extends AbstractLaborMethodTableController<WaterSampleResult, WaterSampleResultDto>
        implements Initializable {

    public WaterChemistryFinalResultTableControllerLabor(ConfigurableApplicationContext applicationContext,
                                                         SampleService sampleService, SurveyPointsService surveyPointsService, CurrentState currentState,
                                                         TableService<WaterSampleResult, WaterSampleResultDto> service) {
        super(applicationContext, sampleService, surveyPointsService, currentState, service);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    @Override
    public void setCellsFormat() {
        columnsMap.values().forEach(column -> column.setEditable(false));

        columnsMap.values().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView, observableList,
                null, null));


        List<String> correctionZeroColumns = new ArrayList<>(Arrays.asList(
                "CO3_v", "CO3_eq", "CO3_eq_proc",
                "NO2_v", "NO2_eq", "NO2_eq_proc",
                "NO3_v", "NO3_eq", "NO3_eq_proc",
                "NH4_v", "NH4_eq", "NH4_eq_proc",
                "Fe_v", "Fe_eq"));

        columnsMap.forEach((columnName, column) -> {
            if (correctionZeroColumns.contains(columnName)) {

                ((TableColumn<WaterSampleResultDto, String>) column).setCellValueFactory(data -> {
                    Float value;

                    if (columnName.matches("CO3.+")) {
                        value = data.getValue().getCO3_v();
                    } else if (columnName.matches("NO2.+")) {
                        value = data.getValue().getNO2_v();
                    } else if (columnName.matches("NO3.+")) {
                        value = data.getValue().getNO3_v();
                    } else if (columnName.matches("NH4.+")) {
                        value = data.getValue().getNH4_v();
                    } else {
                        value = data.getValue().getFe_v();
                    }

                    String descriptionText;

                    if (value < 0.1F && !columnName.matches(".*_v")) {
                        descriptionText = StringUtils.EMPTY;
                    } else if (value == 0) {
                        descriptionText = "нет";
                    } else if (value > 0 && value < 0.1F) {
                        descriptionText = "следы";
                    } else {
                        descriptionText = setDescriptionText(columnName, data.getValue());
                    }

                    return new SimpleStringProperty(descriptionText);
                });

            } else {
                column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            }
        });

        tableView.setItems(observableList);
    }

    private String setDescriptionText(String columnName, WaterSampleResultDto value) {
        return switch (columnName) {
            case "CO3_eq" -> String.format("%.2f", value.getCO3_eq());
            case "CO3_eq_proc" -> String.format("%.2f", value.getCO3_eq_proc());
            case "NO2_eq" -> String.format("%.2f", value.getNO2_eq());
            case "NO2_eq_proc" -> String.format("%.2f", value.getNO2_eq_proc());
            case "NO3_eq" -> String.format("%.2f", value.getNO3_eq());
            case "NO3_eq_proc" -> String.format("%.2f", value.getNO3_eq_proc());
            case "NH4_eq" -> String.format("%.2f", value.getNH4_eq());
            case "NH4_eq_proc" -> String.format("%.2f", value.getNH4_eq_proc());
            default -> String.format("%.2f", value.getFe_eq());
        };
    }

    public void addNewRow() {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("addNewRow"));
    }
}
