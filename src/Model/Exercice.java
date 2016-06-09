package Model;

import java.util.ArrayList;

/**
 * Created by falcon on 6/7/16.
 */
public abstract class Exercice {
   protected ArrayList<CodageType> sujet;

    public Exercice(ArrayList<CodageType> sujet) {
        this.sujet = (ArrayList<CodageType>) sujet.clone();
    }

    //pour la serialization
    public Exercice() {
        sujet = null;
    }

    public ArrayList<CodageType> getSujet() {
        return sujet;
    }

    public void setSujet(ArrayList<CodageType> sujet) {
        this.sujet = sujet;
    }

    public boolean isOnlyAbout(ArrayList<CodageType> codageType) {
        if(sujet.size() != codageType.size()) {
            return false;
        }

        for(CodageType c : codageType) {
            if(!sujet.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
