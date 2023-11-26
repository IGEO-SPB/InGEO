package org.geoproject.ingeo.controllers.resultTables;

import org.geoproject.ingeo.dto.ConstructionMeshAreometryResultDto;
import org.geoproject.ingeo.enums.dtoenums.ConstructionMeshAreometryResultDTOFieldsEnum;
import org.geoproject.ingeo.models.GranCompositionConstructionMeshAreometry;
import org.geoproject.ingeo.services.mainViews.SampleService;
import org.geoproject.ingeo.services.tableViews.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ConstructionMeshAreometryResultTableController extends AbstractResultTableController<GranCompositionConstructionMeshAreometry, ConstructionMeshAreometryResultDto>
        implements Initializable {

    public ConstructionMeshAreometryResultTableController(ConfigurableApplicationContext applicationContext,
                                                          SampleService sampleService, CurrentState currentState,
                                                          TableService<GranCompositionConstructionMeshAreometry, ConstructionMeshAreometryResultDto> service) {
        super(applicationContext, sampleService, currentState, service);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    @Override
    public void setCellsFormat() {
        columnsMap.keySet().forEach(e -> JavaFXCommonMethods.setCellFactory(e, tableView, observableList,
                null, null));

        columnsMap.forEach((column, columnName) -> column.setCellValueFactory(new PropertyValueFactory<>(columnName)));

        columnsMap.forEach((column, columnName) ->
                column.setOnEditCommit(event ->
                        event.getRowValue()
                                .setFieldValue(ConstructionMeshAreometryResultDTOFieldsEnum.getEnumByName(columnName), event.getNewValue())
                ));

        tableView.setItems(observableList);
    }
}