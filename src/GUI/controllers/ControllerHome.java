package GUI.controllers;

import GUI.Home;
import Module.Profil;
import Module.ProfileManager;
import Vue.ProfilePane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML //  fx:id="coursbutton"
    private Button coursbutton;

    @FXML //  fx:id="coursbutton"
    private Label labelHome;


    @FXML //  fx:id="exercisebutton"
    private Button exercisebutton;

    @FXML //  fx:id="profilebutton"
    private Button profilebutton;

    @FXML //  fx:id="progressindicator"
    private ProgressIndicator progressindicator;

    @FXML //  fx:id="labelNiveau"
    private Label labelNiveau;





    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        Profil current = ProfileManager.getCurrentProfile();
        labelHome.setText("Salut "+current.getEmail()+" !");
        labelNiveau.setText(current.getNiveau());
        progressindicator.setProgress((current.getExperience() % 1000) / 1000.0);

        coursbutton.setOnAction(event -> {
            Stage stage;
            Parent root;
            stage= (Stage) coursbutton.getScene().getWindow();
            try {
                root =  FXMLLoader.load(getClass().getResource("../fxml/CoursList.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        exercisebutton.setOnAction(event -> {
            Stage stage;
            Parent root;
            stage= (Stage) exercisebutton.getScene().getWindow();
            try {
                root =  FXMLLoader.load(getClass().getResource("../fxml/Exercise.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        profilebutton.setOnAction(event -> {
            Stage stage = new Stage();
            Parent root = new ProfilePane(current);
            stage.setTitle("Profil");
            stage.setScene(new Scene(root, 1000, 500));
            stage.show();


        });


        // initialize your logic here: all @FXML variables will have been injected

    }

    public void setLabelHome(String labelhome) {
        this.labelHome.setText("Bonjour "+labelhome+" !");
    }
}
