package GUI.controllers;

import Model.CodageType;
import Model.VoltageState;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Created by Mouss on 11/06/2016.
 */
public class ControllerEncodingExercise {

    private String EncodingTechnique;

    @FXML
    private Label consigneLabel;

    @FXML
    private Button validatebutton;

    @FXML
    private StackPane subPane;

    @FXML
    void initialize() {
        assert subPane != null : "fx:id=\"subPane\" was not injected: check your FXML file 'EncodingExercise.fxml'.";
        assert consigneLabel != null : "fx:id=\"consigneLabel\" was not injected: check your FXML file 'EncodingExercise.fxml'.";
        assert validatebutton != null : "fx:id=\"validatebutton\" was not injected: check your FXML file 'EncodingExercise.fxml'.";


    }

    public void setConsigneLabel(String consigneLabel) {
        this.consigneLabel.setText(consigneLabel);
    }

    public Button getValidatebutton() {
        return validatebutton;
    }

    public StackPane getUpperpanel() {
        return subPane;
    }

    public void setEncodingTechnique(String encodingTechnique) {
        EncodingTechnique = encodingTechnique;
    }

}
