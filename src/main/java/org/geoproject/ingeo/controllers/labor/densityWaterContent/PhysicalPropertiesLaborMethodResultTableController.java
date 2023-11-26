package org.geoproject.ingeo.controllers.labor.densityWaterContent;

import org.geoproject.ingeo.controllers.labor.AbstractLaborMethodResultTableController;
import org.geoproject.ingeo.dto.methodDtos.PhysicalPropertiesDTO;
import org.geoproject.ingeo.enums.dtoenums.PhysicalPropertiesDTOFieldsEnum;
import org.geoproject.ingeo.models.labor.PhysicalProperties;
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
public class PhysicalPropertiesLaborMethodResultTableController extends AbstractLaborMethodResultTableController<PhysicalProperties, PhysicalPropertiesDTO> implements Initializable {

    //todo нейминг + коррозионный метод
    //Корроз. агр. грунта, Om*m
    //Корроз. агр. грунта, Om*m
    //Корроз. агр. грунта, A/m^2
    //Корроз. агр. грунта, A/m^2

    public PhysicalPropertiesLaborMethodResultTableController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                                              CurrentState currentState, TableService<PhysicalProperties, PhysicalPropertiesDTO> service) {
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
                                .setFieldValue(PhysicalPropertiesDTOFieldsEnum.getEnumByName(columnName), event.getNewValue())
                ));

        tableView.setItems(observableList);
    }
}