package sample;

import Model.VoltageState;
import Module.EditableVoltageChart;
import Module.VoltageChart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by falcon on 5/31/16.
 */
public class VoltageChartTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Voltage");
        //creating the chart
        final VoltageChart voltageChart;
        voltageChart = new EditableVoltageChart((Axis) xAxis, (Axis)yAxis, 0.5f);

        ArrayList<VoltageState> states = new ArrayList<>();
        states.add(VoltageState.DOWN);
        states.add(VoltageState.MIDDLE);
        states.add(VoltageState.MIDDLE);
        states.add(VoltageState.DOWN);
        states.add(VoltageState.UP);
        states.add(VoltageState.UP);
        states.add(VoltageState.MIDDLE);
        voltageChart.addSerie(states);
        voltageChart.updateChart();

        states.clear();
        states.add(VoltageState.MIDDLE);
        states.add(VoltageState.MIDDLE);
        states.add(VoltageState.UP);
        states.add(VoltageState.UP);
        states.add(VoltageState.MIDDLE);
        states.add(VoltageState.MIDDLE);
        states.add(VoltageState.DOWN);
        states.add(VoltageState.DOWN);
        states.add(VoltageState.MIDDLE);
        states.add(VoltageState.MIDDLE);
        states.add(VoltageState.UP);
        states.add(VoltageState.UP);
        states.add(VoltageState.MIDDLE);
        states.add(VoltageState.MIDDLE);
        voltageChart.addSerie(states);
        voltageChart.updateChart();

        StackPane root = new StackPane();
        root.setPrefSize(1200,600 );

        root.getChildren().add(voltageChart);
        Scene scene  = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
