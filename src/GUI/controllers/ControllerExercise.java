package GUI.controllers;

import Exercise.DecodingExercise;
import Exercise.EncodingExercise;
import Model.CodageType;
import Model.QCM;
import Model.QCMManager;
import Module.Profil;
import Module.ProfileManager;
import Vue.QCMPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private Button QCMbutton;

    @FXML
    private Button DecodingButton;

    @FXML
    private Button EncodingButton;

    @FXML
    private Pane secondpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert ListCourse != null : "fx:id=\"ListCourse\" was not injected: check your FXML file 'Exercise.fxml'.";
        assert retourbutton != null : "fx:id=\"retourbutton\" was not injected: check your FXML file 'Exercise.fxml'.";
        QCMbutton.setVisible(false);
        DecodingButton.setVisible(false);
        EncodingButton.setVisible(false);
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
                case "Non Return to Zero (NRZ)": {
                    createExercise("NRZ");
                    break;
                }
                case "Non Return to Zero-Inversed (NRZi)": {
                    createExercise("NRZI");
                    break;
                }
                case "Bipolaire": {
                    createExercise("BIPOLAR");
                    break;
                }
                case "Manchester": {
                    createExercise("MANCHESTER");
                    break;
                }
                default: {
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

    private void createExercise(String selected) {
        QCMbutton.setVisible(true);
        DecodingButton.setVisible(true);
        EncodingButton.setVisible(true);
      // secondpane.getChildren().clear();
        Button Ex1 = new Button("Exercice de decodage de signal " + selected);
        DecodingButton.setText("Exercice de decodage de signal " + selected);
        DecodingButton.setOnAction(eventbut -> {
            new DecodingExercise(selected);
        });

        Button Ex2 = new Button("Exercice d'encodage " + selected);
        EncodingButton.setText("Exercice d'encodage " + selected);
        EncodingButton.setOnAction(eventbut -> {
            try {
                new EncodingExercise(selected);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button Ex3 = new Button("QCM Sur " + selected);
        QCMbutton.setText("QCM Sur " + selected);
        QCMbutton.setOnAction(eventbut -> {
            CodageType a;


            switch (selected) {
                case "NRZ":
                    a = CodageType.NRZ;
                    break;
                case "NRZI":
                    a = CodageType.NRZI;
                    break;
                case "BIPOLAR":
                    a = CodageType.BIPOLAR;
                    break;
                case "MANCHESTER":
                    a = CodageType.MANCHESTER;
                    break;
                default:
                    a = CodageType.NRZ;

            }
            Button validate = new Button("Valider");
            Stage stage2 = new Stage();
            ArrayList<CodageType> temp = new ArrayList<CodageType>();
            temp.add(a);
            GridPane grid = new GridPane();
            int i = 0;
            ArrayList<QCMPane> lQCM = new ArrayList<QCMPane>();
            for (QCM q :QCMManager.getQCMAbout(temp)) {
                QCMPane qp = new QCMPane(q);
                grid.add(qp, 0, i);
                lQCM.add(qp);
                i++;
            }
            grid.add(validate, 0,i+1);
            validate.setOnAction(event -> {
                Profil current = ProfileManager.getCurrentProfile();
                String reponse ="Incorrect !\nLes bonnes reponses sont  :";
                boolean correct = true;
                for (QCMPane qp  : lQCM){
                    if (!qp.isBonneReponse()){
                        current.addMauvaiseReponse(a);
                        correct = false;
                        reponse+="\n"+qp.getRightAnswer();
                    }else {
                        current.addBonneReponse(a);
                    }
                }

                if (!correct){

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Exercice");
                    alert.setContentText(reponse);
                    alert.showAndWait();
                }else{

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Exercice");
                    alert.setContentText("Correct ! ");
                    alert.showAndWait();
                    stage2.close();
                }
                System.out.println();

            });

            ScrollPane sp = new ScrollPane();
            sp.setContent(grid);
            //Parent root = new QCMPane(qcm);
            Scene scene = new Scene(sp);
            stage2.setScene(scene);
            stage2.show();

        });
      //  secondpane.getChildren().add(Ex1);
        //secondpane.getChildren().add(Ex2);
        //secondpane.getChildren().add(Ex3);
    }
}
