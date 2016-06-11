package sample;

import Model.CodageType;
import Model.QCM;
import Model.QCMManager;
import Vue.QCMPane;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ArrayList<CodageType> codage = new ArrayList<>();
        codage.add(CodageType.MANCHESTER);
        QCM qcm = QCMManager.getQCMAbout(codage).get(0);
        Parent root = new QCMPane(qcm);
        primaryStage.setTitle("test qcm");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
