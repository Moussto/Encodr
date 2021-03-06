package Exercise;

import Model.CodageType;
import Module.Generateur;
import Module.Profil;
import Module.ProfileManager;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * Created by Mouss on 10/06/2016.
 */
public class DecodingExercise {
    static String EncodingTechnique = "BIPOLAR";
    String sequence = "";

    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    final LineChart<Number, Number> lineChart =
            new LineChart<Number, Number>(xAxis, yAxis);


    public void handleerror(String inputuser) {
        int i = 0;
        String correct = "";
        String incorrect = "";
        for (i = 0; i < inputuser.length(); i++) {
            if (sequence.charAt(i) == inputuser.charAt(i)) {
                correct += inputuser.charAt(i);
            } else {
                break;
            }
        }
        for (int y = i; y < inputuser.length(); y++) {
            incorrect += inputuser.charAt(y);
        }

        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        DrawMonoClockFromX(correct, series, 0);
        DrawMonoClockFromX(incorrect, series2, i);
        series.setName("Correct");
        series2.setName("Incorrect");
        lineChart.getData().add(series2);
        lineChart.getData().add(series);

        String secondanalysis = checkotherCoding(inputuser);

        if (!secondanalysis.equals("NONE")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct Exercice");
            alert.setContentText("Attention vous avez utilisé l'encodage "+secondanalysis+" pour decoder ce signal !\nIl faut utiliser "+EncodingTechnique );
            alert.showAndWait();

        }


    }

    //nrz
    public void DrawMonoClock(String in, XYChart.Series series) {
        Generateur g = new Generateur();
        String out = g.NRZ(in);
        String[] tokens = out.split("-");
        int voltmax = 5;
        int voltmin = 0;
        System.out.println(tokens[0]);
        for (int i = 1; i < tokens.length + 1; i++) {
            if (tokens[i - 1].equals("UP")) {
                series.getData().add(new XYChart.Data(i - 1, voltmax));
                series.getData().add(new XYChart.Data(i, voltmax));
            } else if (tokens[i - 1].equals("DOWN")) {
                series.getData().add(new XYChart.Data(i - 1, voltmin));
                series.getData().add(new XYChart.Data(i, voltmin));
            }
        }
    }

    // 0 for normal code
    public static void DrawMonoClockFromX(String in, XYChart.Series series, int x) {
        Generateur g = new Generateur();
        String out = g.EncodeWith(in, EncodingTechnique);
        String[] tokens = out.split("-");
        int voltmax = 5;
        int voltmin = 0;
        if (EncodingTechnique.equals("BIPOLAR")) voltmin = -5;

        for (int i = 1; i < tokens.length + 1; i++) {
            if (tokens[(i - 1)].equals("UP")) {
                series.getData().add(new XYChart.Data(x + i - 1, voltmax));
                series.getData().add(new XYChart.Data(x + i, voltmax));
            } else if (tokens[i - 1].equals("DOWN")) {
                series.getData().add(new XYChart.Data(x + i - 1, voltmin));
                series.getData().add(new XYChart.Data(x + i, voltmin));
            } else if (tokens[i - 1].equals("MIDDLE")) {
                series.getData().add(new XYChart.Data(x + i - 1, 0));
                series.getData().add(new XYChart.Data(x + i, 0));
            }
        }
    }

    public void DrawMonoClockFromXToY(String in, XYChart.Series series, int x, int y) {
        Generateur g = new Generateur();
        String out = g.NRZ(in);
        String[] tokens = out.split("-");
        int voltmax = 5;
        int voltmin = 0;
        for (int i = x + 1; i < y; i++) {
            if (tokens[(i - 1)].equals("UP")) {
                series.getData().add(new XYChart.Data(x + i - 1, voltmax));
                series.getData().add(new XYChart.Data(x + i, voltmax));
            } else if (tokens[i - 1].equals("DOWN")) {
                series.getData().add(new XYChart.Data(x + i - 1, voltmin));
                series.getData().add(new XYChart.Data(x + i, voltmin));
            }
        }
    }


     public DecodingExercise(String encodingtech) {
          Stage stage = new Stage();
         EncodingTechnique = encodingtech;

         //same as above due to shitty preparation
         CodageType Encoding;
         switch (encodingtech) {
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


        yAxis.setLabel("Voltage");


        lineChart.setStyle("CHART_COLOR_1: grey;");
        lineChart.setTitle("Codage " + EncodingTechnique);
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Signal a traduire");

        //series.setName("My portfolio");
        //populating the series with data

        TextField notification = new TextField();
        notification.setText("Reponse : ");

        notification.clear();

        String test = "1";
        for (int i = 1; i < 10; i++) {
            test += (Math.random() < 0.5) ? 0 : 1;
        }
        sequence = test;
         System.out.println(sequence);
        //DrawMonoClock(test, series);
        DrawMonoClockFromX(test, series, 0);
        lineChart.getData().add(series);


        StackPane root = new StackPane();
        root.setPrefSize(1200, 600);


        SplitPane sp = new SplitPane();
        final StackPane sp1 = new StackPane();

        sp1.getChildren().add(lineChart);

        final StackPane sp2 = new StackPane();
        final TextField inputuser = new TextField();
        inputuser.setPromptText("Veuillez traduire le signal ci-dessus");
        inputuser.setPrefWidth(300);
        inputuser.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    Profil current = ProfileManager.getCurrentProfile();

                    Boolean error = false;
                    String text = inputuser.getText();
                    if (text.length() < sequence.length()) {
                        error = true;
                        current.addMauvaiseReponse(Encoding);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Exercice");
                        alert.setContentText("Il faut entrez plus de nombres");
                        alert.showAndWait();
                    }
                    if (text.length() > sequence.length()) {
                        error = true;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Exercice");
                        alert.setContentText("Il faut entrez moins de nombres");
                        alert.showAndWait();
                    }


                    if (!text.matches("[0-1]+")) {
                        error = true;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Exercice");
                        alert.setContentText("Il ne faut pas inserez de caracteres autres que 0 et 1 ");
                        alert.showAndWait();
                    }

                    if (!error){
                        if (sequence.equals(text)) {
                            current.addBonneReponse(Encoding);

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Exercice");
                            alert.setContentText("Felicitations ! ");
                            alert.showAndWait();
                            stage.close();
                        } else {
                            // Generateur doit analyser la reponse et aider le pelo
                            if (!text.isEmpty()) handleerror(text);
                            sp2.getChildren().addAll(new Label("La sequence "+text+" est incorrect"));
                        }
                    }

                    // clear text
                    inputuser.setDisable(true);
                    inputuser.setVisible(false);
                }
            }
        });


        sp2.getChildren().addAll(inputuser);


        sp.getItems().addAll(sp1, sp2);
        sp.setDividerPositions(0.8f, 0.6f);
        sp.setOrientation(Orientation.VERTICAL);


        root.getChildren().add(sp);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String checkotherCoding(String text){
        for (CodageType a : CodageType.values()){
            if(!a.equals(EncodingTechnique)){
                String one = Generateur.EncodeWith(text, a.toString());
                String two = Generateur.EncodeWith(sequence,EncodingTechnique);
                if(one.equals(two)){
                    return a.toString();
                }
            }
        }
        return "NONE";

    }


}

