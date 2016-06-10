package GUI.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


/**
 * Created by Mouss on 10/06/2016.
 */
public class ControllerCoursList {

    @FXML
    private ListView<String> ListCourse;

    @FXML
    private WebView Webview;

    @FXML
    private Button retourbutton;



    @FXML
    void initialize() {
        assert ListCourse != null : "fx:id=\"ListCourse\" was not injected: check your FXML file 'CoursList.fxml'.";
        assert Webview != null : "fx:id=\"Webview\" was not injected: check your FXML file 'CoursList.fxml'.";
        assert retourbutton!=null : "fx:id=\"retourbutton\" was not injected: check your FXML file 'CoursList.fxml'.";

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
                    URL url = getClass().getResource("../../html/nrz.html");
                    System.out.println(url);
                    WebEngine webEngine = Webview.getEngine();
                    webEngine.load(url.toExternalForm());
                    break;
                }
                case "Non Return to Zero-Inversed (NRZi)":{
                    URL url = getClass().getResource("../../html/nrzi.html");
                    WebEngine webEngine = Webview.getEngine();
                    webEngine.load(url.toExternalForm());
                    System.out.println("NRZi");
                    break;
                }
                case "Bipolaire":{
                    URL url = getClass().getResource("../../html/bipol.html");
                    WebEngine webEngine = Webview.getEngine();
                    webEngine.load(url.toExternalForm());
                    System.out.println("Bipolaire");
                    break;
                }
                case "Manchester":{
                    URL url = getClass().getResource("../../html/manchester.html");
                    WebEngine webEngine = Webview.getEngine();
                    webEngine.load(url.toExternalForm());
                    System.out.println("Manchester");
                    break;
                }
                default:{
                    URL url = getClass().getResource("../../html/nrz.html");
                    System.out.println(url);
                    WebEngine webEngine = Webview.getEngine();
                    webEngine.load(url.toExternalForm());
                }
            }


        });


    }

    @FXML
    private void handleButtonAction() throws IOException {
            Stage stage;
            //get reference to the button's stage
            stage = (Stage) retourbutton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Home.fxml"));
            Parent root = fxmlLoader.load();
            ControllerHome controller = fxmlLoader.getController();
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

}
