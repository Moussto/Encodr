package sample;

import Module.Generateur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class LineChartSample extends Application {

    //nrz
    public void DrawMonoClock(String in, XYChart.Series series){
        Generateur g =new Generateur();
        String out = g.NRZ(in);
        String[] tokens = out.split("-");
        int voltmax = 5;
        int voltmin = 0;
        System.out.println(tokens[0]);
        series.getData().add(new XYChart.Data(0, 0));
        for (int i = 1; i < tokens.length+1; i++) {
            if(tokens[i-1].equals("UP")){
                series.getData().add(new XYChart.Data(i-1, voltmax));
                series.getData().add(new XYChart.Data(i, voltmax));
            }else if (tokens[i-1].equals("DOWN")){
                series.getData().add(new XYChart.Data(i-1, voltmin));
                series.getData().add(new XYChart.Data(i, voltmin));
            }
        }
    }


    @Override public void start(Stage stage) {

         //   Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        //stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Voltage");
        //creating the chart
        final LineChart<Number,Number> lineChart;
        lineChart = new LineChart<>(xAxis,yAxis);

        //lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        //series.setName("My portfolio");
        //populating the series with data

        TextField notification = new TextField ();
        notification.setText("Reponse : ");

        notification.clear();

        //0110001011
        //String test = "1111010101";
        String test="";
        for(int i=0;i<10;i++){
           test +=(Math.random()<0.5)?0:1;
        }
        final String sequence = test;
        DrawMonoClock(test, series);
        lineChart.getData().add(series);


        StackPane root = new StackPane();
        root.setPrefSize(1200,600 );


        SplitPane sp = new SplitPane();
        final StackPane sp1 = new StackPane();
        sp1.getChildren().add(lineChart);

        final StackPane sp2 = new StackPane();
        final TextField inputuser = new TextField();
        inputuser.setPromptText("Enter your first name.");
        inputuser.setPrefWidth(300);;
        inputuser.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                    String text = inputuser.getText();
                    if(text.length() < 1){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Exercice");
                        alert.setContentText("Il faut entrez plus de nombres");
                        alert.showAndWait();
                    }

                    if(!text.matches("[0-1]+")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Exercice");
                        alert.setContentText("Il ne faut pas inserez de caracteres autres que 0 et 1 ");
                        alert.showAndWait();
                    }

                    if(sequence.equals(text)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Exercice");
                        alert.setContentText("Felicitation ! ");
                        alert.showAndWait();
                    }else{
                        // Generateur doit analyser la reponse et aider le pelo
                    }
                    // clear text
                    inputuser.setText("");
                }
            }
        });
        sp2.getChildren().addAll(inputuser);


        sp.getItems().addAll(sp1, sp2);
        sp.setDividerPositions(0.8f, 0.6f);
        sp.setOrientation(Orientation.VERTICAL);

        root.getChildren().add(sp);
        Scene scene  = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}