package Vue;

import Model.CodageType;
import Module.Profil;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by falcon on 6/11/16.
 */
public class ProfilePane extends GridPane {
    private Profil profil;

    private ProgressIndicator xp_progressBar;

    public ProfilePane(Profil profil) {
        super();
        this.profil = profil;
        init();
    }

    private void init() {

        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(0, 10, 0, 10));

        xp_progressBar = new ProgressIndicator();

        //nom du profil
        Text nom = new Text(profil.getEmail());
        nom.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.add(nom, 0, 0);

        //etat des connaissances
        Text etatConnaissance = new Text("Etat des conaissances:");
        etatConnaissance.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.add(etatConnaissance, 0, 2);

        int index = 3;
        Text codageText;
        for(CodageType c : CodageType.values()) {
            codageText = new Text(c.toString() + ":");
            codageText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
            this.add(codageText, 0, index);
            index++;
        }
        updateInfo();
    }

    public void updateInfo() {
        int index = 3;
        Text etat;
        for(CodageType c : CodageType.values()) {
            float ratio = profil.getRatioBonneMauvaiseReponse(c);
            if(ratio < 0) {
                etat = new Text("Non étudié");
                etat.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
            } else if(ratio < 0.6) {
                etat = new Text("Très fragile");
                etat.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
                etat.setFill(Color.DARKRED);
            } else if(ratio < 0.9) {
                etat = new Text("fragile");
                etat.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                etat.setFill(Color.ORANGERED);
            } else if(ratio < 1.1) {
                etat = new Text("Moyenne");
                etat.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                etat.setFill(Color.ORANGE);
            } else if(ratio < 1.5) {
                etat = new Text("Bonne");
                etat.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                etat.setFill(Color.GREENYELLOW);
            } else if(ratio < 1.8) {
                etat = new Text("Très bonne");
                etat.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                etat.setFill(Color.GREEN);
            } else {
                etat = new Text("Maitrisée");
                etat.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
                etat.setFill(Color.LIGHTGREEN);
            }
            this.add(etat, 1, index);
            index++;
        }

        //xp
        Text xp_text = new Text("LvL: " + (profil.getExperience() / 1000 + 1));
        xp_text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.add(xp_text, 2, 0);

        xp_progressBar.setProgress((profil.getExperience() % 1000) / 1000);
        this.add(xp_progressBar, 2, 1);
    }

}
