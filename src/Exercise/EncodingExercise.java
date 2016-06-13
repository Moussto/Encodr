package Exercise;

import GUI.controllers.ControllerEncodingExercise;
import GUI.controllers.ControllerHome;
import Model.CodageType;
import Model.VoltageState;
import Module.Generateur;
import Module.Profil;
import Module.ProfileManager;
import Vue.EditableVoltageChart;
import Vue.VoltageChart;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.GestureEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

import static javafx.scene.input.KeyCode.X;
import static javafx.scene.input.KeyCode.Y;

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

        CodageType Encoding;
        switch (EncodingTechnique) {
            case "NRZ":
                Encoding = CodageType.NRZ;
                break;
            case "NRZI":
                Encoding = CodageType.NRZI;
                break;
            case "BIPOLAR":
                Encoding = CodageType.BIPOLAR;
                break;
            case "MANCHESTER":
                Encoding = CodageType.MANCHESTER;
                break;
            default:
                Encoding = CodageType.NRZ;

        }
        yAxis.setTickUnit(1);
        yAxis.setLowerBound(-2);
        yAxis.setUpperBound(2);
        yAxis.setForceZeroInRange(false);
        yAxis.setAutoRanging(false);
        voltageChart = new EditableVoltageChart((Axis) xAxis, (Axis) yAxis, 1);
        String test = "1";
        ArrayList<VoltageState> start = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            test += (Math.random() < 0.5) ? 0 : 1;
            start.add(VoltageState.MIDDLE);
        }
        start.add(VoltageState.MIDDLE);
        sequence = test;

        voltageChart.addSerie(start);

        voltageChart.updateChart();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/fxml/EncodingExercise.fxml"));
        Parent root = fxmlLoader.load();
        ControllerEncodingExercise controller = fxmlLoader.getController();
        StackPane upperpane = controller.getUpperpanel();
        upperpane.getChildren().add(voltageChart);
        controller.setConsigneLabel("Veuillez encodez la chaine suivante en " + EncodingTechnique + "\n" + sequence);
        controller.setEncodingTechnique(EncodingTechnique);

        //create a new scene with root and set the stage
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        (((EditableVoltageChart) voltageChart).getChartBackground()).setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND); //Change cursor to hand
        });

        (((EditableVoltageChart) voltageChart).getChartBackground()).setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT); //Change cursor to hand
        });

        Button validate = controller.getValidatebutton();
        ArrayList<VoltageState> st = Generateur.Encode2With(sequence, EncodingTechnique);
        if (EncodingTechnique.equals("NRZ")) {
            for (int i = 0; i < st.size(); i++) {
                if (st.get(i).equals(VoltageState.DOWN)) {
                    st.set(i, VoltageState.MIDDLE);
                }
            }
        }


        XYChart.Series correctserie = CreateSeriesFromStates(st);

        validate.setOnAction(event -> {
            XYChart.Series inputseries = CreateSeriesFromStates(voltageChart.getStateSeries().get(0));
            Profil current = ProfileManager.getCurrentProfile();

            if (correctserie.getData().equals(inputseries.getData())) System.out.println("yo");
            else System.out.println("damn");
            if (compareSeries(inputseries, correctserie)) {
                current.addBonneReponse(Encoding);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Exercice");
                alert.setContentText("Correct ! ");
                alert.showAndWait();
                stage.close();

            } else {
                current.addMauvaiseReponse(Encoding);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Exercice");
                alert.setContentText("Incorrect ! ");
                alert.showAndWait();
            }

        });


        stage.show();


    }


    public XYChart.Series CreateSeriesFromStates(ArrayList<VoltageState> st) {
        XYChart.Series correctserie = new XYChart.Series();
        float xValue = 1;
        for (int i = 1; i <= st.size(); ++i) {
            if (st.get(i - 1) == VoltageState.DOWN) {
                correctserie.getData().add(new XYChart.Data(xValue - 1, -1));
                correctserie.getData().add(new XYChart.Data(xValue, -1));
            } else if (st.get(i - 1) == VoltageState.MIDDLE) {
                correctserie.getData().add(new XYChart.Data(xValue - 1, 0));
                correctserie.getData().add(new XYChart.Data(xValue, 0));
            } else if (st.get(i - 1) == VoltageState.UP) {
                correctserie.getData().add(new XYChart.Data(xValue - 1, 1));
                correctserie.getData().add(new XYChart.Data(xValue, 1));
            }

            xValue += 1;
        }

        return correctserie;

    }

    public boolean compareSeries(XYChart.Series a, XYChart.Series b) {
        ObservableList<XYChart.Data> la = a.getData();
        ObservableList<XYChart.Data> lb = b.getData();
        for (int i = 0; i < la.size(); i++) {

            if (!la.get(i).toString().equals(lb.get(i).toString())) {
                return false;
            }
        }
        return true;

    }


}
