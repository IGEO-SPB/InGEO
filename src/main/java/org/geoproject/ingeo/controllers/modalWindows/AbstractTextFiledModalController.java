package org.geoproject.ingeo.controllers.modalWindows;

import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.services.ModalWindowService;
import org.geoproject.ingeo.utils.CurrentState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTextFiledModalController<T, Y> {

    protected final ConfigurableApplicationContext applicationContext;
    protected final SampleService sampleService;
    protected final ModalWindowService<T, Y> service;
    protected final CurrentState currentState;

    protected Map<TextField, String> allTextFieldMap;

    protected T object;
    protected Y dto;

    protected AbstractTextFiledModalController(ConfigurableApplicationContext applicationContext, SampleService sampleService,
                                               ModalWindowService<T, Y> service, CurrentState currentState) {
        this.applicationContext = applicationContext;
        this.sampleService = sampleService;
        this.service = service;
        this.currentState = currentState;
    }

    public void init() {
        initMap();

        setDTO();

        setTextFieldMap();

        setFieldBinding();
    }

    public void initMap() {
        allTextFieldMap = new HashMap<>();
    }

    public abstract void setDTO();

    public abstract void setTextFieldMap();

    public abstract void setFieldBinding();

    public void saveEntity() {
        service.create(dto);
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        saveEntity();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCancellButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
