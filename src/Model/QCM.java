package Model;

import java.util.ArrayList;

/**
 * Created by falcon on 6/7/16.
 * une QCM avec une question et N réponses
 * S'il y'a plus de 4 réponses possible on en séléctionnera aléatoirement 4, dont une sera la bonne
 */
public class QCM extends Exercice {
    final String question;
    final ArrayList<String> reponses;
    final int indexBonneReponse;

    public QCM(String question, ArrayList<String> reponses, int indexBonneReponse) {
        this.question = question;
        this.reponses = (ArrayList<String>) reponses.clone();
        this.indexBonneReponse = indexBonneReponse;
    }


}
