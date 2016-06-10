package GUI;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Mouss on 10/06/2016.
 */
public class Home {

    public Home() throws IOException {
        Stage subStage = new Stage();
        subStage.setTitle("Accueil");

        Parent root = FXMLLoader.load(getClass().getResource("fxml/Home.fxml"));
        subStage.setScene(new Scene(root));
        subStage.show();
    }
}
