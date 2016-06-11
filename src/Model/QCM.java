package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by falcon on 6/7/16.
 * une QCM avec une question et N réponses
 * S'il y'a plus de 4 réponses possible on en séléctionnera aléatoirement 4, dont une sera la bonne
 */
public class QCM extends Exercice {
    private String question;
    private ArrayList<String> reponses;
    private int indexBonneReponse;

    public QCM(ArrayList<CodageType> sujet, String question, ArrayList<String> reponses, int indexBonneReponse) {
        super(sujet);
        this.question = question;
        this.reponses = (ArrayList<String>) reponses.clone();
        this.indexBonneReponse = indexBonneReponse;
    }

    //pour la serialization
    public QCM() {
        question = "";
        reponses = null;
        indexBonneReponse = -1;
    }

    public String getQuestion() {
        return question;
    }

    public int getIndexBonneReponse() {
        return indexBonneReponse;
    }

    public ArrayList<String> getReponses() {
        return reponses;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setReponses(ArrayList<String> reponses) {
        this.reponses = reponses;
    }

    public void setIndexBonneReponse(int indexBonneReponse) {
        this.indexBonneReponse = indexBonneReponse;
    }

    public boolean isBonneReponse(String reponse) {
        if(reponse.equals(reponses.get(indexBonneReponse))) {
            return true;
        }
        return false;
    }
}
