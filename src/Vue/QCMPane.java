package Vue;

import Model.QCM;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by falcon on 6/11/16.
 */
public class QCMPane extends GridPane {
    private static int maxResponse = 5;
    private QCM qcm;
    private List<RadioButton> buttons;


    public QCMPane(QCM qcm) {
        super();
        buttons = new ArrayList<>();
        this.qcm = qcm;
        init();
    }

    private List<String> setChoices() {
        ArrayList<String> reponses = qcm.getReponses();
        int indexBonneReponse = qcm.getIndexBonneReponse();

        List<String> choix;
        if(reponses.size() <= maxResponse) {
            choix = (List<String>) reponses.clone();
        } else {
            List<String> choix_tmp = (List<String>) reponses.clone();
            choix = new ArrayList<>();
            Collections.shuffle(choix_tmp);
            for(int i = 0; i < maxResponse; ++i) {
                int j = 0;
                String rep = choix_tmp.get(i + j);
                if(!qcm.isBonneReponse(rep)) {
                    choix.add(rep);
                } else j++;
            }
            choix.add(reponses.get(indexBonneReponse));
        }
        Collections.shuffle(choix);
        return choix;
    }

    private void init() {
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(0, 10, 0, 10));

        //la question
        Text question = new Text(qcm.getQuestion());
        question.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.add(question, 0, 0);

        //les reponses
        ToggleGroup group = new ToggleGroup();
        RadioButton button;
        List<String> choix = setChoices();
        int index = 1;
        for(String s : choix) {
            button = new RadioButton(s);
            button.setToggleGroup(group);
            this.add(button, 0, index);
            buttons.add(button);
            index++;
        }


    }

    public boolean isBonneReponse() {
        for(RadioButton b : buttons) {
            if(b.isSelected()) {
                if(qcm.isBonneReponse(b.getText()))
                    return true;
                else return false;
            }
        }
        return false;
    }

    public String getRightAnswer(){
        for(RadioButton b : buttons) {
            if(qcm.isBonneReponse(b.getText()))
                    return b.getText();
        }
        return "notfound";
    }
}
