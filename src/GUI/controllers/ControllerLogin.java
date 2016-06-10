package GUI.controllers;

import GUI.Home;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    @FXML //  fx:id="loginbutton"
    private Button loginbutton; // Value injected by FXMLLoader

    @FXML //  fx:id="logininput"
    private TextField logininput; // Value injected by FXMLLoader


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert loginbutton != null : "fx:id=\"loginbutton\" was not injected: check your FXML file" + fxmlFileLocation;
        assert logininput != null : "fx:id=\"logininput\" was not injected: check your FXML file" + fxmlFileLocation;
        loginbutton.setOnAction(event -> {
            try {
                handleButtonAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logininput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginbutton.fire();
            }
        });


        // initialize your logic here: all @FXML variables will have been injected

    }

    @FXML
    private void handleButtonAction() throws IOException {
        if(!logininput.getText().isEmpty()){
            Stage stage;
            //get reference to the button's stage
            stage = (Stage) loginbutton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Home.fxml"));
            Parent root = fxmlLoader.load();
            ControllerHome controller = fxmlLoader.getController();
            controller.setLabelHome(logininput.getText());

            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

}
