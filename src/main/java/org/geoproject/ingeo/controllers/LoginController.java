package com.geoproject.igeo.controllers;

import com.geoproject.igeo.enums.StageTitleEnum;
import com.geoproject.igeo.enums.ViewsEnum;
import com.geoproject.igeo.utils.CurrentState;
import com.geoproject.igeo.utils.JavaFXCommonMethods;
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