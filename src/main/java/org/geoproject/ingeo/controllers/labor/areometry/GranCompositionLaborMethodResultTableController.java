package org.geoproject.ingeo.controllers.labor.areometry;

import org.geoproject.ingeo.controllers.labor.AbstractLaborMethodResultTableController;
import org.geoproject.ingeo.dto.methodDtos.GranCompositionDTO;
import org.geoproject.ingeo.enums.dtoenums.GranCompositionDTOFieldsEnum;
import org.geoproject.ingeo.models.labor.GranCompositionAreometry;
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
public class GranCompositionLaborMethodResultTableController extends AbstractLaborMethodResultTableController<GranCompositionAreometry, GranCompositionDTO> implements Initializable {

    public GranCompositionLaborMethodResultTableController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                                           CurrentState currentState,
                                                           TableService<GranCompositionAreometry, GranCompositionDTO> service) {
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
                        .setFieldValue(GranCompositionDTOFieldsEnum.getEnumByName(columnName), event.getNewValue())
            ));

        tableView.setItems(observableList);
    }
}