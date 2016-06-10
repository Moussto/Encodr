package GUI.controllers;

import Exercise.EncodingExercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Mouss on 10/06/2016.
 */
public class ControllerExercise implements Initializable {

    @FXML
    private ListView<String> ListCourse;

    @FXML
    private Button retourbutton;

    @FXML
    private Pane secondpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert ListCourse != null : "fx:id=\"ListCourse\" was not injected: check your FXML file 'Exercise.fxml'.";
        assert retourbutton!=null : "fx:id=\"retourbutton\" was not injected: check your FXML file 'Exercise.fxml'.";
        retourbutton.setOnAction(event -> {
            try {
                handleButtonAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ObservableList<String> items = FXCollections.observableArrayList(
                "Non Return to Zero (NRZ)", "Non Return to Zero-Inversed (NRZi)", "Bipolaire", "Manchester");
        ListCourse.setItems(items);
        ListCourse.setOnMouseClicked(event -> {
            String selected = ListCourse.getSelectionModel().getSelectedItem();
            switch (selected) {
                case "Non Return to Zero (NRZ)":{
                    createExercise("NRZ");
                    break;
                }
                case "Non Return to Zero-Inversed (NRZi)":{
                    createExercise("NRZI");
                    break;
                }
                case "Bipolaire":{
                    createExercise("BIPOLAR");
                    break;
                }
                case "Manchester":{
                    createExercise("MANCHESTER");
                    break;
                }
                default:{
                    createExercise("NRZ");
                }
            }


        });
    }

    private void handleButtonAction() throws IOException {
        //get reference to the button's stage
        Stage stage = (Stage) retourbutton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Home.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void createExercise(String selected){
        secondpane.getChildren().clear();
        Button Ex1 = new Button("Exercice de decodage de signal "+selected);
        Ex1.setOnAction(eventbut -> {
            new EncodingExercise(selected);
        });

        Button Ex2 = new Button("Exercice d'encodage "+selected);
        Ex2.setOnAction(eventbut -> {
            // appel de ton exercice lucas
        });
        Button Ex3 = new Button("QCM Sur "+selected);
        Ex3.setOnAction(eventbut -> {
            // appel du QCM
        });
        secondpane.getChildren().add(Ex3);
        secondpane.getChildren().add(Ex2);
        secondpane.getChildren().add(Ex1);
    }
}
