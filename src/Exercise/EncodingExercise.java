package Exercise;

import Model.VoltageState;
import Module.Generateur;
import Vue.EditableVoltageChart;
import Vue.VoltageChart;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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


    public EncodingExercise(String encodingTech){
        EncodingTechnique = encodingTech;
        voltageChart = new EditableVoltageChart((Axis) xAxis, (Axis)yAxis, 0.5f);
        String test = "";
        for (int i = 0; i < 10; i++) {
            test += (Math.random() < 0.5) ? 0 : 1;
        }
        sequence = test;
        ArrayList<VoltageState> states = Generateur.Encode2With(sequence, EncodingTechnique);
        voltageChart.addSerie(states);
        voltageChart.updateChart();
        StackPane root = new StackPane();
        root.setPrefSize(1200,600 );

        Stage stage = new Stage();
        root.getChildren().add(voltageChart);
        Scene scene  = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}
