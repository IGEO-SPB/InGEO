package org.geoproject.ingeo.controllers.labor.constructionMeshAreometry;

import org.geoproject.ingeo.controllers.labor.AbstractLaborMethodResultTableController;
import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshAreometryResultDto;
import org.geoproject.ingeo.enums.dtoenums.ConstructionMeshAreometryResultDTOFieldsEnum;
import org.geoproject.ingeo.models.labor.GranCompositionConstructionMeshAreometry;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.TableService;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ConstructionMeshAreometryLaborMethodResultTableController extends AbstractLaborMethodResultTableController<GranCompositionConstructionMeshAreometry, ConstructionMeshAreometryResultDto>
        implements Initializable {

    public ConstructionMeshAreometryLaborMethodResultTableController(ConfigurableApplicationContext applicationContext,
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