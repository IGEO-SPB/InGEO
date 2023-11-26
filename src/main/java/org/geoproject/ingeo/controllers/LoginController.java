package org.geoproject.ingeo.controllers;

import org.geoproject.ingeo.enums.ViewsEnum;
import org.geoproject.ingeo.utils.CurrentState;
import org.geoproject.ingeo.utils.JavaFXCommonMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginController {
    private final ConfigurableApplicationContext applicationContext;
    private final CurrentState currentState;

    @FXML
    protected void onLoginButtonClicked(ActionEvent event) throws IOException {
        System.out.println("CURRENT PROJECT:");
        System.out.println(currentState.getCurrentProject());

        var path = ViewsEnum.ALL_PROJECTS_VIEW.getPath();
        var title = ViewsEnum.ALL_PROJECTS_VIEW.getTitle();
        JavaFXCommonMethods.changeScene(event, path, applicationContext, title);}
}