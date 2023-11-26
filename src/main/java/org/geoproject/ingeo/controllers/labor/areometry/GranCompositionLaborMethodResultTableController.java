package org.geoproject.ingeo.controllers.resultTables;

import org.geoproject.ingeo.dto.GranCompositionDTO;
import org.geoproject.ingeo.enums.dtoenums.GranCompositionDTOFieldsEnum;
import org.geoproject.ingeo.models.GranCompositionAreometry;
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
public class GranCompositionResultTableController extends AbstractResultTableController<GranCompositionAreometry, GranCompositionDTO> implements Initializable {

    public GranCompositionResultTableController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
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