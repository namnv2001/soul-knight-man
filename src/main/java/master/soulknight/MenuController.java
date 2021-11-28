package master.soulknight;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button Play;
    @FXML
    private Button Info;

    @FXML
    void toPlayState(ActionEvent event) {
        GamePanel.isPlaying = true;
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void toInfoState(ActionEvent event) {

    }

}
