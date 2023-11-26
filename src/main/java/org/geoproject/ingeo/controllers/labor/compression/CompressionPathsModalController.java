package org.geoproject.ingeo.controllers.labor.compression;

import org.geoproject.ingeo.models.labor.CompressionPaths;
import org.geoproject.ingeo.repositories.labor.CompressionPathsRepository;
import org.geoproject.ingeo.utils.CurrentState;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class CompressionPathsModalController implements Initializable {

    private final CompressionPathsRepository repository;
    private final CurrentState state;

    public CompressionPathsModalController(CompressionPathsRepository repository, CurrentState state) {
        this.repository = repository;
        this.state = state;
    }
    private CompressionPaths dbModel = new CompressionPaths();

    public TextField path203k;
    public TextField path213k;
    public TextField path208k;
    public TextField pathLog;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var queriedModel = repository.getByProject(state.getCurrentProject());
        if(queriedModel != null) dbModel = queriedModel;

        path203k.textProperty().setValue(dbModel.getPathK203());
        path208k.textProperty().setValue(dbModel.getPathK208());
        path213k.textProperty().setValue(dbModel.getPathK213());
        pathLog.textProperty().setValue(dbModel.getPathOther());
    }

    public void onSaveClicked(ActionEvent actionEvent) {
        dbModel.setPathK203(path203k.getText());
        dbModel.setPathK208(path208k.getText());
        dbModel.setPathK213(path213k.getText());
        dbModel.setPathOther(pathLog.getText());
        dbModel.setProject(state.getCurrentProject());

        repository.save(dbModel);
    }
}
