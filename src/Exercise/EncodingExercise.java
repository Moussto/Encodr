package Exercise;

import GUI.controllers.ControllerEncodingExercise;
import GUI.controllers.ControllerHome;
import Model.VoltageState;
import Module.Generateur;
import Vue.EditableVoltageChart;
import Vue.VoltageChart;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mouss on 10/06/2016.
 */
public class EncodingExercise {
    String EncodingTechnique = "BIPOLAR";
    String sequence = "";

    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    private VoltageChart voltageChart;


    public EncodingExercise(String encodingTech) throws IOException {
        EncodingTechnique = encodingTech;
        yAxis.setLowerBound(-1);
        yAxis.setUpperBound(2);
        yAxis.setTickUnit(1);
        voltageChart = new EditableVoltageChart((Axis) xAxis, (Axis)yAxis, 1);
        String test = "";
        for (int i = 0; i < 10; i++) {
            test += (Math.random() < 0.5) ? 0 : 1;
        }
        sequence = test;
        ArrayList<VoltageState> states = Generateur.Encode2With(sequence, EncodingTechnique);
        voltageChart.addSerie(states);
        voltageChart.updateChart();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/fxml/EncodingExercise.fxml"));
        Parent root = fxmlLoader.load();
        ControllerEncodingExercise controller = fxmlLoader.getController();
        StackPane upperpane = controller.getUpperpanel();
        upperpane.getChildren().add(voltageChart);
        controller.setConsigneLabel("Voila la consigne !");

        //create a new scene with root and set the stage
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}
